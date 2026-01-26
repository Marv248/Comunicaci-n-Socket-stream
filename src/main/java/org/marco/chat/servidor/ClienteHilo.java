package org.marco.chat.servidor;

import org.marco.chat.utilidades.FechaUtil;
import java.io.*;
import java.net.Socket;

public class ClienteHilo extends Thread {
    private Socket s;
    private HistorialServidor hist;
    private String nom;

    public ClienteHilo(Socket s, HistorialServidor hist) {
        this.s = s;
        this.hist = hist;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        ) {
            // 1. leemos el nombre que manda el cliente al principio
            String id = in.readLine();
            // 2. checamos si el nombre ya esta en el mapa del server
            if (id == null || id.trim().isEmpty() ||
                    ServidorSocket.clientesMapa.containsKey(id)) {
                // si ya esta pues le mandamos el error pa que lo rebote
                out.println("ERROR: El nombre ya esta ocupado.");
                return;
            }

            // 3. si paso la prueba, le damos el OK pa que entre
            this.nom = id;
            out.println("OK");
            // lo metemos al mapa de la gente conectada
            ServidorSocket.clientesMapa.put(nom, out);
            // avisamos a todos que alguien entro pa que se refresque la lista
            ServidorSocket.notificarActualizacionDeUsuarios();
            // un descanso pa asegurar que a todos les llegue bien la lista
            new Thread(() -> {
                try { Thread.sleep(600); } catch (Exception e) {}
                ServidorSocket.notificarActualizacionDeUsuarios();
            }).start();
            // 4. nos quedamos escuchando lo que escriba el cliente
            String msg;
            while ((msg = in.readLine()) != null) {
                if (msg.startsWith("@")) {
                    mandarPrivado(msg);
                } else {
                    mandarATodos(nom + ": " + msg);
                }
            }
        } catch (Exception e) {
            System.err.println("se fue: " + (nom != null ? nom : "anonimo"));
        } finally {
            // si se sale lo quitamos de la lista pa que no mande errores
            if (nom != null) {
                ServidorSocket.clientesMapa.remove(nom);
                ServidorSocket.notificarActualizacionDeUsuarios();
            }
            try {
                if (!s.isClosed()) s.close();
            } catch (IOException e) {}
        }
    }

    // pa los mensajes que empiezan con @
    private void mandarPrivado(String linea) {
        int espacio = linea.indexOf(" ");
        if (espacio != -1) {
            String destino = linea.substring(1, espacio);
            String texto = linea.substring(espacio + 1);
            PrintWriter pwReceptor = ServidorSocket.clientesMapa.get(destino);

            if (pwReceptor != null) {
                // se lo mandamos al que es
                pwReceptor.println("[Mensaje de " + nom + "]: " + texto);
                // guardamos en el txt de la pareja (aqui se hace el registro unico)
                hist.guardarMensaje(nom, destino, texto);
                // log pal server
                hist.agregarMensaje(nom + " -> " + destino + ": " + texto);
            }
        }
    }

    // pa los mensajes globales
    private void mandarATodos(String textoCompleto) {
        hist.agregarMensaje(textoCompleto);
        for (PrintWriter p : ServidorSocket.clientesMapa.values()) {
            p.println("[" + FechaUtil.getFechaActual() + "] " + textoCompleto);
        }
    }
}
package org.marco.chat.servidor;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServidorSocket {
    private int p;
    private HistorialServidor hist;

    // aqui guardamos a todos los que entran: <Nombre, Salida>
    public static Map<String, PrintWriter> clientesMapa = new ConcurrentHashMap<>();

    public ServidorSocket(int p, HistorialServidor hist) {
        this.p = p;
        this.hist = hist;
    }

    public void iniciar() {
        System.out.println("Servidor prendido en el puerto: " + p);
        try (ServerSocket ss = new ServerSocket(p)) {
            while (true) {
                // esperamos a que alguien se conecte
                Socket s = ss.accept();
                // creamos un hilo pa atender a cada cliente por separado
                new ClienteHilo(s, hist).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // metodo pa avisar a todos quien esta conectado
    public static void notificarActualizacionDeUsuarios() {
        // pasamos por cada cliente pa mandarle la lista
        for (String yo : clientesMapa.keySet()) {
            StringBuilder sb = new StringBuilder();
            // armamos la lista pero quitando el nombre del que la recibe
            for (String user : clientesMapa.keySet()) {
                if (!user.equals(yo)) {
                    if (sb.length() > 0) sb.append(",");
                    sb.append(user);
                }
            }
            // mandamos el comando especial pa que el cliente actualice su panel
            PrintWriter out = clientesMapa.get(yo);
            if (out != null) {
                out.println("LIST_UPDATE:" + sb.toString());
            }
        }
    }
}
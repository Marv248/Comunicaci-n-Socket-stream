package org.marco;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Receptor extends Thread {

    private int puerto;
    private boolean activo = true;

    public Receptor(int puerto) {
        this.puerto = puerto;
    }

    public void detener() {
        activo = false;
        interrupt();
    }

    @Override
    public void run() {
        try {
            DatagramSocket socket = new DatagramSocket(puerto);
            byte[] buffer = new byte[1024];

            System.out.println("📡 Escuchando en puerto " + puerto);

            while (activo) {
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                socket.receive(paquete); // bloqueante

                String mensaje = new String(
                        paquete.getData(), 0, paquete.getLength());

                System.out.println("\n📩 Mensaje recibido: " + mensaje);
                System.out.print("👉 ");
            }

            socket.close();
        } catch (Exception e) {
            if (activo) e.printStackTrace();
        }
    }
}
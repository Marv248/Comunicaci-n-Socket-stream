package org.marco;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Emisor {

    public void emitir(String ip, int puerto, String mensaje) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress destino = InetAddress.getByName(ip);

            byte[] datos = mensaje.getBytes();
            DatagramPacket paquete =
                    new DatagramPacket(datos, datos.length, destino, puerto);

            socket.send(paquete);
            socket.close();
            System.out.println("✅ Mensaje enviado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

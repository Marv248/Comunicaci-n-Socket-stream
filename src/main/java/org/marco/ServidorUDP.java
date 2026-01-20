package org.marco;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.HashMap;

public class ServidorUDP {

    // usuarios -> dirección
    private static HashMap<String, InetSocketAddress> clientes = new HashMap<>();

    public static void main(String[] args) {

        int puertoServidor = 5000;

        System.out.println("🟢 Servidor iniciado en puerto " + puertoServidor);

        try (DatagramSocket socket = new DatagramSocket(puertoServidor)) {

            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                socket.receive(paquete);

                String mensaje = new String(
                        paquete.getData(), 0, paquete.getLength());

                /*
                 * Formato:
                 * REGISTRAR:usuario
                 * MENSAJE:origen:destino:texto
                 */

                String[] partes = mensaje.split(":", 4);

                if (partes[0].equals("REGISTRAR")) {
                    String usuario = partes[1];

                    clientes.put(usuario,
                            new InetSocketAddress(
                                    paquete.getAddress(), paquete.getPort()));

                    System.out.println("✅ Usuario conectado: " + usuario);
                }

                if (partes[0].equals("MENSAJE")) {
                    String origen = partes[1];
                    String destino = partes[2];
                    String texto = partes[3];

                    InetSocketAddress destinoAddr = clientes.get(destino);

                    if (destinoAddr != null) {
                        String reenviar = origen + ": " + texto;

                        DatagramPacket envio = new DatagramPacket(
                                reenviar.getBytes(),
                                reenviar.length(),
                                destinoAddr.getAddress(),
                                destinoAddr.getPort());

                        socket.send(envio);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

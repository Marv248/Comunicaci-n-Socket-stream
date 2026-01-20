package org.marco;

import javax.swing.*;
import java.awt.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteUDP extends JFrame {

    private JTextArea areaChat;
    private JTextField txtMensaje;
    private JButton btnEnviar;

    private DatagramSocket socket;
    private String usuario;
    private String destinatario;

    public ClienteUDP(String usuario) throws Exception {

        this.usuario = usuario;

        socket = new DatagramSocket();

        construirUI();
        iniciarReceptor();
        registrarEnServidor();
    }

    private void construirUI() {

        setTitle("Chat - " + usuario);
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        areaChat = new JTextArea();
        areaChat.setEditable(false);

        txtMensaje = new JTextField();
        btnEnviar = new JButton("Enviar");

        btnEnviar.addActionListener(e -> enviarMensaje());

        add(new JScrollPane(areaChat), BorderLayout.CENTER);

        JPanel panelSur = new JPanel(new BorderLayout());
        panelSur.add(txtMensaje, BorderLayout.CENTER);
        panelSur.add(btnEnviar, BorderLayout.EAST);

        add(panelSur, BorderLayout.SOUTH);
    }

    private void registrarEnServidor() {
        enviarAlServidor("REGISTRAR:" + usuario);
    }

    private void enviarMensaje() {
        String texto = txtMensaje.getText();
        txtMensaje.setText("");

        enviarAlServidor("MENSAJE:" + usuario + ":" + destinatario + ":" + texto);
        areaChat.append("Yo: " + texto + "\n");
    }

    private void enviarAlServidor(String mensaje) {
        try {
            InetAddress serverIP = InetAddress.getByName("127.0.0.1");

            DatagramPacket paquete = new DatagramPacket(
                    mensaje.getBytes(),
                    mensaje.length(),
                    serverIP,
                    5000);

            socket.send(paquete);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void iniciarReceptor() {
        Thread receptor = new Thread(() -> {
            try {
                byte[] buffer = new byte[1024];

                while (true) {
                    DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                    socket.receive(paquete);

                    String msg = new String(
                            paquete.getData(), 0, paquete.getLength());

                    SwingUtilities.invokeLater(() ->
                            areaChat.append(msg + "\n"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        receptor.start();
    }

    public static void main(String[] args) throws Exception {

        String usuario = JOptionPane.showInputDialog("Tu usuario:");
        String destino = JOptionPane.showInputDialog("Usuario destino:");

        ClienteUDP cliente = new ClienteUDP(usuario);
        cliente.destinatario = destino;
        cliente.setVisible(true);
    }
}

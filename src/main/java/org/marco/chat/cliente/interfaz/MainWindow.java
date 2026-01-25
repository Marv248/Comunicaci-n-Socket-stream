package org.marco.chat.cliente.interfaz;

import org.marco.chat.cliente.interfaz.paneles.PanelChat;
import org.marco.chat.cliente.interfaz.paneles.PanelConexion;
import org.marco.chat.modelo.Usuario;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private CardLayout cardLayout;
    private JPanel contenedor;
    private PanelChat panelChat;
    private PanelConexion panelConexion;

    public MainWindow() {
        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);
        panelConexion = new PanelConexion(this);
        panelChat = new PanelChat(this);

        contenedor.add(panelConexion, "Conexion");
        contenedor.add(panelChat, "Chat");
        contenedor.add(new PanelConexion(this), "Conexion");
        contenedor.add(new PanelChat(this), "Chat");

        add(contenedor);
        cardLayout.show(contenedor, "conexion");

        setTitle("Chat Rebollo - Nicolás");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void mostrarChat(Usuario usuario) {
        panelChat.setUsuario(usuario);
        cardLayout.show(contenedor, "Chat");
    }

}

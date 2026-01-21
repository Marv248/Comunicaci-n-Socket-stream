package org.marco.chat.cliente.interfaz;

import org.marco.chat.cliente.interfaz.paneles.PanelChat;
import org.marco.chat.cliente.interfaz.paneles.PanelConexion;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private CardLayout cardLayout;
    private JPanel contenedor;

    public MainWindow() {
        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);

        contenedor.add(new PanelConexion(this), "Conexion");
        contenedor.add(new PanelChat(this), "Chat");

        add(contenedor);
        cardLayout.show(contenedor, "conexion");

        setTitle("Chat Rebollo - Nicolás");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void mostrarChat(){
        cardLayout.show(contenedor, "Chat");
    }
}

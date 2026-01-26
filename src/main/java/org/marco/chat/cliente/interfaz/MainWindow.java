package org.marco.chat.cliente.interfaz;

import org.marco.chat.cliente.interfaz.controladores.ControladorConexion;
import org.marco.chat.cliente.interfaz.paneles.PanelChat;
import org.marco.chat.cliente.interfaz.paneles.PanelConexion;
import org.marco.chat.utilidades.PanelDegradado;
import org.marco.chat.modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {
    private CardLayout cards;
    private PanelDegradado fondo;
    private PanelChat pChat;
    private PanelConexion pCon;

    public MainWindow() {
        setTitle("Chat Rebollo - Nicolás");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        // pa que no se cierre solo, primero preguntamos
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // evento pa cuando le pican a la X de la ventana
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmarSalida();
            }
        });
        cards = new CardLayout();
        fondo = new PanelDegradado();
        fondo.setLayout(cards);
        pChat = new PanelChat(this);
        pCon = new PanelConexion(this);
        // agregamos las pantallas al contenedor
        fondo.add(pCon, "Conexion");
        fondo.add(pChat, "Chat");
        add(fondo);
        cards.show(fondo, "Conexion"); // que empiece en el login
        setVisible(true);
    }

    // metodo pa cambiar a la pantalla del chat
    public void mostrarChat(Usuario u) {
        pChat.setUsuario(u);
        cards.show(fondo, "Chat");
    }

    public PanelChat getPanelChat() {
        return pChat;
    }

    // mensaje de confirmacion pa salir
    public void confirmarSalida() {
        int v = JOptionPane.showConfirmDialog(this,
                "¿Deseas salir del chat?", "Cerrar Sesión", JOptionPane.YES_NO_OPTION);
        if (v == JOptionPane.YES_OPTION) {
            ControladorConexion.cerrarConexion();
            System.exit(0);
        }
    }
}
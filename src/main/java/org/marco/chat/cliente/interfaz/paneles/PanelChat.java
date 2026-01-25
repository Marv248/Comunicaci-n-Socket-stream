package org.marco.chat.cliente.interfaz.paneles;

import org.marco.chat.cliente.interfaz.MainWindow;
import org.marco.chat.modelo.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
///     Panel Conexion
/// Clase encargada de crear un panel Swing para que el usuario pueda interactuar con la UI.
public class PanelChat extends JPanel {

    private JTextArea areaChat;
    private Usuario usuario;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        cargarHistorial();
    }

    private void cargarHistorial() {}

    public PanelChat(MainWindow ventana) {
        // TODO: @cristo1208 métele diseño chido

        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(10, 10, 100));

        areaChat = new JTextArea();
        areaChat.setEditable(false);
        areaChat.setLineWrap(true);
        areaChat.setWrapStyleWord(true);
        areaChat.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        areaChat.setBorder(new EmptyBorder(10,10,10,10));
        areaChat.setOpaque(false);
        areaChat.setBackground(new Color(70, 140, 210));

        JScrollPane scroll = new JScrollPane(areaChat);
        scroll.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBackground(Color.WHITE);
        panelCentro.add(scroll, BorderLayout.CENTER);

        add(panelCentro, BorderLayout.CENTER);
        add(new PanelUsuarios(), BorderLayout.WEST);
        add(new PanelMensaje(this), BorderLayout.SOUTH);
    }

    public void mostrarMensaje(String mensaje) {
        areaChat.append(mensaje + "\n");
        areaChat.setCaretPosition(areaChat.getDocument().getLength());
    }
}
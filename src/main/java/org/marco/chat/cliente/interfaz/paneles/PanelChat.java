package org.marco.chat.cliente.interfaz.paneles;

import org.marco.chat.cliente.interfaz.MainWindow;

import javax.swing.*;
import java.awt.*;
///     Panel Conexion
/// Clase encargada de crear un panel Swing para que el usuario pueda interactuar con la UI.
public class PanelChat extends JPanel {

    private JTextArea areaChat;

    public PanelChat(MainWindow ventana) {
        setLayout(new BorderLayout());

        areaChat = new JTextArea();
        areaChat.setEditable(false);

        add(new JScrollPane(areaChat), BorderLayout.CENTER);
        add(new PanelUsuarios(), BorderLayout.WEST);
        add(new PanelMensaje(this), BorderLayout.SOUTH);
    }

    public void mostrarMensaje(String mensaje) {
        areaChat.append(mensaje + "\n");
    }
}

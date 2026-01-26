package org.marco.chat.cliente.interfaz.paneles;

import org.marco.chat.cliente.interfaz.controladores.ControladorChat;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelMensaje extends JPanel {

    public PanelMensaje(PanelChat chat) {
        // panel invisible pa que se vea el fondo de atras
        setOpaque(false);
        setLayout(new BorderLayout(10, 0));
        setBorder(new EmptyBorder(5, 0, 5, 0));
        // el cuadro pa escribir los mensajes
        JTextField txt = new JTextField();
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        // le ponemos un color oscuro casi solido pa que no se encimen las letras
        txt.setBackground(new Color(10, 20, 40, 250));
        txt.setForeground(Color.WHITE);
        txt.setCaretColor(Color.WHITE);
        txt.setOpaque(true);
        // el borde color cian y un espacio pa que el texto no pegue a la orilla
        txt.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 180, 230), 1),
                BorderFactory.createEmptyBorder(6, 12, 6, 12)));
        // boton pa mandar el texto
        JButton btn = new JButton("ENVIAR");
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBackground(new Color(0, 180, 230));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(100, 38));
        btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        // accion del boton al dar clic
        btn.addActionListener(e -> enviar(txt, chat));
        // pa que tambien mande el mensaje con el enter
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    enviar(txt, chat);
                }
            }
        });

        add(txt, BorderLayout.CENTER);
        add(btn, BorderLayout.EAST);
    }

    // metodo pa procesar el envio
    private void enviar(JTextField t, PanelChat c) {
        String msg = t.getText().trim();
        if (msg.isEmpty()) return; // si no hay nada no hacemos nada
        ControladorChat ctrl = new ControladorChat();
        if (c.getUsuarioActual() == null) return;
        String yo = c.getUsuarioActual().getNombre();
        String para = c.getNombreSeleccionado();
        // mandamos al server el mensaje
        ctrl.enviarMensaje(yo, para, msg);
        // lo ponemos en nuestro cuadro de chat pa verlo
        String prefijo = (para == null || para.equals("TODOS")) ? "Yo: " : "Para " + para + ": ";
        c.mostrarMensaje(prefijo + msg);
        // borramos lo que escribimos y dejamos el foco ahi
        t.setText("");
        t.requestFocus();
    }
}
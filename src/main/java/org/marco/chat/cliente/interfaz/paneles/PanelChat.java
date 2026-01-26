package org.marco.chat.cliente.interfaz.paneles;

import org.marco.chat.cliente.interfaz.MainWindow;
import org.marco.chat.modelo.Usuario;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PanelChat extends JPanel {
    private JTextArea area;
    private JLabel titulo;
    private Usuario userActual;
    private PanelUsuarios listaNombres;
    private String chatCon = "TODOS";

    public PanelChat(MainWindow win) {
        setOpaque(false);
        setLayout(new BorderLayout(15, 15));
        setBorder(new EmptyBorder(20, 20, 20, 20));
        // el texto de arriba que dice con quien hablamos
        titulo = new JLabel("Chateando con: TODOS");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(Color.WHITE);
        add(titulo, BorderLayout.NORTH);

        // panel de la izquierda (la lista y el boton salir)
        JPanel pIzquierdo = new JPanel(new BorderLayout(0, 15));
        pIzquierdo.setOpaque(false);
        pIzquierdo.setPreferredSize(new Dimension(230, 0));

        listaNombres = new PanelUsuarios();
        listaNombres.alSeleccionarUsuario(n -> {
            if (n == null || n.equals("[ Chat Global ]")) {
                if (!chatCon.equals("TODOS")) {
                    chatCon = "TODOS";
                    titulo.setText("Chateando con: TODOS");
                    mostrarMensaje("--- MODO GLOBAL ---");
                }
            } else {
                if (!chatCon.equals(n)) {
                    chatCon = n;
                    titulo.setText("Chateando con: " + n);
                    mostrarMensaje("--- CHAT PRIVADO CON: " + n + " ---");
                }
            }
        });

        JButton btnSalir = new JButton("Cerrar Sesión");
        btnSalir.setBackground(new Color(160, 35, 45));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSalir.setFocusPainted(false);

        pIzquierdo.add(listaNombres, BorderLayout.CENTER);
        pIzquierdo.add(btnSalir, BorderLayout.SOUTH);

        add(pIzquierdo, BorderLayout.WEST);

        // area donde se ven los mensajes y la barra pa escribir
        JPanel centro = new JPanel(new BorderLayout(0, 10));
        centro.setOpaque(false);

        area = new JTextArea();
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        area.setOpaque(false);
        area.setForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(area);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);

        TitledBorder tb = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(255, 255, 255, 80)), "Mensajes");
        tb.setTitleColor(Color.WHITE);
        scroll.setBorder(tb);

        centro.add(scroll, BorderLayout.CENTER);
        centro.add(new PanelMensaje(this), BorderLayout.SOUTH);

        add(centro, BorderLayout.CENTER);

        // accion del boton pa salir
        btnSalir.addActionListener(e -> win.confirmarSalida());
    }

    public Usuario getUsuarioActual() { return userActual; }

    public String getNombreSeleccionado() {
        return listaNombres.getUsuarioSeleccionado();
    }

    public void setUsuario(Usuario u) {
        this.userActual = u;
        area.setText("--- Bienvenido: " + u.getNombre() + " ---\n");
    }

    // pa poner los mensajes en el cuadro
    public void mostrarMensaje(String m) {
        SwingUtilities.invokeLater(() -> {
            area.append(m + "\n");
            area.setCaretPosition(area.getDocument().getLength());
            area.repaint();
        });
    }

    public PanelUsuarios getPanelUsuarios() {
        return listaNombres;
    }
}
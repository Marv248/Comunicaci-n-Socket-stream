package org.marco.chat.cliente.interfaz.paneles;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelUsuarios extends JPanel {
    private DefaultListModel<String> mod;
    private JList<String> lista;

    public PanelUsuarios() {
        // panel invisible pa que luzca el fondo
        setOpaque(false);
        setLayout(new BorderLayout());
        // titulo de la lista
        JLabel titulo = new JLabel("CONECTADOS", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        titulo.setForeground(Color.WHITE);
        titulo.setBorder(new EmptyBorder(10, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);
        mod = new DefaultListModel<>();
        lista = new JList<>(mod);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // le ponemos un poquito de color pa que no se vea el rastro de letras
        lista.setBackground(new Color(255, 255, 255, 10));
        lista.setOpaque(false);
        lista.setForeground(Color.WHITE);
        lista.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lista.setFixedCellHeight(40);
        // color pa cuando seleccionamos a alguien
        lista.setSelectionBackground(new Color(0, 180, 230, 180));
        lista.setSelectionForeground(Color.WHITE);
        JScrollPane scroll = new JScrollPane(lista);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 40)));

        add(scroll, BorderLayout.CENTER);
    }
    // metodo pa que se refresquen los nombres
    public void actualizarLista(String[] users, String yo) {
        SwingUtilities.invokeLater(() -> {
            String sel = lista.getSelectedValue();
            mod.clear();
            mod.addElement("[ Chat Global ]");
            for (String u : users) {
                String nom = u.trim();
                // agregamos a todos menos a ti
                if (!nom.isEmpty() && !nom.equalsIgnoreCase(yo)) {
                    mod.addElement(nom);
                }
            }
            // pa que no se pierda el que teniamos seleccionado
            if (sel != null && mod.contains(sel)) {
                lista.setSelectedValue(sel, true);
            } else {
                lista.setSelectedIndex(0);
            }
            lista.repaint();
        });
    }

    public String getUsuarioSeleccionado() {
        String val = lista.getSelectedValue();
        // si es el global regresamos null pa que sea general
        return (val == null || val.equals("[ Chat Global ]")) ? null : val;
    }

    // pa saber cuando alguien le pica a un nombre
    public void alSeleccionarUsuario(java.util.function.Consumer<String> accion) {
        lista.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                accion.accept(lista.getSelectedValue());
            }
        });
    }
}
package org.marco.chat.cliente.interfaz.paneles;

import org.marco.chat.modelo.Usuario;

import javax.swing.*;

///     Controlador Chat
/// Clase encargada de crear el panel lateral de usuarios donde se mostrarán los usuarios.

public class PanelUsuarios extends JPanel {

    private JList<Usuario> lista;

    public PanelUsuarios() {
        lista = new JList<>();
        add(new JScrollPane(lista));
    }

    public Usuario getUsuarioSeleccionado() {
        return lista.getSelectedValue();
    }
}

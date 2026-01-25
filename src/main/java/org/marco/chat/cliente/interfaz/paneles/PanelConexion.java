package org.marco.chat.cliente.interfaz.paneles;

import org.marco.chat.cliente.interfaz.MainWindow;
import org.marco.chat.cliente.interfaz.controladores.ControladorConexion;
import org.marco.chat.modelo.Usuario;

import javax.swing.*;

///     Panel Conexion
/// Clase encargada de crear un panel Swing para que el usuario pueda ingresar al sistema su IP; Puerto y Nombre

public class PanelConexion extends JPanel {

    public PanelConexion(MainWindow ventana) {
        JTextField txtIp = new JTextField(15);
        JTextField txtPuerto = new JTextField(5);
        JTextField txtNombre = new JTextField(10);
        JButton btnConectar = new JButton("Conectar");

        btnConectar.addActionListener(e -> {
            Usuario usuario;
            if (txtIp.getText().equals("") || txtPuerto.getText().equals("") || txtNombre.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese el IP del servidor", "Ingrese todos los valores", JOptionPane.WARNING_MESSAGE);
                return;
            } else {
                usuario = new Usuario(txtNombre.getText(), txtIp.getText(), Integer.parseInt(txtPuerto.getText()));
                JOptionPane.showMessageDialog(ventana, "Inicio exitoso", "Inicio exitoso", JOptionPane.INFORMATION_MESSAGE);
            }

            if (ControladorConexion.conectar(txtIp.getText(), Integer.parseInt(txtPuerto.getText()), txtNombre.getText())) // Pasa al controlador (intermediario) la IP; puerto y nombre del usuario
                ventana.mostrarChat(usuario); // Cambiar panel de conexion a chat
            else
                JOptionPane.showMessageDialog(null, "Algo salió mal al conectarse al servidor", "No se pudo conectar al servidor", JOptionPane.WARNING_MESSAGE);
        });

        add(new JLabel("IP del servidor:"));
        add(txtIp);

        add(new JLabel("Puerto del servidor:"));
        add(txtPuerto);

        add(new JLabel("Usuario:"));
        add(txtNombre);

        add(btnConectar);
    }
}

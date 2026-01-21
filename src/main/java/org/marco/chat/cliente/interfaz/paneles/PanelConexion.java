package org.marco.chat.cliente.interfaz.paneles;

import org.marco.chat.cliente.interfaz.MainWindow;
import org.marco.chat.cliente.interfaz.controladores.ControladorConexion;

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
            if (!txtIp.getText().isEmpty() & !txtPuerto.getText().isEmpty() & !txtNombre.getText().isEmpty())
                JOptionPane.showMessageDialog(null, "Por favor, ingrese el IP del servidor", "Ingrese todos los valores", JOptionPane.WARNING_MESSAGE);

            ControladorConexion.conectar(txtIp.getText(), Integer.parseInt(txtPuerto.getText()), txtNombre.getText()); // Pasa al controlador (intermediario) la IP; puerto y nombre del usuario
            ventana.mostrarChat(); // Cambiar panel de conexion a chat
        });

        add(new JLabel("IP"));
        add(txtIp);

        add(new JLabel("Puerto"));
        add(txtPuerto);

        add(new JLabel("Usuario"));
        add(txtNombre);

        add(btnConectar);
    }
}

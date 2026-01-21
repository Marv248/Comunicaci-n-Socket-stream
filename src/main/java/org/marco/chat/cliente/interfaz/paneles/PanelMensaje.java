package org.marco.chat.cliente.interfaz.paneles;

import org.marco.chat.cliente.interfaz.controladores.ControladorChat;

import javax.swing.*;

public class PanelMensaje extends JPanel {

    public PanelMensaje(PanelChat chat) {
        JTextField txtMensaje = new JTextField(40);
        JButton btnEnviar = new JButton("Enviar");

        btnEnviar.addActionListener(e -> {
            if(txtMensaje.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Debe ingresar texto", "Llene el mensaje", JOptionPane.WARNING_MESSAGE);
            }
//            ControladorChat.enviarMensaje(txtMensaje.getText()); TODO: IMPLEMENTAR MÉTODO enviarMensaje()
            txtMensaje.setText("");
        });

        add(txtMensaje);
        add(btnEnviar);
    }
}

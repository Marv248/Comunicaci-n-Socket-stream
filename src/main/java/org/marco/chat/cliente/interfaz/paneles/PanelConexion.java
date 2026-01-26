package org.marco.chat.cliente.interfaz.paneles;

import org.marco.chat.cliente.interfaz.MainWindow;
import org.marco.chat.cliente.interfaz.controladores.ControladorConexion;
import org.marco.chat.modelo.Usuario;
import javax.swing.*;
import java.awt.*;

public class PanelConexion extends JPanel {

    public PanelConexion(MainWindow win) {
        setOpaque(false);
        setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10, 10, 10, 10);
        g.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("CHAT REBOLLO Y NICOLÁS", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titulo.setForeground(Color.WHITE);

        JTextField tIp = new JTextField("127.0.0.1", 15);
        JTextField tPort = new JTextField("1234", 15);
        JTextField tNom = new JTextField(15);

        // les damos estilo pa que no se vean feos
        estilo(tIp);
        estilo(tPort);
        estilo(tNom);

        JButton btn = new JButton("ENTRAR");
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(new Color(0, 180, 230));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(120, 45));
        btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        // acomodamos todo el relajo en el layout
        g.gridx = 0; g.gridy = 0; g.gridwidth = 2;
        g.insets = new Insets(0, 0, 50, 0);
        add(titulo, g);

        g.gridwidth = 1; g.insets = new Insets(8, 10, 8, 10);
        g.gridx = 0; g.gridy = 1; add(label("IP Servidor:"), g);
        g.gridx = 1; add(tIp, g);

        g.gridx = 0; g.gridy = 2; add(label("Puerto:"), g);
        g.gridx = 1; add(tPort, g);

        g.gridx = 0; g.gridy = 3; add(label("Tu Nombre:"), g);
        g.gridx = 1; add(tNom, g);

        g.gridx = 0; g.gridy = 4; g.gridwidth = 2;
        g.insets = new Insets(30, 0, 0, 0);
        add(btn, g);

        // logica pa conectar al chat
        btn.addActionListener(e -> {
            String ip = tIp.getText().trim();
            String pStr = tPort.getText().trim();
            String nom = tNom.getText().trim();

            if (ip.isEmpty() || pStr.isEmpty() || nom.isEmpty()) {
                JOptionPane.showMessageDialog(this, "llena todo pa poder entrar", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                int p = Integer.parseInt(pStr);

                // checamos si el server nos deja entrar con ese nombre
                if (ControladorConexion.conectar(ip, p, nom, win.getPanelChat())) {
                    Usuario u = new Usuario(nom, ip, p);
                    win.mostrarChat(u);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "el puerto ocupa ser numero", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // metodo pa crear los labels rapido
    private JLabel label(String t) {
        JLabel l = new JLabel(t);
        l.setForeground(new Color(200, 230, 255));
        l.setFont(new Font("Segoe UI", Font.BOLD, 14));
        return l;
    }

    // pa que los campos se vean igual
    private void estilo(JTextField c) {
        c.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        c.setBackground(new Color(255, 255, 255, 235));
        c.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 180, 230), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
    }
}
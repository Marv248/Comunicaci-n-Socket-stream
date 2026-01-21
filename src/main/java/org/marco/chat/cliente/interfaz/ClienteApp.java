package org.marco.chat.cliente.interfaz;

import javax.swing.*;

public class ClienteApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new MainWindow();
        });
    }
}

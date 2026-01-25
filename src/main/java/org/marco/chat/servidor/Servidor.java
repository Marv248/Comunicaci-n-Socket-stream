package org.marco.chat.servidor;

import javax.swing.*;

public class Servidor {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String input = JOptionPane.showInputDialog(null, "Ingrese el puerto del servidor:",  "Servidor de Chat", JOptionPane.QUESTION_MESSAGE);

            if (input == null) {
                System.exit(0);
            }

            int puerto = Integer.parseInt(input);

            HistorialServidor historialServidor = new HistorialServidor(); // TODO: CARGAR AL HISTORIAL ACTUAL ACUMULADO EN DOCS

            ServidorSocket servidor = new ServidorSocket(puerto, historialServidor);
            // El servidor corre en otro hilo
            new Thread(servidor::iniciar).start();
        });
    }
}

package org.marco.chat.utilidades;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class PanelDegradado extends JPanel {

    public PanelDegradado() {
        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // pa que se vea mas suave el dibujo
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int ancho = getWidth();
        int alto = getHeight();

        // el centro del brillo un poco arriba del medio
        Point2D centro = new Point2D.Float(ancho / 2, alto / 2.5f);
        float radio = Math.max(ancho, alto) * 0.8f;
        float[] fraccion = {0.0f, 0.5f, 1.0f};

        // los colores azules pa que se vea chido
        Color[] colores = {
                new Color(0, 180, 230), // cian
                new Color(0, 70, 130),  // azul
                new Color(5, 10, 25)    // casi negro
        };

        // pintamos el fondo con el degradado circular
        RadialGradientPaint p = new RadialGradientPaint(centro, radio, fraccion, colores);
        g2.setPaint(p);
        g2.fillRect(0, 0, ancho, alto);
    }
}
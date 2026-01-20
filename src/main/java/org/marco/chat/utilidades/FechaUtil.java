package org.marco.chat.utilidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FechaUtil {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String getFechaActual() {
        return LocalDateTime.now().format(formatter);
    }
}

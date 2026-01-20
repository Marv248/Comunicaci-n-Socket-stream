package org.marco.chat.cliente;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HistorialCliente {
    private List<String > mensajes = new ArrayList<>();

    public void agregarMensaje(String mensaje){
        mensajes.add(mensaje);
    }
    public void gerDoc(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/lebir/Downloads/conversacion.txt"))) {
            for(int i = 0; i < mensajes.size(); i++){
                bw.write(mensajes.get(i));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

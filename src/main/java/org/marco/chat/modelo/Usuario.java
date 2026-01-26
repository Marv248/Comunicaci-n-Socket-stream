package org.marco.chat.modelo;

public class Usuario {

    private String nombre;
    private String direccionIp;
    private int puerto;

    public Usuario(String nombre, String direccionIp, int puerto) {
        this.nombre = nombre;
        this.direccionIp = direccionIp;
        this.puerto = puerto;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDireccionIp() {
        return direccionIp;
    }
    public void setDireccionIp(String direccionIp) {
        this.direccionIp = direccionIp;
    }
    public int getPuerto() {
        return puerto;
    }
    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }
}

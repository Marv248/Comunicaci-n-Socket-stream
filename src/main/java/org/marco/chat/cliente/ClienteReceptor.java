package org.marco.chat.cliente;

import java.net.Socket;

public class ClienteReceptor extends Thread{
    private Socket socket;
    public ClienteReceptor(Socket socket) {
        this.socket = socket;
    }

    public void run() {

    }
}

package org.marco;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("Puerto local (escucha): ");
        int puertoLocal = Integer.parseInt(scanner.nextLine());

        System.out.print("IP del receptor: ");
        String ipReceptor = scanner.nextLine();

        System.out.print("Puerto del receptor: ");
        int puertoReceptor = Integer.parseInt(scanner.nextLine());

        Emisor emisor = new Emisor();

        // 🔥 HILO RECEPTOR
        Receptor receptor = new Receptor(puertoLocal);
        receptor.start();

        boolean continuar = true;

        while (continuar) {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Enviar mensaje");
            System.out.println("2. Cambiar destinatario");
            System.out.println("3. Salir");
            System.out.print("Opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    System.out.print("Mensaje: ");
                    String msg = scanner.nextLine();
                    emisor.emitir(ipReceptor, puertoReceptor, msg);
                    break;

                case "2":
                    System.out.print("Nueva IP: ");
                    ipReceptor = scanner.nextLine();

                    System.out.print("Nuevo puerto: ");
                    puertoReceptor = Integer.parseInt(scanner.nextLine());
                    break;

                case "3":
                    continuar = false;
                    receptor.detener();
                    System.out.println("👋 Cerrando aplicación");
                    break;

                default:
                    System.out.println("❌ Opción inválida");
            }
        }

        scanner.close();
    }
}
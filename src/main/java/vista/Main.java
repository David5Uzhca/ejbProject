/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

/**
 *
 * @author Dell
 */

import controlador.MensajeEJB;
import modelo.Mensaje;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            InitialContext ctx = new InitialContext();
            MensajeEJB mensajeEJB = (MensajeEJB) ctx.lookup("java:global/ejbproject/MensajeEJB");

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("1. Enviar mensaje");
                System.out.println("2. Ver mensajes");
                System.out.println("3. Salir");
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                if (opcion == 1) {
                    System.out.print("Remitente: ");
                    String remitente = scanner.nextLine();
                    System.out.print("Destinatario: ");
                    String destinatario = scanner.nextLine();
                    System.out.print("Mensaje: ");
                    String contenido = scanner.nextLine();

                    Mensaje mensaje = new Mensaje();
                    mensaje.setRemitente(remitente);
                    mensaje.setDestinatario(destinatario);
                    mensaje.setContenido(contenido);
                    mensajeEJB.enviarMensaje(mensaje);

                } else if (opcion == 2) {
                    mensajeEJB.obtenerMensajes().forEach(m -> {
                        System.out.println(m.getRemitente() + " -> " + m.getDestinatario() + ": " + m.getContenido());
                    });
                } else if (opcion == 3) {
                    break;
                } else {
                    System.out.println("Opción inválida");
                }
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}

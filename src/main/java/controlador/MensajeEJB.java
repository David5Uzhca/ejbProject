/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author Dell
 */

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import modelo.Mensaje;
import java.util.List;

@Stateless
public class MensajeEJB {
    @PersistenceContext(unitName = "mensajeriaPU")
    private EntityManager em;

    public void enviarMensaje(Mensaje mensaje) {
        em.persist(mensaje);
        System.out.println("Mensaje enviado: " + mensaje.getContenido());
    }

    public List<Mensaje> obtenerMensajes() {
        return em.createQuery("SELECT m FROM Mensaje m", Mensaje.class).getResultList();
    }
}

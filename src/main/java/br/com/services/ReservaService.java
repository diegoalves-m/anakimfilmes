/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.services;

import edu.ifpb.dac.Cliente;
import edu.ifpb.dac.Locacao;
import edu.ifpb.dac.Reserva;
import ifpb.dac.service.InterfaceReserva;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Diego Alves
 */
@Stateless
@Remote(InterfaceReserva.class)
public class ReservaService implements InterfaceReserva {

    @PersistenceContext(unitName = "anakim")
    EntityManager em;

    @Override
    public void salvar(Reserva reserva) {
        em.persist(reserva);
    }

    @Override
    public void atualizar(Reserva reserva) {
        em.merge(reserva);
    }

    @Override
    public List<Reserva> listaTodas() {
        TypedQuery query = em.createQuery("select r from Reserva r", Locacao.class);
        return query.getResultList();
    }

    @Override
    public void remover(Reserva reserva) {
        em.remove(em.merge(reserva));
    }

    @Override
    public Reserva getReserva(int idLocacao) {
        try {
            Query query = em.createQuery("select r from Reserva r where r.Locacao.id_locacao = :idLocacao");
            query.setParameter("idLocacao", idLocacao);
            Reserva reserva = (Reserva) query.getSingleResult();
            return reserva;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Cliente getCliente(int idReserva) {
        Query query = em.createQuery("select c from Reserva r join r.clientes c where r.id_reserva = :idReserva");
        query.setParameter("idReserva", idReserva);
        Cliente cliente = (Cliente) query.getSingleResult();
        return cliente;
    }

}

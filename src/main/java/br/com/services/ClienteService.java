
package br.com.services;

import edu.ifpb.dac.Cliente;
import ifpb.dac.service.InterfaceCliente;
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
@Remote(InterfaceCliente.class)
public class ClienteService implements InterfaceCliente {
    
    @PersistenceContext(unitName = "anakim")
    EntityManager em;

    @Override
    public void salvar(Cliente cliente) {
        em.persist(cliente);
    }

    @Override
    public void atualizar(Cliente cliente) {
        em.merge(cliente);
    }

    @Override
    public List<Cliente> listaTodos() {
        TypedQuery query = em.createQuery("select c from Cliente c", Cliente.class);
        return query.getResultList();
    }

    @Override
    public void remover(Cliente cliente) {
         em.remove(em.merge(cliente));
    }

    @Override
    public Cliente getCliente(int id) {
        return em.find(Cliente.class, id);
    }

    @Override
    public List<Cliente> getPesquisa(String where, String pesquisa) {
        Query query = em.createQuery("select c from Cliente c where c."+where+" like '"+pesquisa+"%'");
        List<Cliente> lista = query.getResultList();
        return lista;
    }
    
    @Override
    public Cliente zeraPontosCliente(int idCliente) {
        Cliente c = getCliente(idCliente);
        c.setPontos(0);
        return c;
    }
    
    @Override
    public Cliente adicionaPontosCliente(int idCliente) {
        Cliente c = getCliente(idCliente);
        c.setPontos(c.getPontos() + 10);
        return c;
    }

    
}

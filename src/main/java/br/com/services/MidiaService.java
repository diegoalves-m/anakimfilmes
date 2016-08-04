package br.com.services;

import edu.ifpb.dac.Filme;
import edu.ifpb.dac.Midia;
import ifpb.dac.service.InterfaceMidia;
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
@Remote(InterfaceMidia.class)
public class MidiaService implements InterfaceMidia {

    @PersistenceContext(unitName = "anakim")
    EntityManager em;

    @Override
    public void salvar(Midia midia) {
        em.persist(midia);
    }

    @Override
    public void atualizar(Midia midia) {
        em.merge(midia);
    }

    @Override
    public List<Midia> listaTodos() {
        TypedQuery query = em.createQuery("select m from Midia m", Midia.class);
        return query.getResultList();
    }

    @Override
    public void remover(Midia midia) {
        em.remove(em.merge(midia));
    }

    @Override
    public List<Filme> getFilmes() {
        Query query = em.createQuery("select f from Filme f");
        List<Filme> filmes = query.getResultList();
        return filmes;
    }

    @Override
    public Midia getMidia(int idFilme) {
        try {
            Query query = em.createQuery("select m from Filme f join f.midia m where f.id_filme = :idFilme");
            query.setParameter("idFilme", idFilme);
            Midia m = (Midia) query.getSingleResult();
            return m;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Filme> getPesquisa(String where, String pesquisa) {
        Query query = em.createQuery("select f from Filme f where f." + where + " like '" + pesquisa + "%'");
        List<Filme> lista = query.getResultList();
        return lista;
    }

    @Override
    public Filme getFilme(int id) {
        return em.find(Filme.class, id);
    }

    @Override
    public List<Filme> filmesReserva() {
        Query query = em.createQuery("select f from Filme f where f.reservado = true");
        List<Filme> lista = query.getResultList();
        return lista;
    }

    @Override
    public List<Filme> filmesReservaPesquisa(String pesquisa) {
        Query query = em.createQuery("select f from Filme f where f.reservado = true and f.nome like '" + pesquisa + "%'");
        List<Filme> lista = query.getResultList();
        return lista;
    }

}

package br.com.services;

import edu.ifpb.dac.Cliente;
import edu.ifpb.dac.Locacao;
import ifpb.dac.service.InterfaceLocacao;
import java.util.Date;
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
@Remote(InterfaceLocacao.class)
public class LocacaoService implements InterfaceLocacao {

    @PersistenceContext(unitName = "anakim")
    EntityManager em;

    @Override
    public void salvar(Locacao locacao) {
        em.merge(locacao);
    }

    @Override
    public void atualizar(Locacao locacao) {
        em.merge(locacao);
    }

    @Override
    public List<Locacao> listaTodos() {
        TypedQuery query = em.createQuery("select l from Locacao l", Locacao.class);
        return query.getResultList();
    }

    @Override
    public void remover(Locacao locacao) {
        em.remove(em.merge(locacao));
    }

    @Override
    public List<Cliente> listClienteComLocacao() {
        try {
            Query query = em.createQuery("select distinct l.cliente from Locacao l");
            List<Cliente> l = query.getResultList();
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Locacao> getLocacaoIdCliente(int idCliente) {
        try {
            Query query = em.createQuery("select l from Locacao l where l.cliente.id_pessoa = :idCliente");
            query.setParameter("idCliente", idCliente);
            List<Locacao> locacoes = query.getResultList();
            return locacoes;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Locacao getLocacao(int idMidia) {
        try {
            Query query = em.createQuery("select l from Midia m join m.locacoes l where m.id_midia = :idMidia");
            query.setParameter("idMidia", idMidia);
            Locacao l = (Locacao) query.getSingleResult();
            return l;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Locacao getLocacaoIdClienteUnique(int idCliente) {
        try {
            Query query = em.createQuery("select l from Locacao l where l.cliente.id_pessoa = :idCliente");
            query.setParameter("idCliente", idCliente);
            Locacao locacao = (Locacao) query.getSingleResult();
            return locacao;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Locacao> getPesquisaData(Date data, String where) {
        Query query = em.createQuery("select l from Locacao l where l." + where + " = :data");
        query.setParameter("data", data);
        List<Locacao> lista = query.getResultList();
        return lista;
    }
}

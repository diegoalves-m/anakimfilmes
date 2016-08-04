/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.services;

import edu.ifpb.dac.Funcionario;
import ifpb.dac.service.InterfaceFuncionario;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Diego Alves
 */
@Stateless
@Remote(InterfaceFuncionario.class)
public class FuncionarioService implements InterfaceFuncionario {

    @PersistenceContext(unitName = "anakim")
    EntityManager em;

    @Override
    public void salvar(Funcionario funcionario) {
        em.persist(funcionario);
    }

    @Override
    public void atualizar(Funcionario funcionario) {
        em.merge(funcionario);
    }

    @Override
    public List<Funcionario> listaTodos() {
        TypedQuery query = em.createQuery("select f from Funcionario f", Funcionario.class);
        return query.getResultList();
    }

    @Override
    public void remover(Funcionario funcionario) {
        em.remove(em.merge(funcionario));
    }

    @Override
    public Funcionario autenticar(Funcionario funcionario) {
        try {
            Query query = em.createQuery("SELECT f FROM Funcionario f WHERE f.email = :email AND f.senha = :senha");
            query.setParameter("email", funcionario.getEmail());
            query.setParameter("senha", funcionario.getSenha());
            Funcionario func = (Funcionario) query.getSingleResult();
            return func;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Funcionario getFuncionario(int id) {
        return em.find(Funcionario.class, id);
    }

    @Override
    public List<Funcionario> getPesquisa(String where, String pesquisa) {
        Query query = em.createQuery("select f from Funcionario f where f." + where + " like '" + pesquisa + "%'");
        List<Funcionario> lista = query.getResultList();
        return lista;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controllers;

import edu.ifpb.dac.Categoria;
import edu.ifpb.dac.Filme;
import edu.ifpb.dac.Locacao;
import edu.ifpb.dac.Midia;
import ifpb.dac.service.InterfaceLocacao;
import ifpb.dac.service.InterfaceMidia;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Diego Alves
 */
@Named(value = "controladorMidia")
@SessionScoped
public class ControladorMidia implements Serializable {

    private Categoria categoria;
    private Filme filme;
    private Midia midia;
    private List<Filme> listaFilmes;
    private boolean editando;
    private boolean status;
    @EJB
    private InterfaceMidia interfaceMidia;
    @EJB
    private InterfaceLocacao interfaceLocacao;

    public ControladorMidia() {
        categoria = new Categoria();
        filme = new Filme();
        midia = new Midia();
        listaFilmes = new ArrayList<>();
        editando = false;
    }

    public boolean isStatus() {
        return status;
    }

    public boolean isEditando() {
        return editando;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Midia getMidia() {
        return midia;
    }

    public void setMidia(Midia midia) {
        this.midia = midia;
    }

    public String salvarMidia() {
        filme.setCategoria(categoria);
        midia.setFilme(filme);
        interfaceMidia.salvar(midia);
        filme = new Filme();
        categoria = new Categoria();
        midia = new Midia();
        return null;
    }

    public List<Filme> getListFilmes() {
        if (interfaceMidia.getFilmes().isEmpty()) {
            FacesMessage facesMessage = new FacesMessage("Nenhum registro de filme.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, facesMessage);
        } else {
            listaFilmes = interfaceMidia.getFilmes();
            return listaFilmes;
        }
        return null;
    }

    public String atualizarFilme(int idFilme) {
        this.midia = interfaceMidia.getMidia(idFilme);
        this.filme = this.midia.getFilme();
        this.categoria = this.filme.getCategoria();
        editando = true;
        return "filmes.xhtml";
    }

    public String enviaAtualizacao() {
        interfaceMidia.atualizar(midia);
        FacesMessage facesMessage = new FacesMessage("Atualizado com sucesso!");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, facesMessage);
        editando = false;
        midia = new Midia();
        categoria = new Categoria();
        filme = new Filme();
        return "gerenciamentoFilmes.xhtml";
    }

    public String remover(int idFilme) {
        midia = interfaceMidia.getMidia(idFilme);
        interfaceMidia.remover(midia);
        return null;
    }

    public String disponivel(boolean status) {
        if(status == true) {
            return "disponivel";
        } else {
            return "indisponivel";
        }
    }

    public String vereificaLocacao(int idFilme) {
        midia = interfaceMidia.getMidia(idFilme);
        String disponibilidade;
        if (interfaceLocacao.getLocacao(midia.getId_midia()) == null) {
            disponibilidade = "SIM";
            status = true;
            disponivel(status);
        } else {
            disponibilidade = "NÃO";
            status = false;
            disponivel(status);
        }
        return disponibilidade;
    }

}

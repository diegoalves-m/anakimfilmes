/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controllers;

import br.com.util.CreateHash;
import edu.ifpb.dac.Funcionario;
import ifpb.dac.service.InterfaceFuncionario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Diego Alves
 */
@Named(value = "controladorFuncionario")
@SessionScoped
public class ControladorFuncionario implements Serializable {

    private Funcionario funcionario;
    private boolean editando;
    @EJB
    private InterfaceFuncionario interfaceFuncionario;

    public ControladorFuncionario() {
        funcionario = new Funcionario();
        editando = false;
    }

    public boolean isEditando() {
        return editando;
    }

    public void setEditando(boolean editando) {
        this.editando = editando;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String autenticar() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        funcionario.setSenha(CreateHash.hashMDK5(funcionario.getSenha()));
        if (interfaceFuncionario.autenticar(funcionario) != null) {
            funcionario = new Funcionario();
            return "gerenciamento.xhtml";       
        } else {
            FacesMessage facesMessage = new FacesMessage("Autenticação inválida");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, facesMessage);
            return null;
        }
    }

    public String salvarFunc() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (!funcionario.getNome().isEmpty() || funcionario.getIdade() != 0 || !funcionario.getEmail().isEmpty()
                || !funcionario.getCpf().isEmpty()) {
            String senha = funcionario.getSenha();
            funcionario.setSenha(CreateHash.hashMDK5(senha));
            interfaceFuncionario.salvar(funcionario);
            FacesMessage facesMessage = new FacesMessage("Funcionário salvo.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, facesMessage);
            funcionario = new Funcionario();
            return null;
        } else {
            FacesMessage facesMessage = new FacesMessage("Existe algum campo em branco.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, facesMessage);
        }
        return null;
    }

    public List<Funcionario> listaTodos() {
        return interfaceFuncionario.listaTodos();
    }

    public String atualizarFunc(Funcionario func) {
        funcionario = func;
        editando = true;
        return "funcionarios.xhtml";
    }

    public String enviarAtualizacao() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String senha = funcionario.getSenha();
        funcionario.setSenha(CreateHash.hashMDK5(senha));
        interfaceFuncionario.atualizar(funcionario);
        FacesMessage facesMessage = new FacesMessage("Atualizado com sucesso!");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, facesMessage);
        funcionario = new Funcionario();
        editando = false;
        return "gerenciamentoFuncionarios.xhtml";
    }

    public String remover(Funcionario funcionario) {
        interfaceFuncionario.remover(funcionario);
        FacesMessage facesMessage = new FacesMessage("Removido com sucesso!");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, facesMessage);
        return null;
    }

    public String sair() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        funcionario = new Funcionario();
        session.invalidate();
        return "index.xhtml";
    }

}

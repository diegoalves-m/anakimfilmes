/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controllers;

import edu.ifpb.dac.Cliente;
import edu.ifpb.dac.Endereco;
import ifpb.dac.service.InterfaceCliente;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Diego Alves
 */
@Named(value = "controladorCliente")
@SessionScoped
public class ControladorCliente implements Serializable {

    private Cliente cliente;
    private Endereco endereco;
    private boolean editando;
    @EJB
    private InterfaceCliente interfaceCliente;

    public ControladorCliente() {
        cliente = new Cliente();
        endereco = new Endereco();
        editando = false;
    }

    public boolean isEditando() {
        return editando;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String salvarCliente() {
        if (!cliente.getNome().isEmpty() || !cliente.getCpf().isEmpty() || !endereco.getCidade().isEmpty()) {
            cliente.setEndereco(endereco);
            interfaceCliente.salvar(cliente);
            FacesMessage facesMessage = new FacesMessage("Cliente salvo.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, facesMessage);
            cliente = new Cliente();
            endereco = new Endereco();
            return null;
        } else {
            FacesMessage facesMessage = new FacesMessage("Existe algum campo em branco.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, facesMessage);
        }
        return null;
    }

    public List<Cliente> listaTodos() {
        return interfaceCliente.listaTodos();
    }

    public String atualizaCliente(Cliente cliente) {
        this.cliente = cliente;
        this.endereco = cliente.getEndereco();
        editando = true;
        return "clientes.xhtml";
    }

    public String enviaAtulizacao() {
        cliente.setEndereco(endereco);
        interfaceCliente.atualizar(cliente);
        FacesMessage facesMessage = new FacesMessage("Atualizado com sucesso!");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, facesMessage);
        editando = false;
        cliente = new Cliente();
        endereco = new Endereco();
        return "gerenciamentoClientes.xhtml";
    }

    public String remover(Cliente cliente) {
        interfaceCliente.remover(cliente);
        FacesMessage facesMessage = new FacesMessage("Removido com sucesso!");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, facesMessage);
        return null;
    }

}

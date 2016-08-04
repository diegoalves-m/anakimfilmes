/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controllers;

import br.com.util.DateController;
import edu.ifpb.dac.Cliente;
import edu.ifpb.dac.Filme;
import edu.ifpb.dac.Locacao;
import edu.ifpb.dac.Midia;
import ifpb.dac.service.InterfaceCliente;
import ifpb.dac.service.InterfaceLocacao;
import ifpb.dac.service.InterfaceMidia;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
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
@Named(value = "controladorLocacao")
@SessionScoped
public class ControladorLocacao implements Serializable {

    private Cliente cliente;
    private Midia midia;
    private Locacao locacao;
    private List<Cliente> listClientes;
    private double valor = 3.00;
    private int defaultDias = 1;
    private String dataLocacao;
    private String dataDevolucao;
    private double diaAdicional = 1.25;
    private double desconto = 2.00;
    private double MULTA = 2.00;

    @EJB
    private InterfaceLocacao interfaceLocacao;
    @EJB
    private InterfaceMidia interfaceMidia;
    @EJB
    private InterfaceCliente interfaceCliente;

    public ControladorLocacao() {
        midia = new Midia();
        locacao = new Locacao();
        listClientes = new ArrayList<>();
        dataDevolucao = DateController.dataDevolucao(defaultDias);
        dataLocacao = DateController.dataAtual();
    }

    public double getDesconto() {
        return desconto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente c) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("cliente", c);
    }

    public Midia getMidia() {
        return midia;
    }

    public void setMidia(Midia midia) {
        this.midia = midia;
    }

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    public List<Cliente> getListClientes() {
        listClientes = interfaceCliente.listaTodos();
        return listClientes;
    }

    public void setListClientes(List<Cliente> listClientes) {
        this.listClientes = listClientes;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao() {
        this.dataLocacao = DateController.dataAtual();
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao() {
        this.dataDevolucao = DateController.dataDevolucao(defaultDias);
    }

    public void adicionaDia() {
        defaultDias = defaultDias + 1;
        dataDevolucao = DateController.dataDevolucao(defaultDias);
        valor = valor + diaAdicional;
    }

    public String andamentoLocacao(Filme filme) {
        midia = interfaceMidia.getMidia(filme.getId_filme());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("filme", midia);
        return "finalizarLocacao.xhtml";
    }

    public String finalizaLocacao() throws ParseException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        cliente = (Cliente) session.getAttribute("cliente");
        locacao.setCliente(cliente);
        locacao.setDataEmprestimo(DateController.dataFormat(DateController.formatEua(dataLocacao)));
        locacao.setDataDevolucao(DateController.dataFormat(DateController.formatEua(dataDevolucao)));
        HttpSession session1 = request.getSession();
        session1.getAttribute("filme");
        locacao.setMidia((Midia) session1.getAttribute("filme"));
        locacao.setValor(valor);
        interfaceLocacao.salvar(locacao);
        midia = new Midia();
        locacao = new Locacao();
        dataDevolucao = DateController.dataDevolucao(defaultDias);
        dataLocacao = DateController.dataAtual();
        
        return "gerenciamento.xhtml";
    }

    public List<Cliente> getClientesComLocacoes() {
        return interfaceLocacao.listClienteComLocacao();
    }

    public List<Locacao> getListaLocacoesPorCliente() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session1 = request.getSession();
        int idCliente = (int) session1.getAttribute("id_loc");
        return interfaceLocacao.getLocacaoIdCliente(idCliente);
    }

    public String setIdSessao(int id) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("id_loc", id);
        return "locacoesCliente.xhtml";
    }

    public void devoulucao(Locacao locacao) {
        this.locacao = locacao;
        this.cliente = locacao.getCliente();
        this.valor = locacao.getValor();
        LocalDate dataLocacaoAux = DateController.dateToLocalDate(locacao.getDataEmprestimo());
        LocalDate dataDevolucao_ = DateController.dateToLocalDate(locacao.getDataDevolucao());
        LocalDate diaDeHoje = DateController.stringToDate(DateController.dataAtual());
        Period qtdDiasNoPrazo = Period.between(dataLocacaoAux, dataDevolucao_);
        Period qtdDiasDevolucao = Period.between(dataLocacaoAux, diaDeHoje);
        if (qtdDiasDevolucao.getDays() <= qtdDiasNoPrazo.getDays()) {
            if (interfaceCliente.getCliente(cliente.getId_pessoa()).getPontos() == 100) {
                double valorAtual = this.valor;
                this.valor = (valorAtual - desconto);
                Cliente cliRet = interfaceCliente.zeraPontosCliente(cliente.getId_pessoa());
                interfaceCliente.atualizar(cliRet);
                interfaceLocacao.remover(locacao);
                FacesMessage facesMessage = new FacesMessage("Devolvido com sucesso, desconto concedido valor a pagar: " + valor + " R$");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, facesMessage);
            }
            Cliente clieRet = interfaceCliente.adicionaPontosCliente(cliente.getId_pessoa());
            interfaceCliente.atualizar(clieRet);
            interfaceLocacao.remover(locacao);
            FacesMessage facesMessage = new FacesMessage("Devolvido com sucesso, valor a pagar: " + valor + " R$");
            FacesMessage facesMessage2 = new FacesMessage("O cliente recebeu +10 pontos no programa da AnakimFilmes");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, facesMessage);
            context.addMessage(null, facesMessage2);
            valor = 3.00;
        }else {
            int diasAdiconaisDeMulta = qtdDiasDevolucao.getDays() - qtdDiasNoPrazo.getDays();
            double valorAtual =  this.valor;
            this.valor = (valorAtual + diasAdiconaisDeMulta * MULTA);
            Cliente clieRet = interfaceCliente.zeraPontosCliente(cliente.getId_pessoa());
            interfaceCliente.atualizar(clieRet);
            interfaceLocacao.remover(locacao);
            FacesMessage facesMessage = new FacesMessage("Devolvido com sucesso, valor a pagar com multa: " + valor + " R$");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, facesMessage);
            valor = 3.00;
        }
    }
    
    
    public String cancelar() {
        midia = new Midia();
        locacao = new Locacao();
        listClientes = new ArrayList<>();
        dataDevolucao = DateController.dataDevolucao(defaultDias);
        dataLocacao = DateController.dataAtual();
        return "gerenciamento.xhtml";
    }

}

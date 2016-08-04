package edu.ifpb.dac;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Diego Alves
 */
@Entity
public class Cliente extends Pessoa implements Serializable {

    private static final long serialVersionUID = -9211215732776319258L;
    
    @Column(nullable = false)
    private int pontos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<Locacao> locacoes;
    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;
    @ManyToMany(mappedBy = "clientes")
    private List<Reserva> reservas;

    public Cliente() {
    }

    public Cliente(int pontos, List<Locacao> locacoes, Endereco endereco, List<Reserva> reservas) {
        this.pontos = pontos;
        this.locacoes = locacoes;
        this.endereco = endereco;
        this.reservas = reservas;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public List<Locacao> getLocacoes() {
        return locacoes;
    }

    public void setLocacoes(List<Locacao> locacoes) {
        this.locacoes = locacoes;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
    
    
}

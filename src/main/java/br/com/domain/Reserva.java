package edu.ifpb.dac;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Diego Alves
 */
@Entity
public class Reserva implements Serializable {
    
    private static final long serialVersionUID = -5179869610537923481L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_reserva;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Locacao Locacao;
    @ManyToMany
    private List<Cliente> clientes;

    public Reserva() {
    }

    public Reserva(int id_reserva, Locacao Locacao, List<Cliente> clientes) {
        this.id_reserva = id_reserva;
        this.Locacao = Locacao;
        this.clientes = clientes;
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public Locacao getLocacao() {
        return Locacao;
    }

    public void setLocacao(Locacao Locacao) {
        this.Locacao = Locacao;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}

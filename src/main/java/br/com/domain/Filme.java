package edu.ifpb.dac;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Diego Alves
 */
@Entity
public class Filme implements Serializable {
    
    private static final long serialVersionUID = -6091748604927258370L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_filme;
    @Column(nullable = false, length = 30)
    private String nome;
    @Column(nullable = false)
    private boolean reservado;
    @Column(nullable = false, length = 255)
    private String sinopse;
    @Temporal(TemporalType.DATE)
    private Date anoLancamento;
    @ManyToOne(cascade = CascadeType.ALL)
    private Categoria categoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "filme")
    private List<Midia> midia;

    public Filme() {
    }

    public Filme(int id_filme, String nome, boolean reservado, String sinopse, Date anoLancamento, Categoria categoria, List<Midia> midia) {
        this.id_filme = id_filme;
        this.nome = nome;
        this.reservado = reservado;
        this.sinopse = sinopse;
        this.anoLancamento = anoLancamento;
        this.categoria = categoria;
        this.midia = midia;
    }

    public int getId_filme() {
        return id_filme;
    }

    public void setId_filme(int id_filme) {
        this.id_filme = id_filme;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public Date getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Date anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Midia> getMidia() {
        return midia;
    }

    public void setMidia(List<Midia> midia) {
        this.midia = midia;
    }
    
    
}

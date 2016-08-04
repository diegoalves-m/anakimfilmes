package edu.ifpb.dac;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Diego Alves
 */
@Entity
public class Midia implements Serializable {
    
    private static final long serialVersionUID = -541709919855748077L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_midia;
    @Column(nullable = false, length = 100)
    private String situacao;
    @Column(nullable = false, length = 15)
    private String tipo;
    @ManyToOne(cascade = CascadeType.ALL)
    private Filme filme;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "midia")
    private List<Locacao> locacoes;

    public Midia() {
    }

    public Midia(int id_midia, String situacao, String tipo, Filme filme, List<Locacao> locacoes) {
        this.id_midia = id_midia;
        this.situacao = situacao;
        this.tipo = tipo;
        this.filme = filme;
        this.locacoes = locacoes;
    }

    public int getId_midia() {
        return id_midia;
    }

    public void setId_midia(int id_midia) {
        this.id_midia = id_midia;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public List<Locacao> getLocacacoes() {
        return locacoes;
    }

    public void setLocacacoes(List<Locacao> locacoes) {
        this.locacoes = locacoes;
    }

}

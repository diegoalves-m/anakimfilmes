package edu.ifpb.dac;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Diego Alves
 */
@Entity
public class Categoria implements Serializable {
    
    private static final long serialVersionUID = 4400438799074441529L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_categoria;
    @Column(nullable = false, length = 100)
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria")
    private List<Filme> filmes;

    public Categoria() {
    }

    public Categoria(int id_categoria, String descricao, List<Filme> filmes) {
        this.id_categoria = id_categoria;
        this.descricao = descricao;
        this.filmes = filmes;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    public void setFilmes(List<Filme> filmes) {
        this.filmes = filmes;
    }
    

}

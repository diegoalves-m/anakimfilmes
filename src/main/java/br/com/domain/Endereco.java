package edu.ifpb.dac;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Diego Alves
 */
@Entity
public class Endereco implements Serializable {

    private static final long serialVersionUID = 2323241239087333796L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_endereco;
    @Column(nullable = false, length = 2)
    private String estado;
    @Column(nullable = false, length = 60)
    private String cidade;
    @Column(nullable = false, length = 60)
    private String bairro;
    @Column(nullable = false, length = 100)
    private String rua;
    @Column(nullable = false)
    private int numero;
    @Column(nullable = false, length = 30)
    private String pontoReferencia;
    @Column(nullable = false, length = 15)
    private String cep;

    public Endereco() {
    }

    public Endereco(int id_endereco, String estado, String cidade, String bairro, String rua,
            int numero, String pontoReferencia, String cep) {
        this.id_endereco = id_endereco;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.pontoReferencia = pontoReferencia;
        this.cep = cep;
    }

    public int getId_endereco() {
        return id_endereco;
    }

    public void setId_endereco(int id_endereco) {
        this.id_endereco = id_endereco;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
}

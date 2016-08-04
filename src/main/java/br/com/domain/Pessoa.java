package edu.ifpb.dac;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Diego Alves
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Pessoa implements Serializable {
    
    private static final long serialVersionUID = -1401131696289150437L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_pessoa;
    @Column(nullable = false, length = 100)
    private String nome;
    @Column(nullable = false)
    private int idade;
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @Column(nullable = false, length = 11)
    private String sexo;
    @Column(nullable = false, length = 15)
    private String cpf;
    @Column(nullable = false, length = 100)
    private String email;
    @Column(length = 15)
    private String telefone;

    public Pessoa() {
    }

    public Pessoa(int id_pessoa, String nome, int idade, Date dataNascimento, String sexo, String cpf, String email, String telefone) {
        this.id_pessoa = id_pessoa;
        this.nome = nome;
        this.idade = idade;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    public int getId_pessoa() {
        return id_pessoa;
    }

    public void setId_pessoa(int id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }   
    
}

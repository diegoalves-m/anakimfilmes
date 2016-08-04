package edu.ifpb.dac;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author Diego Alves
 */
@Entity
public class Funcionario extends Pessoa implements Serializable {
    
     private static final long serialVersionUID = 3643045244945894908L;

    @Column(nullable = false, length = 255)
    private String senha;

    public Funcionario() {
    }

    public Funcionario(String senha, int id_pessoa, String nome, int idade, Date dataNascimento, String sexo, String cpf, String email, String telefone) {
        super(id_pessoa, nome, idade, dataNascimento, sexo, cpf, email, telefone);
        this.senha = senha;
    }


    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.dac.service;

import edu.ifpb.dac.Funcionario;
import java.util.List;

/**
 *
 * @author Diego Alves
 */
public interface InterfaceFuncionario {
    
    public void salvar(Funcionario funcionario);

    public void atualizar(Funcionario funcionario);

    public List<Funcionario> listaTodos();

    public void remover(Funcionario funcionario);

    public Funcionario autenticar(Funcionario funcionario);
    
    public Funcionario getFuncionario(int id);
    
    public List<Funcionario> getPesquisa(String where, String pesquisa);
     
}

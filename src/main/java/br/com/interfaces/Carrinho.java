/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.dac.service;

import edu.ifpb.dac.Cliente;
import edu.ifpb.dac.Pessoa;
import java.util.List;

/**
 *
 * @author Ricardo Job
 */
public interface Carrinho {

    public void add(String produto);

    public void finalizar();
    
    public void salvarPessoa(Pessoa pessoa);
    
    public List<Cliente> listaClientes();

}

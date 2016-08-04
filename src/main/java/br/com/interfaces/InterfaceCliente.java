package ifpb.dac.service;

import edu.ifpb.dac.Cliente;
import java.util.List;

/**
 *
 * @author Diego Alves
 */
public interface InterfaceCliente {

    public void salvar(Cliente cliente);

    public void atualizar(Cliente cliente);

    public List<Cliente> listaTodos();

    public void remover(Cliente cliente);

    public Cliente getCliente(int id);

    public List<Cliente> getPesquisa(String where, String pesquisa);

    public Cliente zeraPontosCliente(int idCliente);

    public Cliente adicionaPontosCliente(int idCliente);

}

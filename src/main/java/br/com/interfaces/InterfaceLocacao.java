
package ifpb.dac.service;

import edu.ifpb.dac.Cliente;
import edu.ifpb.dac.Locacao;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Diego Alves
 */
public interface InterfaceLocacao {
    
    public void salvar(Locacao locacao);

    public void atualizar(Locacao locacao);

    public List<Locacao> listaTodos();

    public void remover(Locacao locacao);
    
    public List<Cliente> listClienteComLocacao();
    
    public List<Locacao> getLocacaoIdCliente(int idCliente);
    
    public Locacao getLocacao(int idMidia);
    
    public Locacao getLocacaoIdClienteUnique(int idCliente);
    
    public List<Locacao> getPesquisaData(Date data, String where);
    
}

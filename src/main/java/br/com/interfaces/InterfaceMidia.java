
package ifpb.dac.service;

import edu.ifpb.dac.Filme;
import edu.ifpb.dac.Midia;
import java.util.List;

/**
 *
 * @author Diego Alves
 */
public interface InterfaceMidia {
    
     public void salvar(Midia midia);

    public void atualizar(Midia midia);

    public List<Midia> listaTodos();

    public void remover(Midia midia);
    
    public List<Filme> getFilmes();
    
    public Midia getMidia(int idFilme);
    
    public List<Filme> getPesquisa(String where, String pesquisa);

    public Filme getFilme(int id);

    public List<Filme> filmesReserva();

    public List<Filme> filmesReservaPesquisa(String pesquisa);

}

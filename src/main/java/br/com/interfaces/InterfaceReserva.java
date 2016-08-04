/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.dac.service;

import edu.ifpb.dac.Cliente;
import edu.ifpb.dac.Reserva;
import java.util.List;

/**
 *
 * @author Diego Alves
 */
public interface InterfaceReserva {
    
    public void salvar(Reserva reserva);

    public void atualizar(Reserva reserva);

    public List<Reserva> listaTodas();

    public void remover(Reserva reserva);

    public Reserva getReserva(int idLocacao);
    
    public Cliente getCliente(int idReserva);
    
}

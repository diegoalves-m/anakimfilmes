package edu.ifpb.dac;

import edu.ifpb.dac.Cliente;
import edu.ifpb.dac.Locacao;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-03T21:24:14")
@StaticMetamodel(Reserva.class)
public class Reserva_ { 

    public static volatile SingularAttribute<Reserva, Locacao> Locacao;
    public static volatile SingularAttribute<Reserva, Integer> id_reserva;
    public static volatile ListAttribute<Reserva, Cliente> clientes;

}
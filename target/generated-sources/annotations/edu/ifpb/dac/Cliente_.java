package edu.ifpb.dac;

import edu.ifpb.dac.Endereco;
import edu.ifpb.dac.Locacao;
import edu.ifpb.dac.Reserva;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-03T21:24:14")
@StaticMetamodel(Cliente.class)
public class Cliente_ extends Pessoa_ {

    public static volatile SingularAttribute<Cliente, Endereco> endereco;
    public static volatile ListAttribute<Cliente, Reserva> reservas;
    public static volatile SingularAttribute<Cliente, Integer> pontos;
    public static volatile ListAttribute<Cliente, Locacao> locacoes;

}
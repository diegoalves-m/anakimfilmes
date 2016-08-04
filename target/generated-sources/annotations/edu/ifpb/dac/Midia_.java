package edu.ifpb.dac;

import edu.ifpb.dac.Filme;
import edu.ifpb.dac.Locacao;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-03T21:24:14")
@StaticMetamodel(Midia.class)
public class Midia_ { 

    public static volatile SingularAttribute<Midia, String> situacao;
    public static volatile SingularAttribute<Midia, String> tipo;
    public static volatile SingularAttribute<Midia, Integer> id_midia;
    public static volatile ListAttribute<Midia, Locacao> locacoes;
    public static volatile SingularAttribute<Midia, Filme> filme;

}
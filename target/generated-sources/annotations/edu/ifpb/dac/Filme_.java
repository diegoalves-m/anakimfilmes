package edu.ifpb.dac;

import edu.ifpb.dac.Categoria;
import edu.ifpb.dac.Midia;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-03T21:24:14")
@StaticMetamodel(Filme.class)
public class Filme_ { 

    public static volatile SingularAttribute<Filme, Boolean> reservado;
    public static volatile SingularAttribute<Filme, String> sinopse;
    public static volatile ListAttribute<Filme, Midia> midia;
    public static volatile SingularAttribute<Filme, Date> anoLancamento;
    public static volatile SingularAttribute<Filme, Categoria> categoria;
    public static volatile SingularAttribute<Filme, Integer> id_filme;
    public static volatile SingularAttribute<Filme, String> nome;

}
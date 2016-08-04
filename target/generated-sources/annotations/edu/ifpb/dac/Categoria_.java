package edu.ifpb.dac;

import edu.ifpb.dac.Filme;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-03T21:24:14")
@StaticMetamodel(Categoria.class)
public class Categoria_ { 

    public static volatile SingularAttribute<Categoria, Integer> id_categoria;
    public static volatile ListAttribute<Categoria, Filme> filmes;
    public static volatile SingularAttribute<Categoria, String> descricao;

}
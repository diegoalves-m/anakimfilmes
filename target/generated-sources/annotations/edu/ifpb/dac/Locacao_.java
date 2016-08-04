package edu.ifpb.dac;

import edu.ifpb.dac.Cliente;
import edu.ifpb.dac.Midia;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-03T21:24:14")
@StaticMetamodel(Locacao.class)
public class Locacao_ { 

    public static volatile SingularAttribute<Locacao, Cliente> cliente;
    public static volatile SingularAttribute<Locacao, Midia> midia;
    public static volatile SingularAttribute<Locacao, Date> dataEmprestimo;
    public static volatile SingularAttribute<Locacao, Double> valor;
    public static volatile SingularAttribute<Locacao, Integer> id_locacao;
    public static volatile SingularAttribute<Locacao, Date> dataDevolucao;

}
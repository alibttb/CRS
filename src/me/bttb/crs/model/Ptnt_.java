package me.bttb.crs.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-04-08T11:45:36.645+0300")
@StaticMetamodel(Ptnt.class)
public class Ptnt_ {
	public static volatile SingularAttribute<Ptnt, Long> prsnPrsnPi;
	public static volatile SingularAttribute<Ptnt, Prsn> prsn;
	public static volatile ListAttribute<Ptnt, Visit> visits;
}

package me.bttb.crs.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-05-01T22:14:49.302+0300")
@StaticMetamodel(Measurment.class)
public class Measurment_ {
	public static volatile SingularAttribute<Measurment, Long> id;
	public static volatile SingularAttribute<Measurment, String> description;
	public static volatile SingularAttribute<Measurment, String> name;
	public static volatile SingularAttribute<Measurment, String> notes;
	public static volatile ListAttribute<Measurment, TkMsrmnt> tkMsrmntList;
}

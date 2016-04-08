package me.bttb.crs.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-04-08T11:45:36.641+0300")
@StaticMetamodel(Measurment.class)
public class Measurment_ {
	public static volatile SingularAttribute<Measurment, Long> msrmntId;
	public static volatile SingularAttribute<Measurment, String> describtion;
	public static volatile SingularAttribute<Measurment, String> name;
	public static volatile SingularAttribute<Measurment, String> notes;
	public static volatile ListAttribute<Measurment, TkMsrmnt> tkMsrmnts;
}

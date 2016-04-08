package me.bttb.crs.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-04-08T11:45:36.636+0300")
@StaticMetamodel(Diaganosi.class)
public class Diaganosi_ {
	public static volatile SingularAttribute<Diaganosi, Long> dgnssId;
	public static volatile SingularAttribute<Diaganosi, String> code;
	public static volatile SingularAttribute<Diaganosi, String> describtion;
	public static volatile SingularAttribute<Diaganosi, String> dgnssType;
	public static volatile SingularAttribute<Diaganosi, String> name;
	public static volatile ListAttribute<Diaganosi, DiaganosedWith> diaganosedWiths;
}

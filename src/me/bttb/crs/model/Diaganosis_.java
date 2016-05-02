package me.bttb.crs.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-04-24T22:49:20.436+0300")
@StaticMetamodel(Diaganosis.class)
public class Diaganosis_ {
	public static volatile SingularAttribute<Diaganosis, Long> id;
	public static volatile SingularAttribute<Diaganosis, String> code;
	public static volatile SingularAttribute<Diaganosis, String> describtion;
	public static volatile SingularAttribute<Diaganosis, String> dgnssType;
	public static volatile SingularAttribute<Diaganosis, String> name;
	public static volatile ListAttribute<Diaganosis, DiaganosedWith> diaganosedWithList;
}

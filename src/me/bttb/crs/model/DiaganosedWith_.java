package me.bttb.crs.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-04-08T11:45:36.633+0300")
@StaticMetamodel(DiaganosedWith.class)
public class DiaganosedWith_ {
	public static volatile SingularAttribute<DiaganosedWith, DiaganosedWithPK> id;
	public static volatile SingularAttribute<DiaganosedWith, String> notes;
	public static volatile SingularAttribute<DiaganosedWith, String> severity;
	public static volatile SingularAttribute<DiaganosedWith, Diaganosi> diaganosi;
	public static volatile SingularAttribute<DiaganosedWith, Visit> visit;
}

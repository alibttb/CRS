package me.bttb.crs.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-05-06T19:52:51.775+0300")
@StaticMetamodel(DiaganosedWith.class)
public class DiaganosedWith_ {
	public static volatile SingularAttribute<DiaganosedWith, Long> id;
	public static volatile SingularAttribute<DiaganosedWith, String> notes;
	public static volatile SingularAttribute<DiaganosedWith, String> severity;
	public static volatile SingularAttribute<DiaganosedWith, Diaganosis> diaganosis;
	public static volatile SingularAttribute<DiaganosedWith, Visit> visit;
}

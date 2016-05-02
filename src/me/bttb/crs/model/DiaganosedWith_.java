package me.bttb.crs.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-04-24T22:49:20.434+0300")
@StaticMetamodel(DiaganosedWith.class)
public class DiaganosedWith_ {
	public static volatile SingularAttribute<DiaganosedWith, DiaganosedWithPK> id;
	public static volatile SingularAttribute<DiaganosedWith, String> notes;
	public static volatile SingularAttribute<DiaganosedWith, String> severity;
	public static volatile SingularAttribute<DiaganosedWith, Diaganosis> diaganosis;
	public static volatile SingularAttribute<DiaganosedWith, Visit> visit;
}

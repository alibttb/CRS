package me.bttb.crs.model;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-04-08T11:45:36.648+0300")
@StaticMetamodel(TreatedWith.class)
public class TreatedWith_ {
	public static volatile SingularAttribute<TreatedWith, TreatedWithPK> id;
	public static volatile SingularAttribute<TreatedWith, BigDecimal> dosage;
	public static volatile SingularAttribute<TreatedWith, BigDecimal> durationDays;
	public static volatile SingularAttribute<TreatedWith, String> notes;
	public static volatile SingularAttribute<TreatedWith, String> repetation;
	public static volatile SingularAttribute<TreatedWith, String> unit;
	public static volatile SingularAttribute<TreatedWith, Treatment> treatment;
	public static volatile SingularAttribute<TreatedWith, Visit> visit;
}

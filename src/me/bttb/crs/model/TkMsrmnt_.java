package me.bttb.crs.model;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-05-06T19:54:16.111+0300")
@StaticMetamodel(TkMsrmnt.class)
public class TkMsrmnt_ {
	public static volatile SingularAttribute<TkMsrmnt, Long> id;
	public static volatile SingularAttribute<TkMsrmnt, String> notes;
	public static volatile SingularAttribute<TkMsrmnt, BigDecimal> numricalValue;
	public static volatile SingularAttribute<TkMsrmnt, String> textualValue;
	public static volatile SingularAttribute<TkMsrmnt, Measurment> measurment;
	public static volatile SingularAttribute<TkMsrmnt, Visit> visit;
}

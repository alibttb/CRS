package me.bttb.crs.model;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-04-08T11:45:36.640+0300")
@StaticMetamodel(Lctn.class)
public class Lctn_ {
	public static volatile SingularAttribute<Lctn, Long> locId;
	public static volatile SingularAttribute<Lctn, String> altittude;
	public static volatile SingularAttribute<Lctn, BigDecimal> level;
	public static volatile SingularAttribute<Lctn, String> locationString;
	public static volatile SingularAttribute<Lctn, String> magnitude;
	public static volatile SingularAttribute<Lctn, String> name;
	public static volatile SingularAttribute<Lctn, BigDecimal> superId;
	public static volatile ListAttribute<Lctn, ContactInfo> contactInfos;
}

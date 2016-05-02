package me.bttb.crs.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-04-24T22:49:20.449+0300")
@StaticMetamodel(Usr.class)
public class Usr_ extends Prsn_ {
	public static volatile SingularAttribute<Usr, byte[]> hashSha256;
	public static volatile SingularAttribute<Usr, String> role;
	public static volatile SingularAttribute<Usr, String> salt;
	public static volatile SingularAttribute<Usr, String> userName;
	public static volatile SingularAttribute<Usr, Date> workStartDate;
	public static volatile SingularAttribute<Usr, String> email;
}

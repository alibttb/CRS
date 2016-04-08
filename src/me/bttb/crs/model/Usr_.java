package me.bttb.crs.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-04-08T11:45:36.651+0300")
@StaticMetamodel(Usr.class)
public class Usr_ {
	public static volatile SingularAttribute<Usr, Long> prsnPi;
	public static volatile SingularAttribute<Usr, String> role;
	public static volatile SingularAttribute<Usr, String> usrHashSha256;
	public static volatile SingularAttribute<Usr, String> usrSalt;
	public static volatile SingularAttribute<Usr, String> usrUserName;
	public static volatile SingularAttribute<Usr, Date> usrWorkStartDate;
	public static volatile SingularAttribute<Usr, Dctr> dctr;
	public static volatile SingularAttribute<Usr, Nr> nr;
	public static volatile SingularAttribute<Usr, Prsn> prsn;
}

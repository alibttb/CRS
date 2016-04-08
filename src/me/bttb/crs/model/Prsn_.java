package me.bttb.crs.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-04-08T11:45:36.644+0300")
@StaticMetamodel(Prsn.class)
public class Prsn_ {
	public static volatile SingularAttribute<Prsn, Long> prsnPi;
	public static volatile SingularAttribute<Prsn, Date> prsnBirthDate;
	public static volatile SingularAttribute<Prsn, String> prsnBirthPlace;
	public static volatile SingularAttribute<Prsn, String> prsnFamilyName;
	public static volatile SingularAttribute<Prsn, String> prsnFatherName;
	public static volatile SingularAttribute<Prsn, String> prsnFirstName;
	public static volatile SingularAttribute<Prsn, String> prsnMotherFamilyName;
	public static volatile SingularAttribute<Prsn, String> prsnMotherName;
	public static volatile SingularAttribute<Prsn, String> type;
	public static volatile ListAttribute<Prsn, ContactInfo> contactInfos;
	public static volatile SingularAttribute<Prsn, Ptnt> ptnt;
	public static volatile SingularAttribute<Prsn, Usr> usr;
}

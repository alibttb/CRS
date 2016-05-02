package me.bttb.crs.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-04-24T22:49:20.442+0300")
@StaticMetamodel(Prsn.class)
public class Prsn_ {
	public static volatile SingularAttribute<Prsn, Long> pid;
	public static volatile SingularAttribute<Prsn, Date> birthDate;
	public static volatile SingularAttribute<Prsn, String> birthPlace;
	public static volatile SingularAttribute<Prsn, String> familyName;
	public static volatile SingularAttribute<Prsn, String> fatherName;
	public static volatile SingularAttribute<Prsn, String> firstName;
	public static volatile SingularAttribute<Prsn, String> motherFamilyName;
	public static volatile SingularAttribute<Prsn, String> motherName;
	public static volatile SingularAttribute<Prsn, String> prsnType;
	public static volatile ListAttribute<Prsn, ContactInfo> contactInfoList;
}

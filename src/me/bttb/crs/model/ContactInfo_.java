package me.bttb.crs.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-04-24T22:49:20.202+0300")
@StaticMetamodel(ContactInfo.class)
public class ContactInfo_ {
	public static volatile SingularAttribute<ContactInfo, Long> id;
	public static volatile SingularAttribute<ContactInfo, String> email;
	public static volatile SingularAttribute<ContactInfo, String> phone;
	public static volatile SingularAttribute<ContactInfo, String> type;
	public static volatile SingularAttribute<ContactInfo, Lctn> lctn;
	public static volatile SingularAttribute<ContactInfo, Prsn> prsn;
}

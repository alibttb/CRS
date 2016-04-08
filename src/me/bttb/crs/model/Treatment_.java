package me.bttb.crs.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-04-08T11:45:36.650+0300")
@StaticMetamodel(Treatment.class)
public class Treatment_ {
	public static volatile SingularAttribute<Treatment, Long> trtmntId;
	public static volatile SingularAttribute<Treatment, String> description;
	public static volatile SingularAttribute<Treatment, String> name;
	public static volatile SingularAttribute<Treatment, String> type;
	public static volatile ListAttribute<Treatment, TreatedWith> treatedWiths;
}

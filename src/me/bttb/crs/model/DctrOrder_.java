package me.bttb.crs.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-04-08T11:45:36.631+0300")
@StaticMetamodel(DctrOrder.class)
public class DctrOrder_ {
	public static volatile SingularAttribute<DctrOrder, Long> rdrId;
	public static volatile SingularAttribute<DctrOrder, String> description;
	public static volatile SingularAttribute<DctrOrder, String> name;
	public static volatile SingularAttribute<DctrOrder, String> notes;
	public static volatile SingularAttribute<DctrOrder, String> type;
	public static volatile SingularAttribute<DctrOrder, Visit> visit;
	public static volatile ListAttribute<DctrOrder, Document> documents;
}

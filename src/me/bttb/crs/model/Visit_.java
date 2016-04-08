package me.bttb.crs.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-04-08T11:45:36.652+0300")
@StaticMetamodel(Visit.class)
public class Visit_ {
	public static volatile SingularAttribute<Visit, Long> vstId;
	public static volatile SingularAttribute<Visit, String> notes;
	public static volatile SingularAttribute<Visit, Date> visitDate;
	public static volatile SingularAttribute<Visit, String> vistType;
	public static volatile ListAttribute<Visit, DctrOrder> dctrOrders;
	public static volatile ListAttribute<Visit, DiaganosedWith> diaganosedWiths;
	public static volatile ListAttribute<Visit, Document> documents;
	public static volatile ListAttribute<Visit, HasSymptom> hasSymptoms;
	public static volatile ListAttribute<Visit, TkMsrmnt> tkMsrmnts;
	public static volatile ListAttribute<Visit, TreatedWith> treatedWiths;
	public static volatile SingularAttribute<Visit, Ptnt> ptnt;
}

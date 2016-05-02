package me.bttb.crs.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-04-24T22:49:20.450+0300")
@StaticMetamodel(Visit.class)
public class Visit_ {
	public static volatile SingularAttribute<Visit, Long> id;
	public static volatile SingularAttribute<Visit, String> notes;
	public static volatile SingularAttribute<Visit, Date> vstDate;
	public static volatile SingularAttribute<Visit, String> vstType;
	public static volatile ListAttribute<Visit, DctrOrder> dctrOrders;
	public static volatile ListAttribute<Visit, DiaganosedWith> diaganosedWithList;
	public static volatile ListAttribute<Visit, Document> documents;
	public static volatile ListAttribute<Visit, HasSymptom> hasSymptomList;
	public static volatile ListAttribute<Visit, TkMsrmnt> tkMsrmntList;
	public static volatile ListAttribute<Visit, TreatedWith> treatedWithList;
	public static volatile SingularAttribute<Visit, Ptnt> ptnt;
}

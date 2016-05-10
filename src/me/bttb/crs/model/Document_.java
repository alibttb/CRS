package me.bttb.crs.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-05-10T20:23:44.864+0300")
@StaticMetamodel(Document.class)
public class Document_ {
	public static volatile SingularAttribute<Document, Long> id;
	public static volatile SingularAttribute<Document, byte[]> content;
	public static volatile SingularAttribute<Document, String> docType;
	public static volatile SingularAttribute<Document, String> name;
	public static volatile SingularAttribute<Document, String> notes;
	public static volatile SingularAttribute<Document, DctrOrder> dctrOrder;
	public static volatile SingularAttribute<Document, Visit> visit;
	public static volatile SingularAttribute<Document, String> description;
}

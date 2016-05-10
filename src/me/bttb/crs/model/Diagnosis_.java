package me.bttb.crs.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-05-09T09:50:17.882+0300")
@StaticMetamodel(Diagnosis.class)
public class Diagnosis_ {
	public static volatile SingularAttribute<Diagnosis, Long> id;
	public static volatile SingularAttribute<Diagnosis, String> code;
	public static volatile SingularAttribute<Diagnosis, String> describtion;
	public static volatile SingularAttribute<Diagnosis, String> dgnssType;
	public static volatile SingularAttribute<Diagnosis, String> name;
	public static volatile ListAttribute<Diagnosis, DiagnosedWith> diaganosedWithList;
}

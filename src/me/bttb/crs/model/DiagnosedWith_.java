package me.bttb.crs.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-05-09T09:49:00.286+0300")
@StaticMetamodel(DiagnosedWith.class)
public class DiagnosedWith_ {
	public static volatile SingularAttribute<DiagnosedWith, Long> id;
	public static volatile SingularAttribute<DiagnosedWith, String> notes;
	public static volatile SingularAttribute<DiagnosedWith, String> severity;
	public static volatile SingularAttribute<DiagnosedWith, Diagnosis> diagnosis;
	public static volatile SingularAttribute<DiagnosedWith, Visit> visit;
}

package me.bttb.crs.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-04-24T22:49:20.444+0300")
@StaticMetamodel(Symptom.class)
public class Symptom_ {
	public static volatile SingularAttribute<Symptom, Long> id;
	public static volatile SingularAttribute<Symptom, String> description;
	public static volatile SingularAttribute<Symptom, String> name;
	public static volatile SingularAttribute<Symptom, String> notes;
	public static volatile ListAttribute<Symptom, HasSymptom> hasSymptomList;
}

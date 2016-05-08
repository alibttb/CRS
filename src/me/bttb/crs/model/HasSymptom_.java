package me.bttb.crs.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-05-06T19:53:45.761+0300")
@StaticMetamodel(HasSymptom.class)
public class HasSymptom_ {
	public static volatile SingularAttribute<HasSymptom, Long> id;
	public static volatile SingularAttribute<HasSymptom, String> notes;
	public static volatile SingularAttribute<HasSymptom, String> repetation;
	public static volatile SingularAttribute<HasSymptom, String> severity;
	public static volatile SingularAttribute<HasSymptom, Date> startDate;
	public static volatile SingularAttribute<HasSymptom, Symptom> symptom;
	public static volatile SingularAttribute<HasSymptom, Visit> visit;
}

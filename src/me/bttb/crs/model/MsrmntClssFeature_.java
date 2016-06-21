package me.bttb.crs.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="٢٠١٦-٠٦-٢١T٠١:١٢:٥٦.٢٩٦+0300")
@StaticMetamodel(MsrmntClssFeature.class)
public class MsrmntClssFeature_ {
	public static volatile SingularAttribute<MsrmntClssFeature, Integer> id;
	public static volatile SingularAttribute<MsrmntClssFeature, Symptom> symptom;
	public static volatile SingularAttribute<MsrmntClssFeature, Double> repeat;
	public static volatile SingularAttribute<MsrmntClssFeature, Double> severity;
	public static volatile SingularAttribute<MsrmntClssFeature, Integer> timesAppeard;
	public static volatile SingularAttribute<MsrmntClssFeature, MsrmntClss> mc;
}

package me.bttb.crs.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="٢٠١٦-٠٦-٢١T٠١:١٢:٥٦.٢٣٣+0300")
@StaticMetamodel(MsrmntClss.class)
public class MsrmntClss_ {
	public static volatile SingularAttribute<MsrmntClss, Integer> id;
	public static volatile SingularAttribute<MsrmntClss, Integer> countOfVisits;
	public static volatile ListAttribute<MsrmntClss, MsrmntClssCmpnnt> cmpnnts;
	public static volatile ListAttribute<MsrmntClss, MsrmntClssFeature> features;
}

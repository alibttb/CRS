package me.bttb.crs.model;

import static javax.persistence.GenerationType.TABLE;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.ALL;

@Entity
public class MsrmntClss implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = TABLE)
	private Integer id;
	private int countOfVisits;
	@OneToMany(mappedBy = "mc", fetch = EAGER, cascade = { PERSIST, REMOVE, MERGE, ALL })
	private List<MsrmntClssCmpnnt> cmpnnts;

	@OneToMany(mappedBy = "mc", fetch = EAGER, cascade = { PERSIST, REMOVE, MERGE, ALL })
	private List<MsrmntClssFeature> features;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return "MC" + id;
	}

	public List<MsrmntClssCmpnnt> getCmpnnts() {
		return cmpnnts;
	}

	public void setCmpnnts(List<MsrmntClssCmpnnt> cmpnnts) {
		this.cmpnnts = cmpnnts;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MsrmntClss) {
			MsrmntClss mc = (MsrmntClss) obj;
			return mc.cmpnnts.containsAll(this.cmpnnts) && this.cmpnnts.containsAll(mc.cmpnnts);
		} else
			return super.equals(obj);
	}

	public List<MsrmntClssFeature> getFeatures() {
		return features;
	}

	public void setFeatures(List<MsrmntClssFeature> features) {
		this.features = features;
	}

	public int getCountOfVisits() {
		return countOfVisits;
	}

	public void setCountOfVisits(int countOfVisits) {
		this.countOfVisits = countOfVisits;
	}

	public void incrementCountOfVisits() {
		countOfVisits++;
	}
}

package me.bttb.crs.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the HAS_SYMPTOMS database table.
 * 
 */
@Embeddable
public class HasSymptomPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="VST_ID", insertable=false, updatable=false, unique=true, nullable=false, precision=15)
	private long vstId;

	@Column(name="SMPTM_ID", insertable=false, updatable=false, unique=true, nullable=false, precision=15)
	private long smptmId;

	public HasSymptomPK() {
	}
	public long getVstId() {
		return this.vstId;
	}
	public void setVstId(long vstId) {
		this.vstId = vstId;
	}
	public long getSmptmId() {
		return this.smptmId;
	}
	public void setSmptmId(long smptmId) {
		this.smptmId = smptmId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HasSymptomPK)) {
			return false;
		}
		HasSymptomPK castOther = (HasSymptomPK)other;
		return 
			(this.vstId == castOther.vstId)
			&& (this.smptmId == castOther.smptmId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.vstId ^ (this.vstId >>> 32)));
		hash = hash * prime + ((int) (this.smptmId ^ (this.smptmId >>> 32)));
		
		return hash;
	}
}
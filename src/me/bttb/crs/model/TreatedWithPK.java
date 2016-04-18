package me.bttb.crs.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the TREATED_WITH database table.
 * 
 */
@Embeddable
public class TreatedWithPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "TRTMNT_ID", insertable = false, updatable = false, unique = true, nullable = false, precision = 19)
	private long trtmntId;

	@Column(name = "VST_ID", insertable = false, updatable = false, unique = true, nullable = false, precision = 19)
	private long vstId;

	public TreatedWithPK() {
	}

	public long getTrtmntId() {
		return this.trtmntId;
	}

	public void setTrtmntId(long trtmntId) {
		this.trtmntId = trtmntId;
	}

	public long getVstId() {
		return this.vstId;
	}

	public void setVstId(long vstId) {
		this.vstId = vstId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TreatedWithPK)) {
			return false;
		}
		TreatedWithPK castOther = (TreatedWithPK) other;
		return (this.trtmntId == castOther.trtmntId) && (this.vstId == castOther.vstId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.trtmntId ^ (this.trtmntId >>> 32)));
		hash = hash * prime + ((int) (this.vstId ^ (this.vstId >>> 32)));

		return hash;
	}
}
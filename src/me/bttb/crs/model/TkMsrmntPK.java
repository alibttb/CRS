package me.bttb.crs.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the TK_MSRMNT database table.
 * 
 */
@Embeddable
public class TkMsrmntPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "MSRMNT_ID", insertable = false, updatable = false, unique = true, nullable = false, precision = 19)
	private long msrmntId;

	@Column(name = "VST_ID", insertable = false, updatable = false, unique = true, nullable = false, precision = 19)
	private long vstId;

	public TkMsrmntPK() {
	}

	public long getMsrmntId() {
		return this.msrmntId;
	}

	public void setMsrmntId(long msrmntId) {
		this.msrmntId = msrmntId;
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
		if (!(other instanceof TkMsrmntPK)) {
			return false;
		}
		TkMsrmntPK castOther = (TkMsrmntPK) other;
		return (this.msrmntId == castOther.msrmntId) && (this.vstId == castOther.vstId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.msrmntId ^ (this.msrmntId >>> 32)));
		hash = hash * prime + ((int) (this.vstId ^ (this.vstId >>> 32)));

		return hash;
	}
}
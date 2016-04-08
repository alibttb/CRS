package me.bttb.crs.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the TK_MSRMNT database table.
 * 
 */
@Embeddable
public class TkMsrmntPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="VST_ID", insertable=false, updatable=false, unique=true, nullable=false, precision=15)
	private long vstId;

	@Column(name="MSRMNT_ID", insertable=false, updatable=false, unique=true, nullable=false, precision=15)
	private long msrmntId;

	public TkMsrmntPK() {
	}
	public long getVstId() {
		return this.vstId;
	}
	public void setVstId(long vstId) {
		this.vstId = vstId;
	}
	public long getMsrmntId() {
		return this.msrmntId;
	}
	public void setMsrmntId(long msrmntId) {
		this.msrmntId = msrmntId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TkMsrmntPK)) {
			return false;
		}
		TkMsrmntPK castOther = (TkMsrmntPK)other;
		return 
			(this.vstId == castOther.vstId)
			&& (this.msrmntId == castOther.msrmntId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.vstId ^ (this.vstId >>> 32)));
		hash = hash * prime + ((int) (this.msrmntId ^ (this.msrmntId >>> 32)));
		
		return hash;
	}
}
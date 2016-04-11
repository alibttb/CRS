package me.bttb.crs.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the DIAGANOSED_WITH database table.
 * 
 */
@Embeddable
public class DiaganosedWithPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DGNSS_ID", insertable=false, updatable=false, unique=true, nullable=false, precision=19)
	private long dgnssId;

	@Column(name="VST_ID", insertable=false, updatable=false, unique=true, nullable=false, precision=19)
	private long vstId;

	public DiaganosedWithPK() {
	}
	public long getDgnssId() {
		return this.dgnssId;
	}
	public void setDgnssId(long dgnssId) {
		this.dgnssId = dgnssId;
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
		if (!(other instanceof DiaganosedWithPK)) {
			return false;
		}
		DiaganosedWithPK castOther = (DiaganosedWithPK)other;
		return 
			(this.dgnssId == castOther.dgnssId)
			&& (this.vstId == castOther.vstId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.dgnssId ^ (this.dgnssId >>> 32)));
		hash = hash * prime + ((int) (this.vstId ^ (this.vstId >>> 32)));
		
		return hash;
	}
}
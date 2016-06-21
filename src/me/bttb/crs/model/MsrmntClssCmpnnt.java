package me.bttb.crs.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: MsrmntClssCmpnnt
 *
 */
@Entity
public class MsrmntClssCmpnnt implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;

	@ManyToOne
	private MsrmntClss mc;

	@ManyToOne
	@JoinColumn(name = "MSRMNT_ID", nullable = false, insertable = true, updatable = false)
	private Measurment measurment;
;

	public MsrmntClssCmpnnt() {
		super();
	}

	public Measurment getMeasurment() {
		return measurment;
	}

	public void setMeasurment(Measurment measurment) {
		this.measurment = measurment;
	}

	public MsrmntClss getMc() {
		return mc;
	}

	public void setMc(MsrmntClss mc) {
		this.mc = mc;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MsrmntClssCmpnnt) {
			MsrmntClssCmpnnt mcc = (MsrmntClssCmpnnt) obj;
			return mcc.measurment.equals(this.measurment);
		}
		return super.equals(obj);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}

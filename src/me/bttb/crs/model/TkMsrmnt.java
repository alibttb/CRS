package me.bttb.crs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the TK_MSRMNT database table.
 * 
 */
@Entity
@Table(name="TK_MSRMNT")
@NamedQuery(name="TkMsrmnt.findAll", query="SELECT t FROM TkMsrmnt t")
public class TkMsrmnt implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TkMsrmntPK id;

	@Column(length=2000)
	private String notes;

	@Column(name="NUMRECAL_VALUE")
	private BigDecimal numrecalValue;

	@Column(name="TEXTUAL_VALUE", length=4000)
	private String textualValue;

	//bi-directional many-to-one association to Measurment
	@ManyToOne
	@JoinColumn(name="MSRMNT_ID", nullable=false, insertable=false, updatable=false)
	private Measurment measurment;

	//bi-directional many-to-one association to Visit
	@ManyToOne
	@JoinColumn(name="VST_ID", nullable=false, insertable=false, updatable=false)
	private Visit visit;

	public TkMsrmnt() {
	}

	public TkMsrmntPK getId() {
		return this.id;
	}

	public void setId(TkMsrmntPK id) {
		this.id = id;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public BigDecimal getNumrecalValue() {
		return this.numrecalValue;
	}

	public void setNumrecalValue(BigDecimal numrecalValue) {
		this.numrecalValue = numrecalValue;
	}

	public String getTextualValue() {
		return this.textualValue;
	}

	public void setTextualValue(String textualValue) {
		this.textualValue = textualValue;
	}

	public Measurment getMeasurment() {
		return this.measurment;
	}

	public void setMeasurment(Measurment measurment) {
		this.measurment = measurment;
	}

	public Visit getVisit() {
		return this.visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

}
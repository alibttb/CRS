package me.bttb.crs.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the TK_MSRMNT database table.
 * 
 */
@Entity
@Table(name = "TK_MSRMNT")
@NamedQuery(name = "TkMsrmnt.findAll", query = "SELECT t FROM TkMsrmnt t")
public class TkMsrmnt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(precision = 19)
	private long id;
	@Column(length = 255)
	private String notes;

	@Column(name = "NUMRICAL_VALUE", precision = 38)
	private BigDecimal numricalValue;

	@Column(name = "TEXTUAL_VALUE", length = 255)
	private String textualValue;

	// bi-directional many-to-one association to Measurment
	@ManyToOne
	@JoinColumn(name = "MSRMNT_ID", nullable = false, insertable = true, updatable = false)
	private Measurment measurment;

	// bi-directional many-to-one association to Visit
	@ManyToOne
	@JoinColumn(name = "VST_ID", nullable = false, insertable = true, updatable = false)
	private Visit visit;

	
	public TkMsrmnt() {
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public BigDecimal getNumricalValue() {
		return this.numricalValue;
	}

	public void setNumricalValue(BigDecimal numricalValue) {
		this.numricalValue = numricalValue;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


}
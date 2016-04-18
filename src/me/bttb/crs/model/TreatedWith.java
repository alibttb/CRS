package me.bttb.crs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the TREATED_WITH database table.
 * 
 */
@Entity
@Table(name = "TREATED_WITH")
@NamedQuery(name = "TreatedWith.findAll", query = "SELECT t FROM TreatedWith t")
public class TreatedWith implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TreatedWithPK id;

	@Column(precision = 38)
	private BigDecimal dosage;

	@Column(name = "DURATION_DAYS", precision = 38)
	private BigDecimal durationDays;

	@Column(length = 255)
	private String notes;

	@Column(length = 255)
	private String repetation;

	@Column(length = 255)
	private String unit;

	// bi-directional many-to-one association to Treatment
	@ManyToOne
	@JoinColumn(name = "TRTMNT_ID", nullable = false, insertable = true, updatable = false)
	private Treatment treatment;

	// bi-directional many-to-one association to Visit
	@ManyToOne
	@JoinColumn(name = "VST_ID", nullable = false, insertable = true, updatable = false)
	private Visit visit;

	public TreatedWith() {
	}

	public TreatedWithPK getId() {
		return this.id;
	}

	public void setId(TreatedWithPK id) {
		this.id = id;
	}

	public BigDecimal getDosage() {
		return this.dosage;
	}

	public void setDosage(BigDecimal dosage) {
		this.dosage = dosage;
	}

	public BigDecimal getDurationDays() {
		return this.durationDays;
	}

	public void setDurationDays(BigDecimal durationDays) {
		this.durationDays = durationDays;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getRepetation() {
		return this.repetation;
	}

	public void setRepetation(String repetation) {
		this.repetation = repetation;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Treatment getTreatment() {
		return this.treatment;
	}

	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}

	public Visit getVisit() {
		return this.visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

}
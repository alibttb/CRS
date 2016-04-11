package me.bttb.crs.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the HAS_SYMPTOM database table.
 * 
 */
@Entity
@Table(name="HAS_SYMPTOM")
@NamedQuery(name="HasSymptom.findAll", query="SELECT h FROM HasSymptom h")
public class HasSymptom implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HasSymptomPK id;

	@Column(length=255)
	private String notes;

	@Column(length=255)
	private String repetation;

	@Column(length=255)
	private String severity;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	private Date startDate;

	//bi-directional many-to-one association to Symptom
	@ManyToOne
	@JoinColumn(name="SMPTM_ID", nullable=false, insertable=true, updatable=false)
	private Symptom symptom;

	//bi-directional many-to-one association to Visit
	@ManyToOne
	@JoinColumn(name="VST_ID", nullable=false, insertable=true, updatable=false)
	private Visit visit;

	public HasSymptom() {
	}

	public HasSymptomPK getId() {
		return this.id;
	}

	public void setId(HasSymptomPK id) {
		this.id = id;
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

	public String getSeverity() {
		return this.severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Symptom getSymptom() {
		return this.symptom;
	}

	public void setSymptom(Symptom symptom) {
		this.symptom = symptom;
	}

	public Visit getVisit() {
		return this.visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

}
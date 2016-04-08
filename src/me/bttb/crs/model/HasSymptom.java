package me.bttb.crs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the HAS_SYMPTOMS database table.
 * 
 */
@Entity
@Table(name="HAS_SYMPTOMS")
@NamedQuery(name="HasSymptom.findAll", query="SELECT h FROM HasSymptom h")
public class HasSymptom implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HasSymptomPK id;

	@Column(length=2000)
	private String notes;

	@Column(length=50)
	private String repetation;

	@Column(length=1)
	private String severity;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	private Date startDate;

	//bi-directional many-to-one association to Symptom
	@ManyToOne
	@JoinColumn(name="SMPTM_ID", nullable=false, insertable=false, updatable=false)
	private Symptom symptom;

	//bi-directional many-to-one association to Visit
	@ManyToOne
	@JoinColumn(name="VST_ID", nullable=false, insertable=false, updatable=false)
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
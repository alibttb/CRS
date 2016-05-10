package me.bttb.crs.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the DIAGANOSED_WITH database table.
 * 
 */
@Entity
@Table(name = "DIAGNOSED_WITH")
@NamedQuery(name = "DiagnosedWith.findAll", query = "SELECT d FROM DiagnosedWith d")
public class DiagnosedWith implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(precision = 19)
	private long id;

	@Column(length = 255)
	private String notes;

	@Column(length = 255)
	private String severity;

	// bi-directional many-to-one association to Diagnosis
	@ManyToOne
	@JoinColumn(name = "DGNSS_ID", nullable = false, insertable = true, updatable = false)
	private Diagnosis diagnosis;

	// bi-directional many-to-one association to Visit
	@ManyToOne
	@JoinColumn(name = "VST_ID", nullable = false, insertable = true, updatable = false)
	private Visit visit;

	public DiagnosedWith() {
	}


	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getSeverity() {
		return this.severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public Diagnosis getDiagnosis() {
		return this.diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		this.diagnosis = diagnosis;
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
package me.bttb.crs.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the DIAGANOSED_WITH database table.
 * 
 */
@Entity
@Table(name = "DIAGANOSED_WITH")
@NamedQuery(name = "DiaganosedWith.findAll", query = "SELECT d FROM DiaganosedWith d")
public class DiaganosedWith implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DiaganosedWithPK id;

	@Column(length = 255)
	private String notes;

	@Column(length = 255)
	private String severity;

	// bi-directional many-to-one association to Diaganosis
	@ManyToOne
	@JoinColumn(name = "DGNSS_ID", nullable = false, insertable = true, updatable = false)
	private Diaganosis diaganosis;

	// bi-directional many-to-one association to Visit
	@ManyToOne
	@JoinColumn(name = "VST_ID", nullable = false, insertable = true, updatable = false)
	private Visit visit;

	public DiaganosedWith() {
	}

	public DiaganosedWithPK getId() {
		return this.id;
	}

	public void setId(DiaganosedWithPK id) {
		this.id = id;
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

	public Diaganosis getDiaganosis() {
		return this.diaganosis;
	}

	public void setDiaganosis(Diaganosis diaganosis) {
		this.diaganosis = diaganosis;
	}

	public Visit getVisit() {
		return this.visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

}
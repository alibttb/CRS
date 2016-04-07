package me.bttb.crs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SYMPTOM database table.
 * 
 */
@Entity
@Table(name="SYMPTOM")
@NamedQuery(name="Symptom.findAll", query="SELECT s FROM Symptom s")
public class Symptom implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SMPTM_ID", unique=true, nullable=false, precision=15)
	private long smptmId;

	@Column(length=4000)
	private String description;

	@Column(length=100)
	private String name;

	@Column(length=2000)
	private String notes;

	//bi-directional many-to-one association to HasSymptom
	@OneToMany(mappedBy="symptom")
	private List<HasSymptom> hasSymptoms;

	public Symptom() {
	}

	public long getSmptmId() {
		return this.smptmId;
	}

	public void setSmptmId(long smptmId) {
		this.smptmId = smptmId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public List<HasSymptom> getHasSymptoms() {
		return this.hasSymptoms;
	}

	public void setHasSymptoms(List<HasSymptom> hasSymptoms) {
		this.hasSymptoms = hasSymptoms;
	}

	public HasSymptom addHasSymptom(HasSymptom hasSymptom) {
		getHasSymptoms().add(hasSymptom);
		hasSymptom.setSymptom(this);

		return hasSymptom;
	}

	public HasSymptom removeHasSymptom(HasSymptom hasSymptom) {
		getHasSymptoms().remove(hasSymptom);
		hasSymptom.setSymptom(null);

		return hasSymptom;
	}

}
package me.bttb.crs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the SYMPTOM database table.
 * 
 */
@Entity
@Table(name = "SYMPTOM")
@NamedQuery(name = "Symptom.findAll", query = "SELECT s FROM Symptom s")
public class Symptom implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(precision = 19)
	private long id;

	@Column(length = 255)
	private String description;

	@Column(length = 255)
	private String name;

	@Column(length = 255)
	private String notes;

	// bi-directional many-to-one association to HasSymptom
	@OneToMany(mappedBy = "symptom")
	private List<HasSymptom> hasSymptomList;

	public Symptom() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
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

	public List<HasSymptom> getHasSymptomList() {
		return this.hasSymptomList;
	}

	public void setHasSymptomList(List<HasSymptom> hasSymptomList) {
		this.hasSymptomList = hasSymptomList;
	}

	public HasSymptom addHasSymptomList(HasSymptom hasSymptomList) {
		getHasSymptomList().add(hasSymptomList);
		hasSymptomList.setSymptom(this);

		return hasSymptomList;
	}

	public HasSymptom removeHasSymptomList(HasSymptom hasSymptomList) {
		getHasSymptomList().remove(hasSymptomList);
		hasSymptomList.setSymptom(null);

		return hasSymptomList;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Symptom) {
			Symptom new_name = (Symptom) obj;
			return new_name.id == this.id;
		}
		return super.equals(obj);
	}

}
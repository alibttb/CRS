package me.bttb.crs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the DIAGANOSIS database table.
 * 
 */
@Entity
@Table(name = "DIAGNOSIS")
@NamedQuery(name = "Diagnosis.findAll", query = "SELECT d FROM Diagnosis d")
public class Diagnosis implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(precision = 19)
	private long id;

	@Column(length = 255)
	private String code;

	@Column(length = 255)
	private String describtion;

	@Column(name = "DGNSS_TYPE", length = 255)
	private String dgnssType;

	@Column(length = 255)
	private String name;

	// bi-directional many-to-one association to DiagnosedWith
	@OneToMany(mappedBy = "diagnosis")
	private List<DiagnosedWith> diaganosedWithList;

	public Diagnosis() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescribtion() {
		return this.describtion;
	}

	public void setDescribtion(String describtion) {
		this.describtion = describtion;
	}

	public String getDgnssType() {
		return this.dgnssType;
	}

	public void setDgnssType(String dgnssType) {
		this.dgnssType = dgnssType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<DiagnosedWith> getDiagnosedWithList() {
		return this.diaganosedWithList;
	}

	public void setDiagnosedWithList(List<DiagnosedWith> diaganosedWithList) {
		this.diaganosedWithList = diaganosedWithList;
	}

	public DiagnosedWith addDiagnosedWithList(DiagnosedWith diaganosedWithList) {
		getDiagnosedWithList().add(diaganosedWithList);
		diaganosedWithList.setDiagnosis(this);

		return diaganosedWithList;
	}

	public DiagnosedWith removeDiagnosedWithList(DiagnosedWith diaganosedWithList) {
		getDiagnosedWithList().remove(diaganosedWithList);
		diaganosedWithList.setDiagnosis(null);

		return diaganosedWithList;
	}

}
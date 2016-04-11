package me.bttb.crs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the DIAGANOSIS database table.
 * 
 */
@Entity
@Table(name="DIAGANOSIS")
@NamedQuery(name="Diaganosis.findAll", query="SELECT d FROM Diaganosis d")
public class Diaganosis implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(unique=true, nullable=false, precision=19)
	private long id;

	@Column(length=255)
	private String code;

	@Column(length=255)
	private String describtion;

	@Column(name="DGNSS_TYPE", length=255)
	private String dgnssType;

	@Column(length=255)
	private String name;

	//bi-directional many-to-one association to DiaganosedWith
	@OneToMany(mappedBy="diaganosis")
	private List<DiaganosedWith> diaganosedWithList;

	public Diaganosis() {
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

	public List<DiaganosedWith> getDiaganosedWithList() {
		return this.diaganosedWithList;
	}

	public void setDiaganosedWithList(List<DiaganosedWith> diaganosedWithList) {
		this.diaganosedWithList = diaganosedWithList;
	}

	public DiaganosedWith addDiaganosedWithList(DiaganosedWith diaganosedWithList) {
		getDiaganosedWithList().add(diaganosedWithList);
		diaganosedWithList.setDiaganosis(this);

		return diaganosedWithList;
	}

	public DiaganosedWith removeDiaganosedWithList(DiaganosedWith diaganosedWithList) {
		getDiaganosedWithList().remove(diaganosedWithList);
		diaganosedWithList.setDiaganosis(null);

		return diaganosedWithList;
	}

}
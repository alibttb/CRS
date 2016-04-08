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
@NamedQuery(name="Diaganosi.findAll", query="SELECT d FROM Diaganosi d")
public class Diaganosi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DGNSS_ID", unique=true, nullable=false, precision=15)
	private long dgnssId;

	@Column(length=10)
	private String code;

	@Column(length=4000)
	private String describtion;

	@Column(name="DGNSS_TYPE", length=2)
	private String dgnssType;

	@Column(length=100)
	private String name;

	//bi-directional many-to-one association to DiaganosedWith
	@OneToMany(mappedBy="diaganosi")
	private List<DiaganosedWith> diaganosedWiths;

	public Diaganosi() {
	}

	public long getDgnssId() {
		return this.dgnssId;
	}

	public void setDgnssId(long dgnssId) {
		this.dgnssId = dgnssId;
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

	public List<DiaganosedWith> getDiaganosedWiths() {
		return this.diaganosedWiths;
	}

	public void setDiaganosedWiths(List<DiaganosedWith> diaganosedWiths) {
		this.diaganosedWiths = diaganosedWiths;
	}

	public DiaganosedWith addDiaganosedWith(DiaganosedWith diaganosedWith) {
		getDiaganosedWiths().add(diaganosedWith);
		diaganosedWith.setDiaganosi(this);

		return diaganosedWith;
	}

	public DiaganosedWith removeDiaganosedWith(DiaganosedWith diaganosedWith) {
		getDiaganosedWiths().remove(diaganosedWith);
		diaganosedWith.setDiaganosi(null);

		return diaganosedWith;
	}

}
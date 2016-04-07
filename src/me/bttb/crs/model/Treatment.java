package me.bttb.crs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TREATMENT database table.
 * 
 */
@Entity
@Table(name="TREATMENT")
@NamedQuery(name="Treatment.findAll", query="SELECT t FROM Treatment t")
public class Treatment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TRTMNT_ID", unique=true, nullable=false, precision=15)
	private long trtmntId;

	@Column(length=4000)
	private String description;

	@Column(length=100)
	private String name;

	@Column(name="\"TYPE\"", length=2)
	private String type;

	//bi-directional many-to-one association to TreatedWith
	@OneToMany(mappedBy="treatment")
	private List<TreatedWith> treatedWiths;

	public Treatment() {
	}

	public long getTrtmntId() {
		return this.trtmntId;
	}

	public void setTrtmntId(long trtmntId) {
		this.trtmntId = trtmntId;
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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<TreatedWith> getTreatedWiths() {
		return this.treatedWiths;
	}

	public void setTreatedWiths(List<TreatedWith> treatedWiths) {
		this.treatedWiths = treatedWiths;
	}

	public TreatedWith addTreatedWith(TreatedWith treatedWith) {
		getTreatedWiths().add(treatedWith);
		treatedWith.setTreatment(this);

		return treatedWith;
	}

	public TreatedWith removeTreatedWith(TreatedWith treatedWith) {
		getTreatedWiths().remove(treatedWith);
		treatedWith.setTreatment(null);

		return treatedWith;
	}

}
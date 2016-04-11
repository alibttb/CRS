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
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(unique=true, nullable=false, precision=19)
	private long id;

	@Column(length=255)
	private String description;

	@Column(length=255)
	private String name;

	@Column(name="\"TYPE\"", length=255)
	private String type;

	//bi-directional many-to-one association to TreatedWith
	@OneToMany(mappedBy="treatment")
	private List<TreatedWith> treatedWithList;

	public Treatment() {
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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<TreatedWith> getTreatedWithList() {
		return this.treatedWithList;
	}

	public void setTreatedWithList(List<TreatedWith> treatedWithList) {
		this.treatedWithList = treatedWithList;
	}

	public TreatedWith addTreatedWithList(TreatedWith treatedWithList) {
		getTreatedWithList().add(treatedWithList);
		treatedWithList.setTreatment(this);

		return treatedWithList;
	}

	public TreatedWith removeTreatedWithList(TreatedWith treatedWithList) {
		getTreatedWithList().remove(treatedWithList);
		treatedWithList.setTreatment(null);

		return treatedWithList;
	}

}
package me.bttb.crs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the MEASURMENT database table.
 * 
 */
@Entity
@Table(name="MEASURMENT")
@NamedQuery(name="Measurment.findAll", query="SELECT m FROM Measurment m")
public class Measurment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(unique=true, nullable=false, precision=19)
	private long id;

	@Column(length=255)
	private String describtion;

	@Column(length=255)
	private String name;

	@Column(length=255)
	private String notes;

	//bi-directional many-to-one association to TkMsrmnt
	@OneToMany(mappedBy="measurment")
	private List<TkMsrmnt> tkMsrmntList;

	public Measurment() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescribtion() {
		return this.describtion;
	}

	public void setDescribtion(String describtion) {
		this.describtion = describtion;
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

	public List<TkMsrmnt> getTkMsrmntList() {
		return this.tkMsrmntList;
	}

	public void setTkMsrmntList(List<TkMsrmnt> tkMsrmntList) {
		this.tkMsrmntList = tkMsrmntList;
	}

	public TkMsrmnt addTkMsrmntList(TkMsrmnt tkMsrmntList) {
		getTkMsrmntList().add(tkMsrmntList);
		tkMsrmntList.setMeasurment(this);

		return tkMsrmntList;
	}

	public TkMsrmnt removeTkMsrmntList(TkMsrmnt tkMsrmntList) {
		getTkMsrmntList().remove(tkMsrmntList);
		tkMsrmntList.setMeasurment(null);

		return tkMsrmntList;
	}

}
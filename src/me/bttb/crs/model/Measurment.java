package me.bttb.crs.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the MEASURMENT database table.
 * 
 */
@Entity
@Table(name = "MEASURMENT")
@NamedQuery(name = "Measurment.findAll", query = "SELECT m FROM Measurment m")
public class Measurment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(precision = 19)
	private long id;

	@Column(length = 255, name = "DESCRIBTION")
	private String description;

	@Column(length = 255)
	private String name;

	@Column(length = 255)
	private String notes;

	// bi-directional many-to-one association to TkMsrmnt
	@OneToMany(mappedBy = "measurment")
	private List<TkMsrmnt> tkMsrmntList;

	@OneToMany(mappedBy = "measurment")
	private List<MsrmntClssCmpnnt> msrmntClssCmpnntList;

	public Measurment() {
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

	public List<TkMsrmnt> getTkMsrmntList() {
		return this.tkMsrmntList;
	}

	public void setTkMsrmntList(List<TkMsrmnt> tkMsrmntList) {
		this.tkMsrmntList = tkMsrmntList;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Measurment) {
			Measurment msr = (Measurment) obj;
			return msr.id == this.id;

		}
		return super.equals(obj);
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

	public List<MsrmntClssCmpnnt> getMsrmntClssCmpnntList() {
		return msrmntClssCmpnntList;
	}

	public void setMsrmntClssCmpnntList(List<MsrmntClssCmpnnt> msrmntClssCmpnntList) {
		this.msrmntClssCmpnntList = msrmntClssCmpnntList;
	}

}
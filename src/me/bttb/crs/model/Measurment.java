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
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MSRMNT_ID", unique=true, nullable=false, precision=15)
	private long msrmntId;

	@Column(length=4000)
	private String describtion;

	@Column(length=100)
	private String name;

	@Column(length=2000)
	private String notes;

	//bi-directional many-to-one association to TkMsrmnt
	@OneToMany(mappedBy="measurment")
	private List<TkMsrmnt> tkMsrmnts;

	public Measurment() {
	}

	public long getMsrmntId() {
		return this.msrmntId;
	}

	public void setMsrmntId(long msrmntId) {
		this.msrmntId = msrmntId;
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

	public List<TkMsrmnt> getTkMsrmnts() {
		return this.tkMsrmnts;
	}

	public void setTkMsrmnts(List<TkMsrmnt> tkMsrmnts) {
		this.tkMsrmnts = tkMsrmnts;
	}

	public TkMsrmnt addTkMsrmnt(TkMsrmnt tkMsrmnt) {
		getTkMsrmnts().add(tkMsrmnt);
		tkMsrmnt.setMeasurment(this);

		return tkMsrmnt;
	}

	public TkMsrmnt removeTkMsrmnt(TkMsrmnt tkMsrmnt) {
		getTkMsrmnts().remove(tkMsrmnt);
		tkMsrmnt.setMeasurment(null);

		return tkMsrmnt;
	}

}
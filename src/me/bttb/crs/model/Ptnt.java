package me.bttb.crs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PTNT database table.
 * 
 */
@Entity
@Table(name="PTNT")
@NamedQuery(name="Ptnt.findAll", query="SELECT p FROM Ptnt p")
public class Ptnt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PRSN_PRSN_PI", unique=true, nullable=false, precision=15)
	private long prsnPrsnPi;

	//bi-directional one-to-one association to Prsn
	@OneToOne
	@JoinColumn(name="PRSN_PRSN_PI", nullable=false, insertable=false, updatable=false)
	private Prsn prsn;

	//bi-directional many-to-one association to Visit
	@OneToMany(mappedBy="ptnt")
	private List<Visit> visits;

	public Ptnt() {
	}

	public long getPrsnPrsnPi() {
		return this.prsnPrsnPi;
	}

	public void setPrsnPrsnPi(long prsnPrsnPi) {
		this.prsnPrsnPi = prsnPrsnPi;
	}

	public Prsn getPrsn() {
		return this.prsn;
	}

	public void setPrsn(Prsn prsn) {
		this.prsn = prsn;
	}

	public List<Visit> getVisits() {
		return this.visits;
	}

	public void setVisits(List<Visit> visits) {
		this.visits = visits;
	}

	public Visit addVisit(Visit visit) {
		getVisits().add(visit);
		visit.setPtnt(this);

		return visit;
	}

	public Visit removeVisit(Visit visit) {
		getVisits().remove(visit);
		visit.setPtnt(null);

		return visit;
	}

}
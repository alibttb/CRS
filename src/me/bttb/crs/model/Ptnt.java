package me.bttb.crs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the PTNT database table.
 * 
 */
@Entity
@Table(name = "PTNT")
@NamedQueries({ @NamedQuery(name = "Ptnt.findAll", query = "SELECT p FROM Ptnt p"),
		@NamedQuery(name = "Ptnt.findByPid", query = "SELECT p FROM Ptnt p where p.pid = :pid"),
		@NamedQuery(name = "Ptnt.findByName", query = "SELECT p FROM Ptnt p "
				+ "where p.firstName like :firstName and p.familyName like :familyName") })
public class Ptnt extends Prsn implements Serializable {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to Visit
	@OneToMany(mappedBy = "ptnt")
	private List<Visit> visits;

	public Ptnt() {
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
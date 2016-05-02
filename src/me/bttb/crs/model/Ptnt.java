package me.bttb.crs.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

	public Integer getAge() {
		LocalDate bDate = this.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return (int) ChronoUnit.YEARS.between(LocalDate.now(), bDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Ptnt) {
			return ((Ptnt) obj).getPid() == this.getPid();
		}
		return super.equals(obj);
	}
}
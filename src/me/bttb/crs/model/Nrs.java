package me.bttb.crs.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the NRS database table.
 * 
 */
@Entity
@Table(name = "NRS")
@NamedQuery(name = "Nrs.findAll", query = "SELECT n FROM Nrs n")
public class Nrs extends Usr implements Serializable {
	private static final long serialVersionUID = 1L;

	public Nrs() {
	}

	@Override
	public String getRole() {
	return "Nurse";
	}
}
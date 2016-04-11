package me.bttb.crs.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the NRS database table.
 * 
 */
@Entity
@Table(name="NRS")
@NamedQuery(name="Nrs.findAll", query="SELECT n FROM Nrs n")
public class Nrs extends Prsn implements Serializable {
	private static final long serialVersionUID = 1L;


	public Nrs() {
	}


}
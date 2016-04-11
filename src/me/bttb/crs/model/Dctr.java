package me.bttb.crs.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the DCTR database table.
 * 
 */
@Entity
@Table(name="DCTR")
@NamedQuery(name="Dctr.findAll", query="SELECT d FROM Dctr d")
public class Dctr extends Prsn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="DCTR_MAIN_SPEC", length=255)
	private String dctrMainSpec;

	@Column(name="DCTR_SUB_SPEC", length=255)
	private String dctrSubSpec;

	public Dctr() {
	}

	public String getDctrMainSpec() {
		return this.dctrMainSpec;
	}

	public void setDctrMainSpec(String dctrMainSpec) {
		this.dctrMainSpec = dctrMainSpec;
	}

	public String getDctrSubSpec() {
		return this.dctrSubSpec;
	}

	public void setDctrSubSpec(String dctrSubSpec) {
		this.dctrSubSpec = dctrSubSpec;
	}

}
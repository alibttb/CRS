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
public class Dctr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USR_PRSN_PRSN_PI", unique=true, nullable=false, precision=15)
	private long usrPrsnPrsnPi;

	@Column(name="DCTR_MAIN_SPEC", length=100)
	private String dctrMainSpec;

	@Column(name="DCTR_SUB_SPEC", length=100)
	private String dctrSubSpec;

	//bi-directional one-to-one association to Usr
	@OneToOne
	@JoinColumn(name="USR_PRSN_PRSN_PI", nullable=false, insertable=false, updatable=false)
	private Usr usr;

	public Dctr() {
	}

	public long getUsrPrsnPrsnPi() {
		return this.usrPrsnPrsnPi;
	}

	public void setUsrPrsnPrsnPi(long usrPrsnPrsnPi) {
		this.usrPrsnPrsnPi = usrPrsnPrsnPi;
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

	public Usr getUsr() {
		return this.usr;
	}

	public void setUsr(Usr usr) {
		this.usr = usr;
	}

}
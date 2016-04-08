package me.bttb.crs.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the NRS database table.
 * 
 */
@Entity
@Table(name="NRS")
@NamedQuery(name="Nr.findAll", query="SELECT n FROM Nr n")
public class Nr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USR_PRSN_PRSN_PI", unique=true, nullable=false, precision=15)
	private long usrPrsnPrsnPi;

	//bi-directional one-to-one association to Usr
	@OneToOne
	@JoinColumn(name="USR_PRSN_PRSN_PI", nullable=false, insertable=false, updatable=false)
	private Usr usr;

	public Nr() {
	}

	public long getUsrPrsnPrsnPi() {
		return this.usrPrsnPrsnPi;
	}

	public void setUsrPrsnPrsnPi(long usrPrsnPrsnPi) {
		this.usrPrsnPrsnPi = usrPrsnPrsnPi;
	}

	public Usr getUsr() {
		return this.usr;
	}

	public void setUsr(Usr usr) {
		this.usr = usr;
	}

}
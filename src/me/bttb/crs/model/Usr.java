package me.bttb.crs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the USR database table.
 * 
 */
@Entity
@Table(name="USR")
@NamedQuery(name="Usr.findAll", query="SELECT u FROM Usr u")
public class Usr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PRSN_PI", unique=true, nullable=false, precision=15)
	private long prsnPi;

	@Column(name="\"ROLE\"", length=4)
	private String role;

	@Column(name="USR_HASH_SHA256", length=32)
	private String usrHashSha256;

	@Column(name="USR_SALT", length=10)
	private String usrSalt;

	@Column(name="USR_USER_NAME", length=75)
	private String usrUserName;

	@Temporal(TemporalType.DATE)
	@Column(name="USR_WORK_START_DATE")
	private Date usrWorkStartDate;

	//bi-directional one-to-one association to Dctr
	@OneToOne(mappedBy="usr")
	private Dctr dctr;

	//bi-directional one-to-one association to Nr
	@OneToOne(mappedBy="usr")
	private Nr nr;

	//bi-directional one-to-one association to Prsn
	@OneToOne
	@JoinColumn(name="PRSN_PI", nullable=false, insertable=false, updatable=false)
	private Prsn prsn;

	public Usr() {
	}

	public long getPrsnPi() {
		return this.prsnPi;
	}

	public void setPrsnPi(long prsnPi) {
		this.prsnPi = prsnPi;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsrHashSha256() {
		return this.usrHashSha256;
	}

	public void setUsrHashSha256(String usrHashSha256) {
		this.usrHashSha256 = usrHashSha256;
	}

	public String getUsrSalt() {
		return this.usrSalt;
	}

	public void setUsrSalt(String usrSalt) {
		this.usrSalt = usrSalt;
	}

	public String getUsrUserName() {
		return this.usrUserName;
	}

	public void setUsrUserName(String usrUserName) {
		this.usrUserName = usrUserName;
	}

	public Date getUsrWorkStartDate() {
		return this.usrWorkStartDate;
	}

	public void setUsrWorkStartDate(Date usrWorkStartDate) {
		this.usrWorkStartDate = usrWorkStartDate;
	}

	public Dctr getDctr() {
		return this.dctr;
	}

	public void setDctr(Dctr dctr) {
		this.dctr = dctr;
	}

	public Nr getNr() {
		return this.nr;
	}

	public void setNr(Nr nr) {
		this.nr = nr;
	}

	public Prsn getPrsn() {
		return this.prsn;
	}

	public void setPrsn(Prsn prsn) {
		this.prsn = prsn;
	}

}
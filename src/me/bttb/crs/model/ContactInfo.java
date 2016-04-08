package me.bttb.crs.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CONTACT_INFO database table.
 * 
 */
@Entity
@Table(name="CONTACT_INFO")
@NamedQuery(name="ContactInfo.findAll", query="SELECT c FROM ContactInfo c")
public class ContactInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CNTCT_NF_ID", unique=true, nullable=false, precision=15)
	private long cntctNfId;

	@Column(length=150)
	private String email;

	@Column(length=25)
	private String phone;

	@Column(name="\"TYPE\"", length=2)
	private String type;

	//bi-directional many-to-one association to Lctn
	@ManyToOne
	@JoinColumn(name="LCTN_LOC_ID", nullable=false)
	private Lctn lctn;

	//bi-directional many-to-one association to Prsn
	@ManyToOne
	@JoinColumn(name="PRSN_PRSN_PI", nullable=false)
	private Prsn prsn;

	public ContactInfo() {
	}

	public long getCntctNfId() {
		return this.cntctNfId;
	}

	public void setCntctNfId(long cntctNfId) {
		this.cntctNfId = cntctNfId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Lctn getLctn() {
		return this.lctn;
	}

	public void setLctn(Lctn lctn) {
		this.lctn = lctn;
	}

	public Prsn getPrsn() {
		return this.prsn;
	}

	public void setPrsn(Prsn prsn) {
		this.prsn = prsn;
	}

}
package me.bttb.crs.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the CONTACT_INFO database table.
 * 
 */
@Entity
@Table(name = "CONTACT_INFO")
@NamedQuery(name = "ContactInfo.findAll", query = "SELECT c FROM ContactInfo c")
public class ContactInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(precision = 19)
	private long id;

	@Column(length = 255)
	private String email;

	@Column(length = 255)
	private String phone;

	@Column(name = "CNTC_TYPE", length = 255)
	private String type;

	// bi-directional many-to-one association to Lctn
	@ManyToOne
	@JoinColumn(name = "LOC_ID")
	private Lctn lctn;

	// bi-directional many-to-one association to Prsn
	@ManyToOne
	@JoinColumn(name = "PID")
	private Prsn prsn;

	public ContactInfo() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
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

	///////////////////////////////
	@Override
	public boolean equals(Object obj) {
		boolean eq = true;
		eq = eq && (obj instanceof ContactInfo);
		if (eq) {
			ContactInfo other = (ContactInfo) obj;
			try {
				eq = eq && other.getPrsn().equals(this.getPrsn());
				if (other.getEmail() != null && this.getEmail() != null)
					eq = eq && other.getEmail().equalsIgnoreCase(this.getEmail());
				if (other.getPhone() != null && this.getPhone() != null)
					eq = eq && other.getPhone().equalsIgnoreCase(this.getPhone());
				if (other.getLctn() != null && this.getLctn() != null)
					eq = eq && other.getLctn().equals(this.getLctn());
			} catch (NullPointerException npe) {

			}
		}
		return eq;
	}
}
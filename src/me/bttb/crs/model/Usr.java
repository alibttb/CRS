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
public class Usr extends Prsn implements Serializable {
	private static final long serialVersionUID = 1L;


	@Column(name="HASH_SHA256", length=255)
	private String hashSha256;

	@Column(name="\"ROLE\"", length=255)
	private String role;

	@Column(length=255)
	private String salt;

	@Column(name="USER_NAME", length=255)
	private String userName;

	@Temporal(TemporalType.DATE)
	@Column(name="WORK_START_DATE")
	private Date workStartDate;

	public Usr() {
	}


	public String getHashSha256() {
		return this.hashSha256;
	}

	public void setHashSha256(String hashSha256) {
		this.hashSha256 = hashSha256;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getWorkStartDate() {
		return this.workStartDate;
	}

	public void setWorkStartDate(Date workStartDate) {
		this.workStartDate = workStartDate;
	}

}
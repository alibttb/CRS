package me.bttb.crs.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * The persistent class for the USR database table.
 * 
 */
@Entity
@Table(name = "USR")
@NamedQueries({ @NamedQuery(name = "Usr.findAll", query = "SELECT u FROM Usr u"),
		@NamedQuery(name = "Usr.getCount", query = "SELECT count(u) FROM Usr u"),
		@NamedQuery(name = "Usr.findByUserName", query = "SELECT u FROM Usr u WHERE u.userName = :userName"),
		@NamedQuery(name = "Usr.findByPid", query = "SELECT u FROM Usr u WHERE u.pid = :pid AND u.prsnType = 'Usr'") })
public class Usr extends Prsn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "HASH_SHA256", length = 255)
	private byte[] hashSha256;

	@Column(name = "USR_ROLE", length = 255)
	private String role;

	@Column(length = 255)
	private String salt;

	@Column(name = "USER_NAME", length = 255, unique = true)
	private String userName;

	@Temporal(TemporalType.DATE)
	@Column(name = "WORK_START_DATE")
	private Date workStartDate;

	@Column(name = "EMAIL", length = 255, unique = true)
	private String email;

	public Usr() {
	}

	public byte[] getHashSha256() {
		return this.hashSha256;
	}

	public void setHashSha256(byte[] hashSha256) {
		this.hashSha256 = hashSha256;
	}

	public String getRole() {
		return "Admin";
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Transient
	public boolean isAdmin() {
		return true;
	}

	@Transient
	public boolean isUser() {
		return true;
	}
}
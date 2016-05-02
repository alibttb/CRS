package me.bttb.crs.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.InheritanceType.JOINED;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the PRSN database table.
 * 
 */
@Entity
@Table(name = "PRSN")
@NamedQuery(name = "Prsn.findAll", query = "SELECT p FROM Prsn p")
@Inheritance(strategy = JOINED)
@DiscriminatorColumn(name = "PRSN_TYPE")
public class Prsn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(precision = 19)
	private Long pid;

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTH_DATE")
	private Date birthDate;

	@Column(name = "BIRTH_PLACE", length = 255)
	private String birthPlace;

	@Column(name = "FAMILY_NAME", length = 255)
	private String familyName;

	@Column(name = "FATHER_NAME", length = 255)
	private String fatherName;

	@Column(name = "FIRST_NAME", length = 255)
	private String firstName;

	@Column(name = "MOTHER_FAMILY_NAME", length = 255)
	private String motherFamilyName;

	@Column(name = "MOTHER_NAME", length = 255)
	private String motherName;

	@Column(name = "PRSN_TYPE", length = 31)
	private String prsnType;

	// bi-directional many-to-one association to ContactInfo
	@OneToMany(mappedBy = "prsn", cascade = { PERSIST, MERGE, REMOVE, DETACH, REFRESH, ALL })
	private List<ContactInfo> contactInfoList;

	public Prsn() {
	}

	public Long getPid() {
		return this.pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthPlace() {
		return this.birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getFamilyName() {
		return this.familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getFatherName() {
		return this.fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMotherFamilyName() {
		return this.motherFamilyName;
	}

	public void setMotherFamilyName(String motherFamilyName) {
		this.motherFamilyName = motherFamilyName;
	}

	public String getMotherName() {
		return this.motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getPrsnType() {
		return this.prsnType;
	}

	public void setPrsnType(String prsnType) {
		this.prsnType = prsnType;
	}

	public List<ContactInfo> getContactInfoList() {
		return this.contactInfoList;
	}

	public void setContactInfoList(List<ContactInfo> contactInfoList) {
		this.contactInfoList = contactInfoList;
	}

	public ContactInfo addContactInfoList(ContactInfo contactInfoList) {
		getContactInfoList().add(contactInfoList);
		contactInfoList.setPrsn(this);

		return contactInfoList;
	}

	public ContactInfo removeContactInfoList(ContactInfo contactInfoList) {
		getContactInfoList().remove(contactInfoList);
		contactInfoList.setPrsn(null);
		return contactInfoList;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Prsn) {
			Prsn p = (Prsn) obj;
			return p.getPid().equals(this.getPid());
		}
		return super.equals(obj);
	}

}
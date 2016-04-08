package me.bttb.crs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PRSN database table.
 * 
 */
@Entity
@Table(name="PRSN")
@NamedQuery(name="Prsn.findAll", query="SELECT p FROM Prsn p")
public class Prsn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PRSN_PI", unique=true, nullable=false, precision=15)
	private long prsnPi;

	@Temporal(TemporalType.DATE)
	@Column(name="PRSN_BIRTH_DATE")
	private Date prsnBirthDate;

	@Column(name="PRSN_BIRTH_PLACE", length=1024)
	private String prsnBirthPlace;

	@Column(name="PRSN_FAMILY_NAME", length=75)
	private String prsnFamilyName;

	@Column(name="PRSN_FATHER_NAME", length=75)
	private String prsnFatherName;

	@Column(name="PRSN_FIRST_NAME", length=75)
	private String prsnFirstName;

	@Column(name="PRSN_MOTHER_FAMILY_NAME", length=75)
	private String prsnMotherFamilyName;

	@Column(name="PRSN_MOTHER_NAME", length=75)
	private String prsnMotherName;

	@Column(name="\"TYPE\"", length=4)
	private String type;

	//bi-directional many-to-one association to ContactInfo
	@OneToMany(mappedBy="prsn")
	private List<ContactInfo> contactInfos;

	//bi-directional one-to-one association to Ptnt
	@OneToOne(mappedBy="prsn")
	private Ptnt ptnt;

	//bi-directional one-to-one association to Usr
	@OneToOne(mappedBy="prsn")
	private Usr usr;

	public Prsn() {
	}

	public long getPrsnPi() {
		return this.prsnPi;
	}

	public void setPrsnPi(long prsnPi) {
		this.prsnPi = prsnPi;
	}

	public Date getPrsnBirthDate() {
		return this.prsnBirthDate;
	}

	public void setPrsnBirthDate(Date prsnBirthDate) {
		this.prsnBirthDate = prsnBirthDate;
	}

	public String getPrsnBirthPlace() {
		return this.prsnBirthPlace;
	}

	public void setPrsnBirthPlace(String prsnBirthPlace) {
		this.prsnBirthPlace = prsnBirthPlace;
	}

	public String getPrsnFamilyName() {
		return this.prsnFamilyName;
	}

	public void setPrsnFamilyName(String prsnFamilyName) {
		this.prsnFamilyName = prsnFamilyName;
	}

	public String getPrsnFatherName() {
		return this.prsnFatherName;
	}

	public void setPrsnFatherName(String prsnFatherName) {
		this.prsnFatherName = prsnFatherName;
	}

	public String getPrsnFirstName() {
		return this.prsnFirstName;
	}

	public void setPrsnFirstName(String prsnFirstName) {
		this.prsnFirstName = prsnFirstName;
	}

	public String getPrsnMotherFamilyName() {
		return this.prsnMotherFamilyName;
	}

	public void setPrsnMotherFamilyName(String prsnMotherFamilyName) {
		this.prsnMotherFamilyName = prsnMotherFamilyName;
	}

	public String getPrsnMotherName() {
		return this.prsnMotherName;
	}

	public void setPrsnMotherName(String prsnMotherName) {
		this.prsnMotherName = prsnMotherName;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<ContactInfo> getContactInfos() {
		return this.contactInfos;
	}

	public void setContactInfos(List<ContactInfo> contactInfos) {
		this.contactInfos = contactInfos;
	}

	public ContactInfo addContactInfo(ContactInfo contactInfo) {
		getContactInfos().add(contactInfo);
		contactInfo.setPrsn(this);

		return contactInfo;
	}

	public ContactInfo removeContactInfo(ContactInfo contactInfo) {
		getContactInfos().remove(contactInfo);
		contactInfo.setPrsn(null);

		return contactInfo;
	}

	public Ptnt getPtnt() {
		return this.ptnt;
	}

	public void setPtnt(Ptnt ptnt) {
		this.ptnt = ptnt;
	}

	public Usr getUsr() {
		return this.usr;
	}

	public void setUsr(Usr usr) {
		this.usr = usr;
	}

}
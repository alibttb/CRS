package me.bttb.crs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the LCTN database table.
 * 
 */
@Entity
@Table(name="LCTN")
@NamedQuery(name="Lctn.findAll", query="SELECT l FROM Lctn l")
public class Lctn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="LOC_ID", unique=true, nullable=false, precision=15)
	private long locId;

	@Column(length=12)
	private String altittude;

	@Column(name="\"level\"", precision=38)
	private BigDecimal level;

	@Column(name="LOCATION_STRING", length=4000)
	private String locationString;

	@Column(length=12)
	private String magnitude;

	@Column(nullable=false, length=4000)
	private String name;

	@Column(name="SUPER_ID", nullable=false, precision=15)
	private BigDecimal superId;

	//bi-directional many-to-one association to ContactInfo
	@OneToMany(mappedBy="lctn")
	private List<ContactInfo> contactInfos;

	public Lctn() {
	}

	public long getLocId() {
		return this.locId;
	}

	public void setLocId(long locId) {
		this.locId = locId;
	}

	public String getAltittude() {
		return this.altittude;
	}

	public void setAltittude(String altittude) {
		this.altittude = altittude;
	}

	public BigDecimal getLevel() {
		return this.level;
	}

	public void setLevel(BigDecimal level) {
		this.level = level;
	}

	public String getLocationString() {
		return this.locationString;
	}

	public void setLocationString(String locationString) {
		this.locationString = locationString;
	}

	public String getMagnitude() {
		return this.magnitude;
	}

	public void setMagnitude(String magnitude) {
		this.magnitude = magnitude;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getSuperId() {
		return this.superId;
	}

	public void setSuperId(BigDecimal superId) {
		this.superId = superId;
	}

	public List<ContactInfo> getContactInfos() {
		return this.contactInfos;
	}

	public void setContactInfos(List<ContactInfo> contactInfos) {
		this.contactInfos = contactInfos;
	}

	public ContactInfo addContactInfo(ContactInfo contactInfo) {
		getContactInfos().add(contactInfo);
		contactInfo.setLctn(this);

		return contactInfo;
	}

	public ContactInfo removeContactInfo(ContactInfo contactInfo) {
		getContactInfos().remove(contactInfo);
		contactInfo.setLctn(null);

		return contactInfo;
	}

}
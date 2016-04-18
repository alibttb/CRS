package me.bttb.crs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the VISIT database table.
 * 
 */
@Entity
@Table(name = "VISIT")
@NamedQuery(name = "Visit.findAll", query = "SELECT v FROM Visit v")
public class Visit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(precision = 19)
	private long id;

	@Column(length = 255)
	private String notes;

	@Temporal(TemporalType.DATE)
	@Column(name = "VST_DATE")
	private Date vstDate;

	@Column(name = "VST_TYPE", length = 255)
	private String vstType;

	// bi-directional many-to-one association to DctrOrder
	@OneToMany(mappedBy = "visit")
	private List<DctrOrder> dctrOrders;

	// bi-directional many-to-one association to DiaganosedWith
	@OneToMany(mappedBy = "visit")
	private List<DiaganosedWith> diaganosedWithList;

	// bi-directional many-to-one association to Document
	@OneToMany(mappedBy = "visit")
	private List<Document> documents;

	// bi-directional many-to-one association to HasSymptom
	@OneToMany(mappedBy = "visit")
	private List<HasSymptom> hasSymptomList;

	// bi-directional many-to-one association to TkMsrmnt
	@OneToMany(mappedBy = "visit")
	private List<TkMsrmnt> tkMsrmntList;

	// bi-directional many-to-one association to TreatedWith
	@OneToMany(mappedBy = "visit")
	private List<TreatedWith> treatedWithList;

	// bi-directional many-to-one association to Ptnt
	@ManyToOne
	@JoinColumn(name = "PID")
	private Ptnt ptnt;

	public Visit() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getVstDate() {
		return this.vstDate;
	}

	public void setVstDate(Date vstDate) {
		this.vstDate = vstDate;
	}

	public String getVstType() {
		return this.vstType;
	}

	public void setVstType(String vstType) {
		this.vstType = vstType;
	}

	public List<DctrOrder> getDctrOrders() {
		return this.dctrOrders;
	}

	public void setDctrOrders(List<DctrOrder> dctrOrders) {
		this.dctrOrders = dctrOrders;
	}

	public DctrOrder addDctrOrder(DctrOrder dctrOrder) {
		getDctrOrders().add(dctrOrder);
		dctrOrder.setVisit(this);

		return dctrOrder;
	}

	public DctrOrder removeDctrOrder(DctrOrder dctrOrder) {
		getDctrOrders().remove(dctrOrder);
		dctrOrder.setVisit(null);

		return dctrOrder;
	}

	public List<DiaganosedWith> getDiaganosedWithList() {
		return this.diaganosedWithList;
	}

	public void setDiaganosedWithList(List<DiaganosedWith> diaganosedWithList) {
		this.diaganosedWithList = diaganosedWithList;
	}

	public DiaganosedWith addDiaganosedWithList(DiaganosedWith diaganosedWithList) {
		getDiaganosedWithList().add(diaganosedWithList);
		diaganosedWithList.setVisit(this);

		return diaganosedWithList;
	}

	public DiaganosedWith removeDiaganosedWithList(DiaganosedWith diaganosedWithList) {
		getDiaganosedWithList().remove(diaganosedWithList);
		diaganosedWithList.setVisit(null);

		return diaganosedWithList;
	}

	public List<Document> getDocuments() {
		return this.documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public Document addDocument(Document document) {
		getDocuments().add(document);
		document.setVisit(this);

		return document;
	}

	public Document removeDocument(Document document) {
		getDocuments().remove(document);
		document.setVisit(null);

		return document;
	}

	public List<HasSymptom> getHasSymptomList() {
		return this.hasSymptomList;
	}

	public void setHasSymptomList(List<HasSymptom> hasSymptomList) {
		this.hasSymptomList = hasSymptomList;
	}

	public HasSymptom addHasSymptomList(HasSymptom hasSymptomList) {
		getHasSymptomList().add(hasSymptomList);
		hasSymptomList.setVisit(this);

		return hasSymptomList;
	}

	public HasSymptom removeHasSymptomList(HasSymptom hasSymptomList) {
		getHasSymptomList().remove(hasSymptomList);
		hasSymptomList.setVisit(null);

		return hasSymptomList;
	}

	public List<TkMsrmnt> getTkMsrmntList() {
		return this.tkMsrmntList;
	}

	public void setTkMsrmntList(List<TkMsrmnt> tkMsrmntList) {
		this.tkMsrmntList = tkMsrmntList;
	}

	public TkMsrmnt addTkMsrmntList(TkMsrmnt tkMsrmntList) {
		getTkMsrmntList().add(tkMsrmntList);
		tkMsrmntList.setVisit(this);

		return tkMsrmntList;
	}

	public TkMsrmnt removeTkMsrmntList(TkMsrmnt tkMsrmntList) {
		getTkMsrmntList().remove(tkMsrmntList);
		tkMsrmntList.setVisit(null);

		return tkMsrmntList;
	}

	public List<TreatedWith> getTreatedWithList() {
		return this.treatedWithList;
	}

	public void setTreatedWithList(List<TreatedWith> treatedWithList) {
		this.treatedWithList = treatedWithList;
	}

	public TreatedWith addTreatedWithList(TreatedWith treatedWithList) {
		getTreatedWithList().add(treatedWithList);
		treatedWithList.setVisit(this);

		return treatedWithList;
	}

	public TreatedWith removeTreatedWithList(TreatedWith treatedWithList) {
		getTreatedWithList().remove(treatedWithList);
		treatedWithList.setVisit(null);

		return treatedWithList;
	}

	public Ptnt getPtnt() {
		return this.ptnt;
	}

	public void setPtnt(Ptnt ptnt) {
		this.ptnt = ptnt;
	}

}
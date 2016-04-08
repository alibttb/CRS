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
@Table(name="VISIT")
@NamedQuery(name="Visit.findAll", query="SELECT v FROM Visit v")
public class Visit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="VST_ID", unique=true, nullable=false, precision=15)
	private long vstId;

	@Column(length=2000)
	private String notes;

	@Temporal(TemporalType.DATE)
	@Column(name="VISIT_DATE")
	private Date visitDate;

	@Column(name="VIST_TYPE", length=1)
	private String vistType;

	//bi-directional many-to-one association to DctrOrder
	@OneToMany(mappedBy="visit")
	private List<DctrOrder> dctrOrders;

	//bi-directional many-to-one association to DiaganosedWith
	@OneToMany(mappedBy="visit")
	private List<DiaganosedWith> diaganosedWiths;

	//bi-directional many-to-one association to Document
	@OneToMany(mappedBy="visit")
	private List<Document> documents;

	//bi-directional many-to-one association to HasSymptom
	@OneToMany(mappedBy="visit")
	private List<HasSymptom> hasSymptoms;

	//bi-directional many-to-one association to TkMsrmnt
	@OneToMany(mappedBy="visit")
	private List<TkMsrmnt> tkMsrmnts;

	//bi-directional many-to-one association to TreatedWith
	@OneToMany(mappedBy="visit")
	private List<TreatedWith> treatedWiths;

	//bi-directional many-to-one association to Ptnt
	@ManyToOne
	@JoinColumn(name="PTNT_PRSN_PRSN_PI", nullable=false)
	private Ptnt ptnt;

	public Visit() {
	}

	public long getVstId() {
		return this.vstId;
	}

	public void setVstId(long vstId) {
		this.vstId = vstId;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getVisitDate() {
		return this.visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public String getVistType() {
		return this.vistType;
	}

	public void setVistType(String vistType) {
		this.vistType = vistType;
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

	public List<DiaganosedWith> getDiaganosedWiths() {
		return this.diaganosedWiths;
	}

	public void setDiaganosedWiths(List<DiaganosedWith> diaganosedWiths) {
		this.diaganosedWiths = diaganosedWiths;
	}

	public DiaganosedWith addDiaganosedWith(DiaganosedWith diaganosedWith) {
		getDiaganosedWiths().add(diaganosedWith);
		diaganosedWith.setVisit(this);

		return diaganosedWith;
	}

	public DiaganosedWith removeDiaganosedWith(DiaganosedWith diaganosedWith) {
		getDiaganosedWiths().remove(diaganosedWith);
		diaganosedWith.setVisit(null);

		return diaganosedWith;
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

	public List<HasSymptom> getHasSymptoms() {
		return this.hasSymptoms;
	}

	public void setHasSymptoms(List<HasSymptom> hasSymptoms) {
		this.hasSymptoms = hasSymptoms;
	}

	public HasSymptom addHasSymptom(HasSymptom hasSymptom) {
		getHasSymptoms().add(hasSymptom);
		hasSymptom.setVisit(this);

		return hasSymptom;
	}

	public HasSymptom removeHasSymptom(HasSymptom hasSymptom) {
		getHasSymptoms().remove(hasSymptom);
		hasSymptom.setVisit(null);

		return hasSymptom;
	}

	public List<TkMsrmnt> getTkMsrmnts() {
		return this.tkMsrmnts;
	}

	public void setTkMsrmnts(List<TkMsrmnt> tkMsrmnts) {
		this.tkMsrmnts = tkMsrmnts;
	}

	public TkMsrmnt addTkMsrmnt(TkMsrmnt tkMsrmnt) {
		getTkMsrmnts().add(tkMsrmnt);
		tkMsrmnt.setVisit(this);

		return tkMsrmnt;
	}

	public TkMsrmnt removeTkMsrmnt(TkMsrmnt tkMsrmnt) {
		getTkMsrmnts().remove(tkMsrmnt);
		tkMsrmnt.setVisit(null);

		return tkMsrmnt;
	}

	public List<TreatedWith> getTreatedWiths() {
		return this.treatedWiths;
	}

	public void setTreatedWiths(List<TreatedWith> treatedWiths) {
		this.treatedWiths = treatedWiths;
	}

	public TreatedWith addTreatedWith(TreatedWith treatedWith) {
		getTreatedWiths().add(treatedWith);
		treatedWith.setVisit(this);

		return treatedWith;
	}

	public TreatedWith removeTreatedWith(TreatedWith treatedWith) {
		getTreatedWiths().remove(treatedWith);
		treatedWith.setVisit(null);

		return treatedWith;
	}

	public Ptnt getPtnt() {
		return this.ptnt;
	}

	public void setPtnt(Ptnt ptnt) {
		this.ptnt = ptnt;
	}

}
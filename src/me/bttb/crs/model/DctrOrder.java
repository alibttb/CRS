package me.bttb.crs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the DCTR_ORDER database table.
 * 
 */
@Entity
@Table(name="DCTR_ORDER")
@NamedQuery(name="DctrOrder.findAll", query="SELECT d FROM DctrOrder d")
public class DctrOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="RDR_ID", unique=true, nullable=false, precision=15)
	private long rdrId;

	@Column(length=4000)
	private String description;

	@Column(length=100)
	private String name;

	@Column(length=2000)
	private String notes;

	@Column(name="\"TYPE\"", length=5)
	private String type;

	//bi-directional many-to-one association to Visit
	@ManyToOne
	@JoinColumn(name="VISIT_VST_ID", nullable=false)
	private Visit visit;

	//bi-directional many-to-one association to Document
	@OneToMany(mappedBy="dctrOrder")
	private List<Document> documents;

	public DctrOrder() {
	}

	public long getRdrId() {
		return this.rdrId;
	}

	public void setRdrId(long rdrId) {
		this.rdrId = rdrId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Visit getVisit() {
		return this.visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

	public List<Document> getDocuments() {
		return this.documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public Document addDocument(Document document) {
		getDocuments().add(document);
		document.setDctrOrder(this);

		return document;
	}

	public Document removeDocument(Document document) {
		getDocuments().remove(document);
		document.setDctrOrder(null);

		return document;
	}

}
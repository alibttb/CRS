package me.bttb.crs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the DCTR_ORDER database table.
 * 
 */
@Entity
@Table(name = "DCTR_ORDER")
@NamedQuery(name = "DctrOrder.findAll", query = "SELECT d FROM DctrOrder d")
public class DctrOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(precision = 19)
	private long id;

	@Column(length = 255)
	private String description;

	@Column(length = 255)
	private String name;

	@Column(length = 255)
	private String notes;

	@Column(name = "RDR_TYPE", length = 255)
	private String rdrType;

	// bi-directional many-to-one association to Visit
	@ManyToOne
	@JoinColumn(name = "VST_ID")
	private Visit visit;

	// bi-directional many-to-one association to Document
	@OneToMany(mappedBy = "dctrOrder")
	private List<Document> documents;

	public DctrOrder() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getRdrType() {
		return this.rdrType;
	}

	public void setRdrType(String rdrType) {
		this.rdrType = rdrType;
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
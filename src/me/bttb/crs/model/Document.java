package me.bttb.crs.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the DOCUMENT database table.
 * 
 */
@Entity
@Table(name = "DOCUMENT")
@NamedQuery(name = "Document.findAll", query = "SELECT d FROM Document d")
public class Document implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(precision = 19)
	private long id;

	@Lob
	private byte[] content;

	@Column(length = 255)
	private String description;

	@Column(name = "DOC_TYPE", length = 255)
	private String docType;

	@Column(length = 255)
	private String name;

	@Column(length = 255)
	private String notes;

	// bi-directional many-to-one association to DctrOrder
	@ManyToOne
	@JoinColumn(name = "RDR_ID")
	private DctrOrder dctrOrder;

	// bi-directional many-to-one association to Visit
	@ManyToOne
	@JoinColumn(name = "VST_ID")
	private Visit visit;

	public Document() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public byte[] getContent() {
		return this.content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDocType() {
		return this.docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
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

	public DctrOrder getDctrOrder() {
		return this.dctrOrder;
	}

	public void setDctrOrder(DctrOrder dctrOrder) {
		this.dctrOrder = dctrOrder;
	}

	public Visit getVisit() {
		return this.visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

}
package me.bttb.crs.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the DOCUMENT database table.
 * 
 */
@Entity
@Table(name="DOCUMENT")
@NamedQuery(name="Document.findAll", query="SELECT d FROM Document d")
public class Document implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false, precision=15)
	private long id;

	@Lob
	private byte[] content;

	@Column(length=4000)
	private String describtion;

	@Column(length=100)
	private String name;

	@Column(length=2000)
	private String notes;

	@Column(name="\"TYPE\"", length=5)
	private String type;

	//bi-directional many-to-one association to DctrOrder
	@ManyToOne
	@JoinColumn(name="ORDER_RDR_ID")
	private DctrOrder dctrOrder;

	//bi-directional many-to-one association to Visit
	@ManyToOne
	@JoinColumn(name="VST_ID", nullable=false)
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

	public String getDescribtion() {
		return this.describtion;
	}

	public void setDescribtion(String describtion) {
		this.describtion = describtion;
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
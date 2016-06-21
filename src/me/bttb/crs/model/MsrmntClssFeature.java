package me.bttb.crs.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 * Entity implementation class for Entity: MsrmntClssFeature
 *
 */
@Entity
public class MsrmntClssFeature implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "SMPTM_ID", nullable = false, insertable = true, updatable = false)
	private Symptom symptom;
	private double repeat;
	private double severity;
	private int timesAppeard;
	@ManyToOne
	private MsrmntClss mc;
	private static final long serialVersionUID = 1L;

	public MsrmntClssFeature() {
		super();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getRepeat() {
		return this.repeat;
	}

	public void setRepeat(double repeat) {
		this.repeat = repeat;
	}

	public void modifyRepeatAverageBY(double repeat) {
		double rpt = (timesAppeard * this.repeat + repeat) / (getTimesAppeard() + 1);
		this.setRepeat(rpt);
	}

	public double getSeverity() {
		return this.severity;
	}

	public void setSeverity(double severity) {
		this.severity = severity;
	}

	public void modifySeverityAvarageBy(double strength) {
		double str = (timesAppeard * this.severity + strength) / (getTimesAppeard() + 1);
		this.setSeverity(str);
	}

	@Transient
	public double getImportance() {
		return (double) this.timesAppeard / (double) mc.getCountOfVisits();
	}

	@Transient
	public double getImportanceFactor() {
		return (double) mc.getCountOfVisits() / (double) this.timesAppeard;
	}

	public MsrmntClss getMc() {
		return mc;
	}

	public void setMc(MsrmntClss mc) {
		this.mc = mc;
	}

	public Symptom getSymptom() {
		return symptom;
	}

	public void setSymptom(Symptom symptom) {
		this.symptom = symptom;
	}

	public int getTimesAppeard() {
		return timesAppeard;
	}

	public void setTimesAppeard(int timesAppeard) {
		this.timesAppeard = timesAppeard;
	}

	public void incrementTimesAppeared() {
		timesAppeard++;
	}

}

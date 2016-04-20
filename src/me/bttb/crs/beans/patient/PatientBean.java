package me.bttb.crs.beans.patient;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import me.bttb.crs.model.Ptnt;

@Named
@SessionScoped
public class PatientBean {
	@Inject
	private PatientDAO patientDAO;
	private Ptnt ptientData;
	
	public PatientBean() {
		
	}

	public PatientDAO getPatientDAO() {
		return patientDAO;
	}

	public void setPatientDAO(PatientDAO patientDAO) {
		this.patientDAO = patientDAO;
	}

	public Ptnt getPtientData() {
		return ptientData;
	}

	public void setPtientData(Ptnt ptientData) {
		this.ptientData = ptientData;
	}
}

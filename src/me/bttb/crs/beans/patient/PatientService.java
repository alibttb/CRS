package me.bttb.crs.beans.patient;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import me.bttb.crs.model.Ptnt;
import me.bttb.crs.model.Visit;

@Service
@Scope("session")
public class PatientService {
	@Autowired
	private PatientDAO dao;
	private Ptnt selected;

	@PostConstruct
	public void init() {
		selected = null;
	}

	public PatientService() {
	}

	public boolean save() {
		return dao.addPatient(selected);
	}

	public PatientDAO getPatientDAO() {
		return dao;
	}

	public void setPatientDAO(PatientDAO patientDAO) {
		this.dao = patientDAO;
	}

	public Ptnt getSelected() {
		return selected;
	}

	public void setSelected(Ptnt selectedPatient) {
		this.selected = selectedPatient;
	}

	public List<Ptnt> getPatientsByName(String firstName, String familyName) {
		return dao.getPatientByName(firstName, familyName);
	}

	public List<Ptnt> getPatientsByExample(Ptnt example) {
		return dao.findByExample(example);
	}

	public List<Ptnt> getPatientsByFullName(String fullNameSearchTerm) {
		return dao.findPatientsByFullNameSearch(fullNameSearchTerm);
	}

	public List<Visit> getVisits() {
		return selected.getVisits();
	}
}

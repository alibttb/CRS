package me.bttb.crs.beans.patient;

import java.util.List;
import java.util.Observable;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import me.bttb.crs.model.Ptnt;

@Service
@Scope("session")
public class PatientService {
	@Autowired
	private PatientDAO dao;
	private Ptnt selected;
	private Observable obs = new Observable();

	@PostConstruct
	public void init() {
		this.setSelected(null);
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
		obs.notifyObservers();
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

	public Observable getObs() {
		return obs;
	}

	public void setObs(Observable obs) {
		this.obs = obs;
	}
}

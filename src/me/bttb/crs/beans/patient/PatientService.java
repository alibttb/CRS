package me.bttb.crs.beans.patient;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import me.bttb.crs.model.Ptnt;
import me.bttb.crs.utils.Observable;

@Service
@Scope("session")
public class PatientService implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4799202526644370574L;
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
		if (selected.getPid() == null) {
			return dao.addPatient(selected);
		} else {
			return dao.updatePatient(selected);
		}
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
		obs.forceNotifyAllObservers();
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

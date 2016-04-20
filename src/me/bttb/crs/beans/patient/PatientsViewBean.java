package me.bttb.crs.beans.patient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import me.bttb.crs.model.Ptnt;

@ManagedBean
@ViewScoped
public class PatientsViewBean {
	@ManagedProperty(value = "#{patientDAO}")
	private PatientDAO patientDAO;
	private List<Ptnt> patients;
	private List<Ptnt> filterdPatients;

	public PatientsViewBean() {
	}

	@PostConstruct
	public void init() {
		refreshAllPatients();
	}

	public long getPatientsCount() {
		return patientDAO.getPatientsCount();
	}

	public List<Ptnt> refreshAllPatients() {
		this.setPatients(patientDAO.getPatients());
		return this.getPatients();
	}

	public void addNewPatientDialog() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("resizable", false);
		options.put("draggable", false);
		options.put("modal", true);
		// options.put("contentHeight", "95%");
		// options.put("height", 400);
		// options.put("width", 700);
		options.put("closable", false);
		RequestContext.getCurrentInstance().openDialog("newPatientDialog", options, null);

	}

	public void onDialogReturn(SelectEvent event) {
		if (event.getObject() != null && event.getObject() instanceof Ptnt) {
			patientDAO.addpatient((Ptnt) event.getObject());
			refreshAllPatients();
			FacesMessage msg = new FacesMessage("Patient Added", "Pateint information added in the database.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			FacesMessage msg = new FacesMessage("operation canceld", "Nothing is saved to the database.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	////////////////////////////////////

	public PatientDAO getPatientDAO() {
		return patientDAO;
	}

	public void setPatientDAO(PatientDAO patientDAO) {
		this.patientDAO = patientDAO;
	}

	public List<Ptnt> getPatients() {
		return patients;
	}

	public void setPatients(List<Ptnt> patients) {
		this.patients = patients;
	}

	public List<Ptnt> getFilterdPatients() {
		return filterdPatients;
	}

	public void setFilterdPatients(List<Ptnt> filterdPatients) {
		this.filterdPatients = filterdPatients;
	}

}

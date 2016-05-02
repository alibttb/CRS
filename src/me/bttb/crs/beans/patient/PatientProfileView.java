package me.bttb.crs.beans.patient;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import me.bttb.crs.beans.visit.VisitService;
import me.bttb.crs.model.Ptnt;
import me.bttb.crs.model.Visit;

@ManagedBean
@ViewScoped
public class PatientProfileView {
	@ManagedProperty(value = "#{patientService}")
	private PatientService patientService;
	@ManagedProperty(value = "#{visitService}")
	private VisitService visitService;

	public PatientProfileView() {
	}

	//////// ACTIONS///////////////////////////////////
	public String openVisit() {
		if (visitService.getSelectedVisit() == null) {
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No visit is selected",
					"you need to select a visit before opening it."));
			return null;
		} else {
			return "/visit-page?faces-redirect=true";
		}
	}

	//////////////////////////////////////////////////
	/////////////// EVENTS/////////////////////////////
	public void onAddNewVisit(ActionEvent event) {
		if (visitService.addNewVisit()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "New visit added!!", null));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Nothing Addes!!", "check if the patient has an empty visit."));
		}
	}

	//////////////////////////////////////////////////
	public PatientService getPatientService() {
		return patientService;
	}

	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}

	public String getFullName() {
		Ptnt p = patientService.getSelected();
		return p.getFirstName() + " " + p.getFatherName() + " " + p.getFamilyName();
	}

	public List<Visit> getPatientVisits() {
		return visitService.getVisitsForPatient(patientService.getSelected());
	}

	public Visit getSelectedVisit() {
		return getVisitService().getSelectedVisit();
	}

	public void setSelectedVisit(Visit vst) {
		getVisitService().setSelectedVisit(vst);
	}

	public VisitService getVisitService() {
		return visitService;
	}

	public void setVisitService(VisitService visitService) {
		this.visitService = visitService;
	}

}

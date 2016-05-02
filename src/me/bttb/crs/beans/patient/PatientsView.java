package me.bttb.crs.beans.patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.persistence.NoResultException;

import me.bttb.crs.model.Ptnt;

@ManagedBean
@ViewScoped
public class PatientsView {
	/////////////////////////////////////////////
	@ManagedProperty(value = "#{patientService}")
	private PatientService patientService;
	/////////////////////////////////////////////
	private Long pid;
	private String fullName;
	private Date startDate;
	private Date endDate;
	private Ptnt example;
	private List<Ptnt> patients;
	private List<Ptnt> filterdPatients;

	public PatientsView() {
	}

	@PostConstruct
	public void init() {
		fullName = "";
		pid = null;
		example = new Ptnt();
		example.setPid(0L);
		example.setContactInfoList(new ArrayList<>());
		refreshAllPatients();
	}

	public List<Ptnt> refreshAllPatients() {
		this.setPatients(getPatientService().getPatientDAO().getPatients());
		return this.getPatients();
	}

	public void onSearchByDetailedExample(ActionEvent event) {
		this.setPatients(this.patientService.getPatientsByExample(example));
	}

	public void onSearchByGeneralTerms(ActionEvent event) {
		if (pid == null) {
			this.setPatients(this.patientService.getPatientsByFullName(this.getFullName()));
		} else {
			try {
				Ptnt rs = this.getPatientService().getPatientDAO().getPatientById(pid);
				this.setPatients(Arrays.asList(rs));
				this.setSelectedPatient(rs);
			} catch (NoResultException nre) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"No patient:", "There was no patients with this pid."));
				this.setPatients(Arrays.asList());
			}
		}
	}


	public String openProfile() {
		if (this.getSelectedPatient() == null) {
			
			return null;
		} else {
			return "/patient-profile.xhtml?faces-redirect=true";
		}

	}

	///////////////////// Properties///////////////
	public void setSelectedPatient(Ptnt selectedPatient) {
		this.getPatientService().setSelected(selectedPatient);
	}

	public Ptnt getSelectedPatient() {
		return this.getPatientService().getSelected();
	}

	public Ptnt getExample() {
		return example;
	}

	public void setExample(Ptnt example) {
		this.example = example;
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

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public PatientService getPatientService() {
		return patientService;
	}

	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}

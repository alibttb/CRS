package me.bttb.crs.beans.patient;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import me.bttb.crs.model.ContactInfo;
import me.bttb.crs.model.Ptnt;

@ManagedBean
@ViewScoped
public class NewPatientView {
	@ManagedProperty(value = "#{patientService}")
	private PatientService service;
	@ManagedProperty(value = "#{msg}")
	private ResourceBundle msg;

	private ContactInfo cntctInfo;
	private boolean addContactInfoDisabled = false;
	private boolean deleteSelectedContactInfoDisabled = true;

	boolean confirm = false;

	public NewPatientView() {
	}

	@PostConstruct
	public void init() {
		if (this.getPatient() == null) {
			this.setPatient(new Ptnt());
			this.setCntctInfoList(new ArrayList<>());
		}
		this.setCntctInfo(new ContactInfo());
		this.getCntctInfo().setPrsn(getPatient());
	}

	/////////////// EVENTS//////////////////

	public void onAddNewContactInfoClicked(ActionEvent e) {
		this.getCntctInfoList().add(getCntctInfo());
		this.setCntctInfo(new ContactInfo());
	}

	public void onResetContactInfoInputClicked(ActionEvent e) {
		this.setCntctInfo(new ContactInfo());
	}

	public String onCancelButtonClicked() {
		this.init();
		return "/main-page?faces-redirect=true";
	}

	public String onSaveButtonClicked() {
		if (this.getService().save()) {
			this.getService().setSelected(this.getPatient());
			// this.init();
			return "/patient-profile?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, getMsg().getString("SaveError"), ""));
			return null;
		}
	}

	///////////////////////////////////////

	public Ptnt getPatient() {
		return this.getService().getSelected();
	}

	public void setPatient(Ptnt patient) {
		this.getService().setSelected(patient);
	}

	public ContactInfo getCntctInfo() {
		return cntctInfo;
	}

	public void setCntctInfo(ContactInfo cntctInfo) {
		this.cntctInfo = cntctInfo;
	}

	public boolean isAddContactInfoDisabled() {
		return addContactInfoDisabled;
	}

	public void setAddContactInfoDisabled(boolean addContactInfoDisabled) {
		this.addContactInfoDisabled = addContactInfoDisabled;
	}

	public boolean isDeleteSelectedContactInfoDisabled() {
		return deleteSelectedContactInfoDisabled;
	}

	public void setDeleteSelectedContactInfoDisabled(boolean deleteSelectedContactInfoDisabled) {
		this.deleteSelectedContactInfoDisabled = deleteSelectedContactInfoDisabled;
	}

	public List<ContactInfo> getCntctInfoList() {
		return getService().getSelected().getContactInfoList();
	}

	public void setCntctInfoList(List<ContactInfo> cntctInfoList) {
		this.getService().getSelected().setContactInfoList(cntctInfoList);
	}

	public PatientService getService() {
		return service;
	}

	public void setService(PatientService service) {
		this.service = service;
	}

	public ResourceBundle getMsg() {
		return msg;
	}

	public void setMsg(ResourceBundle msg) {
		this.msg = msg;
	}

}

package me.bttb.crs.beans.patient;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;

import org.primefaces.event.RowEditEvent;

import me.bttb.crs.model.ContactInfo;
import me.bttb.crs.model.Ptnt;

@ManagedBean
@ViewScoped
public class NewPatientView {
	@ManagedProperty(value = "#{patientService}")
	private PatientService service;

	private List<ContactInfo> cntctInfoList;
	private ContactInfo cntctInfo;
	private boolean addContactInfoDisabled = false;
	private boolean deleteSelectedContactInfoDisabled = true;

	boolean confirm = false;

	public NewPatientView() {
	}

	@PostConstruct
	public void init() {
		this.setPatient(new Ptnt());
		this.setCntctInfoList(new ArrayList<>());
		this.getPatient().setContactInfoList(this.getCntctInfoList());
	}

	/////////////// EVENTS//////////////////
	public void onAddNewContactInfo(ActionEvent event) {
		if (this.getService().getSelected().getContactInfoList() == null)
			this.getService().getSelected().setContactInfoList(new ArrayList<>());
		boolean hasNewRow = this.getService().getSelected().getContactInfoList().stream()
				.filter(ci -> ci.getEmail() == null && ci.getPhone() == null).count() >= 1;
		if (hasNewRow) {
			FacesMessage msg = new FacesMessage("Can't add new row", "There is an empty row already!!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			ContactInfo cntctInfo = new ContactInfo();
			cntctInfo.setType("Main");
			cntctInfo.setPrsn(this.getPatient());
			this.getService().getSelected().getContactInfoList().add(cntctInfo);
		}
	}

	public void onDeleteSelectedContactInfo(ActionEvent event) {
		if (cntctInfo != null) {
			cntctInfoList.remove(cntctInfo);
			cntctInfo = null;
		}
		this.setDeleteSelectedContactInfoDisabled(true);
		this.setAddContactInfoDisabled(false);
	}

	public void onRowEdit(RowEditEvent event) {
		this.setDeleteSelectedContactInfoDisabled(true);
		this.setAddContactInfoDisabled(false);
		ContactInfo editedContactInfo = (ContactInfo) event.getObject();
		boolean moreThanOne = this.getService().getSelected().getContactInfoList().stream()
				.filter(ci -> ci.equals(editedContactInfo)).count() > 1;
		if (moreThanOne) {
			this.getService().getSelected().getContactInfoList().remove(editedContactInfo);
			FacesMessage msg = new FacesMessage("Row Removed", "The contact information provided is redundent!!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void onRowEditInit(RowEditEvent event) {
		if (event.getObject() instanceof ContactInfo) {
			this.setCntctInfo((ContactInfo) event.getObject());
			this.setDeleteSelectedContactInfoDisabled(false);
		}
		this.setAddContactInfoDisabled(true);
	}

	public void onRowCancel(RowEditEvent event) {
		this.setDeleteSelectedContactInfoDisabled(true);
		this.setAddContactInfoDisabled(false);
	}

	public String onCancelButtonClicked() {
		this.init();
		return "/main-page?faces-redirect=true";
	}

	public String onSaveButtonClicked() {
		if (this.getService().save()) {
			this.init();
			return "/patient-profile?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nothing saved there was an error!!", ""));
			return null;
		}

	}
	// public void onRowSelect(SelectEvent event) {
	// this.setDeleteSelectedContactInfoDisabled(false);
	// }
	//
	// public void onRowUnselect(SelectEvent event) {
	// this.setDeleteSelectedContactInfoDisabled(true);
	// }
	// public void onCellEdit(CellEditEvent event) {
	// Object oldValue = event.getOldValue();
	// Object newValue = event.getNewValue();
	//
	// if (newValue != null && !newValue.equals(oldValue)) {
	// FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell
	// Changed",
	// "Old: " + oldValue + ", New:" + newValue);
	// FacesContext.getCurrentInstance().addMessage(null, msg);
	// }
	// }
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
		return cntctInfoList;
	}

	public void setCntctInfoList(List<ContactInfo> cntctInfoList) {
		this.cntctInfoList = cntctInfoList;
	}

	public PatientService getService() {
		return service;
	}

	public void setService(PatientService service) {
		this.service = service;
	}

}

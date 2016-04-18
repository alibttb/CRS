package me.bttb.crs.beans.patient;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

import me.bttb.crs.model.ContactInfo;
import me.bttb.crs.model.Ptnt;

@Named
@ManagedBean
@ViewScoped
public class NewPatientView {
	@Inject
	private PatientDAO patientDAO;
	private Ptnt patient;
	private List<ContactInfo> cntctInfoList;
	private ContactInfo cntctInfo;
	private boolean addContactInfoDisabled = false;
	private boolean deleteSelectedContactInfoDisabled = true;

	boolean confirm = false;

	public NewPatientView() {
		init();
	}

	public void init() {
		this.setPatient(new Ptnt());
		this.setCntctInfoList(new ArrayList<>());
		this.getPatient().setContactInfoList(this.getCntctInfoList());
	}

	/////////////// EVENTS//////////////////
	public void onAddNewContactInfo(ActionEvent event) {
		if (patient.getContactInfoList() == null)
			patient.setContactInfoList(new ArrayList<>());
		boolean hasNewRow = patient.getContactInfoList().stream()
				.filter(ci -> ci.getEmail() == null && ci.getPhone() == null).count() >= 1;
		if (hasNewRow) {
			FacesMessage msg = new FacesMessage("Can't add new row", "There is an empty row already!!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			ContactInfo cntctInfo = new ContactInfo();
			cntctInfo.setType("Main");
			cntctInfo.setPrsn(patient);
			patient.getContactInfoList().add(cntctInfo);
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
		boolean moreThanOne = patient.getContactInfoList().stream().filter(ci -> ci.equals(editedContactInfo))
				.count() > 1;
		if (moreThanOne) {
			patient.getContactInfoList().remove(editedContactInfo);
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

	public void onDialogCancel() {
		RequestContext.getCurrentInstance().closeDialog(null);
		this.init();
	}

	public void onDialogApprove() {
		RequestContext.getCurrentInstance().closeDialog(patient);
		this.init();
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

	public PatientDAO getPatientDAO() {
		return patientDAO;
	}

	public void setPatientDAO(PatientDAO patientDAO) {
		this.patientDAO = patientDAO;
	}

	public Ptnt getPatient() {
		return patient;
	}

	public void setPatient(Ptnt patient) {
		this.patient = patient;
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

}

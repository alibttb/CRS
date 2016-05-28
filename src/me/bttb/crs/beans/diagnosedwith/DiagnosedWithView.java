package me.bttb.crs.beans.diagnosedwith;

import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import me.bttb.crs.model.Diagnosis;

@ManagedBean
@ViewScoped
public class DiagnosedWithView {
	@ManagedProperty(value = "#{diagnosedWithService}")
	private DiagnosedWithService service;
	@ManagedProperty(value = "#{msg}")
	private ResourceBundle msg;

	public DiagnosedWithView() {
	}

	//////////////////// EVENTS/////////////////////////////
	public void onSaveButtonClicked(ActionEvent event) {
		if (service.save()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("Saved"), ""));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("Not_saved"), msg.getString("there_was_an_error")));
		}
	}

	public void onCancelButtonClicked(ActionEvent event) {
		service.cancelEdit();
	}

	public void onAddButtonClicked(ActionEvent event) {
		service.createNewDiagnosedWith();
	}

	public void onEditButtonClicked(ActionEvent event) {

	}

	public void onDeleteButtonClicked(ActionEvent event) {
		if (service.deleteSelected()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("Deleted"), ""));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("Not_deleted"), msg.getString("there_was_an_error")));
		}
	}

	////////////////////////////// FUNCTIONS//////////////
	public List<Diagnosis> completeDiagnosis(String query) {
		return service.getDiagnosisContaining(query);
	}

	public DiagnosedWithService getService() {
		return service;
	}

	public void setService(DiagnosedWithService service) {
		this.service = service;
	}

	public ResourceBundle getMsg() {
		return msg;
	}

	public void setMsg(ResourceBundle msg) {
		this.msg = msg;
	}

}

package me.bttb.crs.beans.hssmptm;

import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import me.bttb.crs.model.Symptom;

@ManagedBean
@ViewScoped
public class HasSymptomView {
	@ManagedProperty(value = "#{hasSymptomService}")
	private HasSymptomService service;

	@ManagedProperty(value="#{msg}")
	private ResourceBundle msg;
	
	public HasSymptomView() {
	}

	//////////////////// EVENTS/////////////////////////////
	public void onSaveButtonClicked(ActionEvent event) {
		if (service.save()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, getMsg().getString("Saved"), ""));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, getMsg().getString("Not_saved"), getMsg().getString("there_was_an_error")));
		}
	}

	public void onCancelButtonClicked(ActionEvent event) {
		service.cancelEdit();
	}

	public void onAddButtonClicked(ActionEvent event) {
		service.createNewHasSymptom();
	}

	public void onEditButtonClicked(ActionEvent event) {
	}

	public void onDeleteButtonClicked(ActionEvent event) {
		if (service.deleteSelected()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, getMsg().getString("Deleted"), ""));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, getMsg().getString("Not_deleted"), getMsg().getString("there_was_an_error")));
		}
	}

	////////////////////////////// FUNCTIONS//////////////
	public List<Symptom> completeSymptom(String query) {
		return service.getSymptomsContaining(query);
	}

	public HasSymptomService getService() {
		return service;
	}

	public void setService(HasSymptomService service) {
		this.service = service;
	}

	public ResourceBundle getMsg() {
		return msg;
	}

	public void setMsg(ResourceBundle msg) {
		this.msg = msg;
	}

}

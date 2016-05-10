package me.bttb.crs.beans.treatedwith;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import me.bttb.crs.model.Treatment;

@ManagedBean
@ViewScoped
public class TreatedWithView {
	@ManagedProperty(value = "#{treatedWithService}")
	private TreatedWithService service;

	public TreatedWithView() {
	}

	//////////////////// EVENTS/////////////////////////////
	public void onSaveButtonClicked(ActionEvent event) {
		if (service.save()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Saved", ""));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Not saved", "there was an error."));
		}
	}

	public void onCancelButtonClicked(ActionEvent event) {
		service.cancelEdit();
	}

	public void onAddButtonClicked(ActionEvent event) {
		service.createNewTreatedWith();
	}

	public void onEditButtonClicked(ActionEvent event) {

	}

	public void onDeleteButtonClicked(ActionEvent event) {
		if (service.deleteSelected()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Deleted", ""));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Not deleted", "there was an error."));
		}
	}

	////////////////////////////// FUNCTIONS//////////////
	public List<Treatment> completeTreatment(String query) {
		return service.getTreatmentContaining(query);
	}

	public TreatedWithService getService() {
		return service;
	}

	public void setService(TreatedWithService service) {
		this.service = service;
	}

}

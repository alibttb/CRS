package me.bttb.crs.beans.treatedwith;

import java.util.List;
import java.util.ResourceBundle;

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
@ManagedProperty(value = "#{msg}")
private ResourceBundle msg;
	public TreatedWithView() {
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
		service.createNewTreatedWith();
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
	public List<Treatment> completeTreatment(String query) {
		return service.getTreatmentContaining(query);
	}

	public TreatedWithService getService() {
		return service;
	}

	public void setService(TreatedWithService service) {
		this.service = service;
	}

	public ResourceBundle getMsg() {
		return msg;
	}

	public void setMsg(ResourceBundle msg) {
		this.msg = msg;
	}

}

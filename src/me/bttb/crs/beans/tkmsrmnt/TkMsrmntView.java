package me.bttb.crs.beans.tkmsrmnt;

import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import me.bttb.crs.model.Measurment;

@ManagedBean
@ViewScoped
public class TkMsrmntView {
	@ManagedProperty(value = "#{tkMsrmntService}")
	private TkMsrmntService service;
	@ManagedProperty(value = "#{msg}")
	private ResourceBundle msg;
	

	public TkMsrmntView() {
	}

	//////////////////// EVENTS/////////////////////////////
	public void onSaveButtonClicked(ActionEvent event) {
		if (service.save()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("Saved"), ""));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("SaveError"),""));
		}
	}

	public void onCancelButtonClicked(ActionEvent event) {
		service.cancelEdit();
	}

	public void onAddButtonClicked(ActionEvent event) {
		service.createNewTkMsrmnt();
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
	public List<Measurment> completeMeasurment(String query) {
		return service.getMsrmntsContaining(query);
	}

	public TkMsrmntService getService() {
		return service;
	}

	public void setService(TkMsrmntService service) {
		this.service = service;
	}

	public ResourceBundle getMsg() {
		return msg;
	}

	public void setMsg(ResourceBundle msg) {
		this.msg = msg;
	}

}

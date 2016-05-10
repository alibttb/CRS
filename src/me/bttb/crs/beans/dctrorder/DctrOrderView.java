package me.bttb.crs.beans.dctrorder;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean
@ViewScoped
public class DctrOrderView {
	@ManagedProperty(value = "#{dctrOrderService}")
	private DctrOrderService service;

	public DctrOrderView() {
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
		service.createNewDctrOrder();
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
	public List<String> completeDctrOrderType(String query) {
		return service.getDctrOrdersTypesContaining(query);
	}

	public List<String> completeDctrOrderName(String query) {
		return service.getDctrOrdersNamesContaining(query);
	}
	public DctrOrderService getService() {
		return service;
	}

	public void setService(DctrOrderService service) {
		this.service = service;
	}

}

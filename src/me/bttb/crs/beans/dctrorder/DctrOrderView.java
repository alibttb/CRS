package me.bttb.crs.beans.dctrorder;

import java.util.List;
import java.util.ResourceBundle;

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

	@ManagedProperty(value = "#{msg}")
	private ResourceBundle msg;
	
	public DctrOrderView() {
	}

	//////////////////// EVENTS/////////////////////////////
	public void onSaveButtonClicked(ActionEvent event) {
		if (service.save()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("Saved"), msg.getString("Saved_Detail"))); //$NON-NLS-1$ //$NON-NLS-2$
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("Not_saved"), msg.getString("there_was_an_error"))); //$NON-NLS-1$ //$NON-NLS-2$
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
					new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("Deleted"), msg.getString("Deleted_Detail"))); //$NON-NLS-1$ //$NON-NLS-2$
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("Not_deleted"), msg.getString("there_was_an_error"))); //$NON-NLS-1$ //$NON-NLS-2$
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

	public ResourceBundle getMsg() {
		return msg;
	}

	public void setMsg(ResourceBundle msg) {
		this.msg = msg;
	}

}

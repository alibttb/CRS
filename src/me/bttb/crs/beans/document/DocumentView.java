package me.bttb.crs.beans.document;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean
@ViewScoped
public class DocumentView implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4565700115621029990L;
	@ManagedProperty(value = "#{documentService}")
	private DocumentService service;

	public DocumentView() {
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
		service.createNewDocument();
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

	public List<String> completeDocumentName(String query) {
		return service.getDocumentNamesContaining(query);
	}
	public DocumentService getService() {
		return service;
	}

	public void setService(DocumentService service) {
		this.service = service;
	}

}

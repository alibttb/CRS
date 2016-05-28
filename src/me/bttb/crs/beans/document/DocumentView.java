package me.bttb.crs.beans.document;

import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class DocumentView {
	@ManagedProperty(value = "#{documentService}")
	private DocumentService service;
	private UploadedFile uploadedFile;
	@ManagedProperty(value = "#{msg}")
	private ResourceBundle msg;

	public DocumentView() {
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

	public void onFileUploaded(FileUploadEvent e) {
		UploadedFile uf = e.getFile();
		service.getSelected().setDocType(uf.getContentType());
		service.getSelected().setContent(uf.getContents());
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
					new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("Deleted"), ""));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("Not_deleted"), msg.getString("there_was_an_error")));
		}
	}

	public void onRemoveUploadedImageClicked(ActionEvent ev) {
		getService().getSelected().setContent(null);
		getService().getSelected().setDocType(null);
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

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public ResourceBundle getMsg() {
		return msg;
	}

	public void setMsg(ResourceBundle msg) {
		this.msg = msg;
	}

}

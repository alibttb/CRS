package me.bttb.crs.beans.document;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import me.bttb.crs.model.Document;

@ManagedBean
@RequestScoped
public class DocumentImageService {
	@ManagedProperty(value = "#{documentService}")
	private DocumentService service;

	public StreamedContent getImageById() {
		FacesContext context = FacesContext.getCurrentInstance();
		String idParam = context.getExternalContext().getRequestParameterMap().get("id");
		Long documentId;
		try {
			documentId = Long.parseLong(idParam);
		} catch (NumberFormatException nfe) {
			documentId = null;
		}
		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE || documentId == null) {
			// So, we're rendering the HTML. Return a stub StreamedContent so
			// that it will generate right URL.
			return new DefaultStreamedContent();
		} else {
			Document doc = getService().getDocumentById(documentId);
			InputStream is = new ByteArrayInputStream(doc.getContent());
			StreamedContent sc = new DefaultStreamedContent(is, doc.getDocType(), doc.getName());
			return sc;
		}
	}

	public StreamedContent getSelectedDocumentImage() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE || getService().getSelected().getContent() == null) {
			// So, we're rendering the HTML. Return a stub StreamedContent so
			// that it will generate right URL.
			return new DefaultStreamedContent();
		} else {
			InputStream is = new ByteArrayInputStream(getService().getSelected().getContent());
			StreamedContent sc = new DefaultStreamedContent(is, getService().getSelected().getDocType(),
					getService().getSelected().getName());
			return sc;
		}
	}

	public DocumentService getService() {
		return service;
	}

	public void setService(DocumentService service) {
		this.service = service;
	}
}

package me.bttb.crs.beans.document;

import java.io.Serializable;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import me.bttb.crs.beans.visit.VisitService;
import me.bttb.crs.model.Document;

@Service
@Scope("session")
public class DocumentService implements Observer, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4630484088695505014L;
	@Autowired
	private VisitService visitService;
	@Autowired
	private DocumentDAO dao;
	private List<Document> list;
	private Document selected;

	@PostConstruct
	private void init() {
		this.visitService.getObs().addObserver(this);
		refresh();
	}

	private void refresh() {
		this.setSelected(null);
		this.setList(dao.findDocumentsInVisit(visitService.getSelected()));
	}

	public boolean save() {
		boolean res;
		if (getSelected().getId() == 0) {
			res = dao.addDocument(selected);
		} else {
			res = dao.updateDocument(selected);
		}
		refresh();
		return res;
	}

	public List<Document> getList() {
		return list;
	}

	public void setList(List<Document> list) {
		this.list = list;
	}

	public Document getSelected() {
		return selected;
	}

	public void setSelected(Document selected) {
		this.selected = selected;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o == visitService.getObs()) {
			this.refresh();
		}
	}

	public void cancelEdit() {
		refresh();
	}

	public void createNewDocument() {
		selected = new Document();
		selected.setVisit(visitService.getSelected());
		this.setSelected(selected);
	}

	public boolean deleteSelected() {
		boolean res = dao.deleteDocument(selected);
		refresh();
		return res;
	}

	public List<String> getDocumentNamesContaining(String query) {
		return dao.findAllDocumentNames().stream().filter(s -> s.toLowerCase().contains(query.toLowerCase()))
				.collect(Collectors.toList());
	}

	public Document getDocumentById(Long documentId) {
		return dao.findDocumentById(documentId);
	}
}

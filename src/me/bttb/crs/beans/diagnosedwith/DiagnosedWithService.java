package me.bttb.crs.beans.diagnosedwith;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import me.bttb.crs.beans.diagnosis.DiagnosisService;
import me.bttb.crs.beans.visit.VisitService;
import me.bttb.crs.model.DiagnosedWith;
import me.bttb.crs.model.Diagnosis;

@Service
@Scope("session")
public class DiagnosedWithService implements Observer {
	@Autowired
	private VisitService visitService;
	@Autowired
	private DiagnosedWithDAO dao;
	@Autowired
	private DiagnosisService diagnosisService;
	private List<DiagnosedWith> list;
	private DiagnosedWith selected;

	@PostConstruct
	private void init() {
		this.visitService.getObs().addObserver(this);
		refresh();
	}

	private void refresh() {
		this.setSelected(null);
		this.setList(dao.findDiagnosedWithInVisit(visitService.getSelected()));
	}

	public boolean save() {
		boolean res;
		if (getSelected().getId() == 0) {
			res = dao.addDiagnosedWith(selected);
		} else {
			res = dao.updateDiagnosedWith(selected);
		}
		refresh();
		return res;
	}

	public List<DiagnosedWith> getList() {
		return list;
	}

	public void setList(List<DiagnosedWith> list) {
		this.list = list;
	}

	public DiagnosedWith getSelected() {
		return selected;
	}

	public void setSelected(DiagnosedWith selected) {
		this.selected = selected;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o == visitService.getObs()) {
			this.refresh();
		}
	}

	public List<Diagnosis> getDiagnosisContaining(String query) {
		return diagnosisService.getAllDiagnoses().stream()
				.filter(m -> m.getName().toLowerCase().contains(query.toLowerCase())).collect(Collectors.toList());
	}

	public void cancelEdit() {
		refresh();
	}

	public void createNewDiagnosedWith() {
		selected = new DiagnosedWith();
		selected.setVisit(visitService.getSelected());
		this.setSelected(selected);
	}

	public boolean deleteSelected() {
		boolean res = dao.deleteDiagnosedWith(selected);
		refresh();
		return res;
	}
}

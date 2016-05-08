package me.bttb.crs.beans.hssmptm;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import me.bttb.crs.beans.symptom.SymptomService;
import me.bttb.crs.beans.visit.VisitService;
import me.bttb.crs.model.HasSymptom;
import me.bttb.crs.model.Symptom;

@Service
@Scope("session")
public class HasSymptomService implements Observer {
	@Autowired
	private VisitService visitService;
	@Autowired
	private HasSymptomDAO dao;
	@Autowired
	private SymptomService symptomService;
	private List<HasSymptom> list;
	private HasSymptom selected;

	@PostConstruct
	private void init() {
		this.visitService.getObs().addObserver(this);
		refresh();
	}

	private void refresh() {
		this.setSelected(null);
		this.setList(dao.findHasSymptomsInVisit(visitService.getSelected()));
	}

	public boolean save() {
		boolean res;
		if (getSelected().getId() == 0) {
			res = dao.addHasSymptom(selected);
		} else {
			res = dao.updateHasSymptom(selected);
		}
		refresh();
		return res;
	}

	public List<HasSymptom> getList() {
		return list;
	}

	public void setList(List<HasSymptom> list) {
		this.list = list;
	}

	public HasSymptom getSelected() {
		return selected;
	}

	public void setSelected(HasSymptom selected) {
		this.selected = selected;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o == visitService.getObs()) {
			this.refresh();
		}
	}

	public List<Symptom> getSymptomsContaining(String query) {
		return symptomService.getAllSymptoms().stream()
				.filter(s -> s.getName().toLowerCase().contains(query.toLowerCase())).collect(Collectors.toList());
	}

	public void cancelEdit() {
		refresh();
	}

	public void createNewHasSymptom() {
		selected = new HasSymptom();
		selected.setVisit(visitService.getSelected());
		this.setSelected(selected);
	}

	public boolean deleteSelected() {
		boolean res = dao.deleteHasSymptom(selected);
		refresh();
		return res;
	}
}

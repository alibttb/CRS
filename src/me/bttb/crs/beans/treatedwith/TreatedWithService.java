package me.bttb.crs.beans.treatedwith;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import me.bttb.crs.beans.treatment.TreatmentService;
import me.bttb.crs.beans.visit.VisitService;
import me.bttb.crs.model.TreatedWith;
import me.bttb.crs.model.Treatment;

@Service
@Scope("session")
public class TreatedWithService implements Observer {
	@Autowired
	private VisitService visitService;
	@Autowired
	private TreatedWithDAO dao;
	@Autowired
	private TreatmentService treatmentService;
	private List<TreatedWith> list;
	private TreatedWith selected;

	@PostConstruct
	private void init() {
		this.visitService.getObs().addObserver(this);
		refresh();
	}

	private void refresh() {
		this.setSelected(null);
		this.setList(dao.findTreatedWithInVisit(visitService.getSelected()));
	}

	public boolean save() {
		boolean res;
		if (getSelected().getId() == 0) {
			res = dao.addTreatedWith(selected);
		} else {
			res = dao.updateTreatedWith(selected);
		}
		refresh();
		return res;
	}

	public List<TreatedWith> getList() {
		return list;
	}

	public void setList(List<TreatedWith> list) {
		this.list = list;
	}

	public TreatedWith getSelected() {
		return selected;
	}

	public void setSelected(TreatedWith selected) {
		this.selected = selected;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o == visitService.getObs()) {
			this.refresh();
		}
	}

	public List<Treatment> getTreatmentContaining(String query) {
		return treatmentService.getAllTreatments().stream()
				.filter(m -> m.getName().toLowerCase().contains(query.toLowerCase())).collect(Collectors.toList());
	}

	public void cancelEdit() {
		refresh();
	}

	public void createNewTreatedWith() {
		selected = new TreatedWith();
		selected.setVisit(visitService.getSelected());
		this.setSelected(selected);
	}

	public boolean deleteSelected() {
		boolean res = dao.deleteTreatedWith(selected);
		refresh();
		return res;
	}
}

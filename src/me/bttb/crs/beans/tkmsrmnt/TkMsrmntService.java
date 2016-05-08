package me.bttb.crs.beans.tkmsrmnt;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import me.bttb.crs.beans.measurment.MeasurmentService;
import me.bttb.crs.beans.visit.VisitService;
import me.bttb.crs.model.Measurment;
import me.bttb.crs.model.TkMsrmnt;

@Service
@Scope("session")
public class TkMsrmntService implements Observer {
	@Autowired
	private VisitService visitService;
	@Autowired
	private TkMsrmntDAO dao;
	@Autowired
	private MeasurmentService measurmentService;
	private List<TkMsrmnt> list;
	private TkMsrmnt selected;

	@PostConstruct
	private void init() {
		this.visitService.getObs().addObserver(this);
		refresh();
	}

	private void refresh() {
		this.setSelected(null);
		this.setList(dao.findTkMsrmntInVisit(visitService.getSelected()));
	}

	public boolean save() {
		boolean res;
		if (getSelected().getId() == 0) {
			res = dao.addTkMsrmnt(selected);
		} else {
			res = dao.updateTkMsrmnt(selected);
		}
		refresh();
		return res;
	}

	public List<TkMsrmnt> getList() {
		return list;
	}

	public void setList(List<TkMsrmnt> list) {
		this.list = list;
	}

	public TkMsrmnt getSelected() {
		return selected;
	}

	public void setSelected(TkMsrmnt selected) {
		this.selected = selected;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o == visitService.getObs()) {
			this.refresh();
		}
	}

	public List<Measurment> getMsrmntsContaining(String query) {
		return measurmentService.getAllMeasurments().stream()
				.filter(m -> m.getName().toLowerCase().contains(query.toLowerCase())).collect(Collectors.toList());
	}

	public void cancelEdit() {
		refresh();
	}

	public void createNewTkMsrmnt() {
		selected = new TkMsrmnt();
		selected.setVisit(visitService.getSelected());
		this.setSelected(selected);
	}

	public boolean deleteSelected() {
		boolean res = dao.deleteTkMsrmnt(selected);
		refresh();
		return res;
	}
}

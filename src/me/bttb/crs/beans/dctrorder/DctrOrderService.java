package me.bttb.crs.beans.dctrorder;

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
import me.bttb.crs.model.DctrOrder;

@Service
@Scope("session")
public class DctrOrderService implements Observer , Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6993810633624445018L;
	@Autowired
	private VisitService visitService;
	@Autowired
	private DctrOrderDAO dao;
	private List<DctrOrder> list;
	private DctrOrder selected;

	@PostConstruct
	private void init() {
		this.visitService.getObs().addObserver(this);
		refresh();
	}

	private void refresh() {
		this.setSelected(null);
		this.setList(dao.findDctrOrdersInVisit(visitService.getSelected()));
	}

	public boolean save() {
		boolean res;
		if (getSelected().getId() == 0) {
			res = dao.addDctrOrder(selected);
		} else {
			res = dao.updateDctrOrder(selected);
		}
		refresh();
		return res;
	}

	public List<DctrOrder> getList() {
		return list;
	}

	public void setList(List<DctrOrder> list) {
		this.list = list;
	}

	public DctrOrder getSelected() {
		return selected;
	}

	public void setSelected(DctrOrder selected) {
		this.selected = selected;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o == visitService.getObs()) {
			this.refresh();
		}
	}

	public List<String> getDctrOrdersTypesContaining(String query) {
		return dao.findAllDctrOrdersTypes().stream().filter(s -> s.toLowerCase().contains(query.toLowerCase()))
				.collect(Collectors.toList());
	}

	public void cancelEdit() {
		refresh();
	}

	public void createNewDctrOrder() {
		selected = new DctrOrder();
		selected.setVisit(visitService.getSelected());
		this.setSelected(selected);
	}

	public boolean deleteSelected() {
		boolean res = dao.deleteDctrOrder(selected);
		refresh();
		return res;
	}

	public List<String> getDctrOrdersNamesContaining(String query) {
		return dao.findAllDctrOrdersNames().stream().filter(s -> s.toLowerCase().contains(query.toLowerCase()))
				.collect(Collectors.toList());
	}
}

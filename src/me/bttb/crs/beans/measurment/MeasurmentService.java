package me.bttb.crs.beans.measurment;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import me.bttb.crs.model.Measurment;

@Service
@Scope("session")
public class MeasurmentService implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1709885674696822580L;
	@Autowired
	private MeasurmentDAO dao;
	private Measurment selected;

	public MeasurmentService() {
	}

	public List<Measurment> getAllMeasurments() {
		return dao.findAll();
	}

	public Measurment getSelected() {
		return selected;
	}

	public void setSelected(Measurment selected) {
		this.selected = selected;
	}

	public void createNew() {
		this.selected = new Measurment();
	}

	public boolean save() {
		try {
			if (selected.getId() == 0) {
				dao.add(selected);
			} else {
				dao.update(selected);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void cancel() {
		selected = null;

	}
}

package me.bttb.crs.beans.treatment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import me.bttb.crs.model.Treatment;

@Service
@Scope("session")
public class TreatmentService {
	@Autowired
	private TreatmentDAO dao;
	private Treatment selected;

	public TreatmentService() {
	}

	public List<Treatment> getAllTreatments() {
		return dao.findAll();
	}

	public Treatment getSelected() {
		return selected;
	}

	public void setSelected(Treatment selected) {
		this.selected = selected;
	}

	public void createNew() {
		this.selected = new Treatment();
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

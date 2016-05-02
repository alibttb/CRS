package me.bttb.crs.beans.symptom;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import me.bttb.crs.model.Symptom;

@Service
@Scope("session")
public class SymptomService {
	@Autowired
	private SymptomDAO dao;
	private Symptom selected;

	public SymptomService() {
	}

	public List<Symptom> getAllSymptoms() {
		return dao.findAllSymptoms();
	}

	public Symptom getSelected() {
		return selected;
	}

	public void setSelected(Symptom selected) {
		this.selected = selected;
	}

	public void createNewSymptom() {
		this.selected = new Symptom();
	}

	public boolean save() {
		try {
			if (selected.getId() == 0) {
				dao.addSymptom(selected);
			} else {
				dao.updateSymptom(selected);
			}
			selected = null;
			return true;

		} catch (PersistenceException pe) {
			selected = null;
			return false;
		}
	}

	public void cancel() {
		selected = null;
	}
}

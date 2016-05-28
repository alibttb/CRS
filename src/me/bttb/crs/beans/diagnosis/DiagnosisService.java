package me.bttb.crs.beans.diagnosis;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import me.bttb.crs.model.Diagnosis;

@Service
@Scope("session")
public class DiagnosisService implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7022611354344778633L;
	@Autowired
	private DiagnosisDAO dao;
	private Diagnosis selected;

	public DiagnosisService() {
	}

	public List<Diagnosis> getAllDiagnoses() {
		return dao.findAll();
	}

	public Diagnosis getSelected() {
		return selected;
	}

	public void setSelected(Diagnosis selected) {
		this.selected = selected;
	}

	public void createNew() {
		this.selected = new Diagnosis();
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

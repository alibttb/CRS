package me.bttb.crs.beans.user;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import me.bttb.crs.beans.user.doctor.DoctorDAO;
import me.bttb.crs.beans.user.nurse.NurseDAO;
import me.bttb.crs.model.Dctr;
import me.bttb.crs.model.Nrs;
import me.bttb.crs.model.Usr;

@Service
@Scope("session")
public class UserService implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7781962674903009686L;
	@Autowired
	UserDAO uDao;
	@Autowired
	DoctorDAO dDao;
	@Autowired
	NurseDAO nDao;
	private Usr selected;

	public UserService() {
	}

	public boolean save() {
		switch (selected.getPrsnType()) {
		case "Usr":
			if (selected.getPid() == null) {
				return uDao.addUser(selected);
			} else {
				return uDao.updateUser(selected);
			}
		case "Dctr":
			if (selected.getPid() == null) {
				return dDao.addDoctor(getSelectedAsDctr());
			} else {
				return dDao.updateDoctor(getSelectedAsDctr());
			}
		case "Nrs":
			if (selected.getPid() == null) {
				return nDao.addNurse(getSelectedAsNurse());
			} else {
				return nDao.updateNurse(getSelectedAsNurse());
			}
		default:
			return false;
		}
	}

	public boolean cancel() {
		selected = null;
		return true;
	}

	public List<Usr> getAllUsers() {
		List<Usr> ls = uDao.getAllUsers();
		return ls;
	}

	public Usr getSelected() {
		return selected;
	}

	public void setSelected(Usr selected) {
		this.selected = selected;
	}

	public boolean isADoctorSelected() {
		return selected instanceof Dctr;
	}

	public Dctr getSelectedAsDctr() {
		return isADoctorSelected() ? ((Dctr) selected) : null;
	}

	public boolean isANurseSelected() {
		return selected instanceof Nrs;
	}

	public Nrs getSelectedAsNurse() {
		return isANurseSelected() ? ((Nrs) selected) : null;
	}

}

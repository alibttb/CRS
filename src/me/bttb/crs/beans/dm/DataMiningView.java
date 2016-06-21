package me.bttb.crs.beans.dm;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import me.bttb.crs.beans.dm.msrmnts.MsrmntClssService;
import me.bttb.crs.beans.dm.msrmnts.MsrmntClssService.Steps;

@ManagedBean
@ViewScoped
public class DataMiningView implements Observer, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{msrmntClssService}")
	private MsrmntClssService mcService;
	@ManagedProperty("#{msg}")
	private ResourceBundle msg;

	private String stepMsg;
	private Integer stepProgress;
	private Integer allProgress;

	@PostConstruct
	public void init() {
		mcService.getObs().addObserver(this);
		stepMsg = msg.getString(mcService.getStep().name());
		stepProgress = mcService.getStepProgress();
		allProgress = mcService.getOverAllProgress();
	}

	@PreDestroy
	public void finish() {
		mcService.getObs().deleteObserver(this);
	}

	public MsrmntClssService getMcService() {
		return mcService;
	}

	public void setMcService(MsrmntClssService mcService) {
		this.mcService = mcService;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o.equals(mcService.getObs())) {
			stepMsg = msg.getString(mcService.getStep().name());
			stepProgress = mcService.getStepProgress();
			allProgress = mcService.getOverAllProgress();
		}

	}

	public void listener() {

	}

	public boolean isWorking() {
		return mcService.getStep() != Steps.IDLE;
	}

	public void onStepComplete() {

	}

	public void onAllComplete() {

	}

	public void startButtonPressed(ActionEvent ev) {
		mcService.startLearning();
	}

	public ResourceBundle getMsg() {
		return msg;
	}

	public void setMsg(ResourceBundle msg) {
		this.msg = msg;
	}

	public String getStepMsg() {
		return stepMsg;
	}

	public void setStepMsg(String stepMsg) {
		this.stepMsg = stepMsg;
	}

	public Integer getStepProgress() {
		return stepProgress;
	}

	public void setStepProgress(Integer stepProgress) {
		this.stepProgress = stepProgress;
	}

	public Integer getAllProgress() {
		return allProgress;
	}

	public void setAllProgress(Integer allProgress) {
		this.allProgress = allProgress;
	}
}

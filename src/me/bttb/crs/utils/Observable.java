package me.bttb.crs.utils;

public class Observable extends java.util.Observable {
	public void forceNotifyAllObservers() {
		this.setChanged();
		this.notifyObservers();
	}
}

package me.bttb.crs.beans;

import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

public interface MetaDataTableEventHandler {

	/////////////////////////// EVENTS////////////////////////////
	void onRefreshButtonClicked(ActionEvent event);

	void onAddNewDialogOpen(ActionEvent event);

	void onEditSelectedDialogOpen(ActionEvent event);

	void onSaveButtonClicked(ActionEvent event);

	void onCancelButtonClicked(ActionEvent event);

	void onRowSelect(SelectEvent event);

	void onRowUnSelect(UnselectEvent event);

}
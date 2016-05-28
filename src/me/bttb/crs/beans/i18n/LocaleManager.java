package me.bttb.crs.beans.i18n;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class LocaleManager implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7519441737704690913L;
	private Locale locale;

	@PostConstruct
	public void init() {
		locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
	}

	public Locale getLocale() {
		return locale;
	}

	public String getLanguage() {
		return locale.getLanguage();
	}

	public void setLanguage(String language) {
		locale = new Locale(language);
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	}

	public String getMediumLocaleDateFormat() {
		SimpleDateFormat f = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
		return f.toPattern();
	}
}
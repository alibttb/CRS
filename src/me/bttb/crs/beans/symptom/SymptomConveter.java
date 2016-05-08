package me.bttb.crs.beans.symptom;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.servlet.ServletContext;

import org.springframework.web.context.support.WebApplicationContextUtils;

import me.bttb.crs.model.Symptom;

@FacesConverter("symptomConveter")
public class SymptomConveter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0 && !value.equals("-1")) {
			try {
				ExternalContext externalContext = context.getExternalContext();
				ServletContext servletContext = (ServletContext) externalContext.getContext();
				SymptomService service = (SymptomService) WebApplicationContextUtils
						.getRequiredWebApplicationContext(servletContext).getBean("symptomService");
				return service.getAllSymptoms().stream().filter(sm -> value.equals(sm.getId() + "")).findFirst().get();
			} catch (NumberFormatException e) {
				throw new ConverterException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid symptom."));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return value instanceof Symptom ? ((Symptom) value).getId() + "" : "-1";
	}

}

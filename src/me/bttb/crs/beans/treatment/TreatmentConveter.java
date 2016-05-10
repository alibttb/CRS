package me.bttb.crs.beans.treatment;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.servlet.ServletContext;

import org.springframework.web.context.support.WebApplicationContextUtils;

import me.bttb.crs.model.Treatment;

@FacesConverter("treatmentConveter")
public class TreatmentConveter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0 && !value.equals("-1")) {
			try {
				ExternalContext externalContext = context.getExternalContext();
				ServletContext servletContext = (ServletContext) externalContext.getContext();
				TreatmentService service = (TreatmentService) WebApplicationContextUtils
						.getRequiredWebApplicationContext(servletContext).getBean("treatmentService");
				return service.getAllTreatments().stream().filter(sm -> value.equals(sm.getId() + "")).findFirst()
						.get();
			} catch (NumberFormatException e) {
				throw new ConverterException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid Treatment."));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return value instanceof Treatment ? ((Treatment) value).getId() + "" : "-1";
	}

}

package me.bttb.crs.beans.diagnosis;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.servlet.ServletContext;

import org.springframework.web.context.support.WebApplicationContextUtils;

import me.bttb.crs.model.Diagnosis;

@FacesConverter("diagnosisConveter")
public class DiagnosisConveter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0 && !value.equals("-1")) {
			try {
				ExternalContext externalContext = context.getExternalContext();
				ServletContext servletContext = (ServletContext) externalContext.getContext();
				DiagnosisService service = (DiagnosisService) WebApplicationContextUtils
						.getRequiredWebApplicationContext(servletContext).getBean("diagnosisService");
				return service.getAllDiagnoses().stream().filter(sm -> value.equals(sm.getId() + "")).findFirst()
						.get();
			} catch (NumberFormatException e) {
				throw new ConverterException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid Diagnosis."));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return value instanceof Diagnosis ? ((Diagnosis) value).getId() + "" : "-1";
	}

}

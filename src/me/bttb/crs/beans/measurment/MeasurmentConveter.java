package me.bttb.crs.beans.measurment;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.servlet.ServletContext;

import org.springframework.web.context.support.WebApplicationContextUtils;

import me.bttb.crs.model.Measurment;

@FacesConverter("measurmentConveter")
public class MeasurmentConveter implements Converter, Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -4484359605998616789L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0 && !value.equals("-1")) {
			try {
				ExternalContext externalContext = context.getExternalContext();
				ServletContext servletContext = (ServletContext) externalContext.getContext();
				MeasurmentService service = (MeasurmentService) WebApplicationContextUtils
						.getRequiredWebApplicationContext(servletContext).getBean("measurmentService");
				return service.getAllMeasurments().stream().filter(sm -> value.equals(sm.getId() + "")).findFirst()
						.get();
			} catch (NumberFormatException e) {
				throw new ConverterException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid measurment."));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return value instanceof Measurment ? ((Measurment) value).getId() + "" : "-1";
	}

}

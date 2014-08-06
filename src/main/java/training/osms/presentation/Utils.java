package training.osms.presentation;

import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class Utils {
	public String getStyle(String parentClientId, String childClientId) {
		FacesMessage message = getFacesMessage(parentClientId, childClientId);
		if (message == null) {
			return null;
		} else {
			if (FacesMessage.SEVERITY_WARN.equals(message.getSeverity())) {		
				return "has-warning";
			} else if (FacesMessage.SEVERITY_ERROR.equals(message.getSeverity()) || FacesMessage.SEVERITY_FATAL.equals(message.getSeverity())) {
				return "has-error";
			} else {
				return null;
			}
		}
	}

	public String getMessage(String parentClientId, String childClientId) {
		FacesMessage message = getFacesMessage(parentClientId, childClientId);
		if (message == null) {
			return null;
		} else {
			return "<span class='help-block'>" + message.getSummary() + "</span>";
		}
	}
	
	public FacesMessage getFacesMessage(String parentClientId, String childClientId) {
		String clientId = parentClientId + ":" + childClientId;
		return getFacesMessage(clientId);
	}
	
	public FacesMessage getFacesMessage(String clientId) {
		FacesMessage message = null;
		FacesContext context = FacesContext.getCurrentInstance();				
		Iterator<FacesMessage> messages = context.getMessages(clientId);
		while (messages.hasNext()) {
			FacesMessage candidate = messages.next();
			if (message == null) {
				message = candidate;
			} else {
				if (message.getSeverity().compareTo(candidate.getSeverity()) < 0) {
					message = candidate;
				}				
			}
		}
		return message;
	}
	
	public String getAlerts() {
		return getAlerts(null);
	}

	public String getAlerts(String clientId) {
		StringBuilder builder = new StringBuilder();		
		FacesContext context = FacesContext.getCurrentInstance();				
		Iterator<FacesMessage> messages = context.getMessages(clientId);
		while (messages.hasNext()) {
			FacesMessage message = messages.next();
			appendMessage(builder, message);
		}
		return builder.toString();
	}

	private void appendMessage(StringBuilder builder, FacesMessage message) {
	}
}
package training.osms.presentation;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import training.osms.business.BusinessException;
import training.osms.business.CategoryController;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class NewCategory {
	private @Autowired
	CategoryController controller;

	private CategoryForm categoryForm;

	public NewCategory() {
		categoryForm = new CategoryForm();
	}

	public void saveCategory() {
		String clientId;
		FacesMessage message = new FacesMessage();
		try {
			controller.saveCategory(categoryForm.getCategory());
			clientId = null;
			message.setSummary("Category was successfully saved");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
		} catch (BusinessException e) {
			clientId = "form:category:name";
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(clientId, message);
	}

	public CategoryForm getCategoryForm() {
		return categoryForm;
	}

	public void setCategoryForm(CategoryForm categoryForm) {
		this.categoryForm = categoryForm;
	}

}
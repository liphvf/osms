package training.osms.presentation;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import training.osms.business.Category;
import training.osms.business.CategoryController;
import training.osms.business.BusinessException;

@ManagedBean
public class NewCategory {

	private Category category;

	public NewCategory() {

		category = new Category();
	}

	public Category getCategory() {

		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void saveCategory() {

		FacesMessage message = new FacesMessage();

		try {
			CategoryController controller = new CategoryController();
			controller.saveCategory(category);

			message.setSummary("Blog is saved");
			message.setSeverity(FacesMessage.SEVERITY_INFO);

		} catch (BusinessException e) {

			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);

		}

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("form:category:name", message);

	}

}

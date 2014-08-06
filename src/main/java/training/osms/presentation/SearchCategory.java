package training.osms.presentation;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import training.osms.business.BusinessException;
import training.osms.business.Category;
import training.osms.business.CategoryController;
import training.osms.business.CategorySearchOptions;

@Component
@Scope(WebApplicationContext.SCOPE_SESSION)
public class SearchCategory {
	private @Autowired
	CategoryController controller;
	private CategorySearchOptions options;
	private List<Category> result;
	private CategoryForm categoryForm;
	private boolean categoryDeleted;

	public SearchCategory() {
		reset();
	}

	public void reset() {
		options = new CategorySearchOptions();
		setCategoryForm(new CategoryForm());
		result = null;
	}

	public void setOptions(CategorySearchOptions options) {
		this.options = options;
	}

	public CategorySearchOptions getOptions() {
		return options;
	}

	public void setResult(List<Category> result) {
		this.result = result;
	}

	public List<Category> getResult() {
		return result;
	}

	public CategoryForm getCategoryForm() {
		return categoryForm;
	}

	public void setCategoryForm(CategoryForm categoryForm) {
		this.categoryForm = categoryForm;
	}

	public void setCategoryDeleted(boolean categoryDeleted) {
		this.categoryDeleted = categoryDeleted;
	}

	public boolean getCategoryDeleted() {
		return categoryDeleted;
	}

	public void search() {
		result = controller.searchCategory(options);
	}
	
	public String update(Category category) {		
		Category categoryAux = category.clone();
		this.categoryForm = new CategoryForm();

		categoryAux.setId(category.getId());
		categoryAux.setName(category.getName());
		categoryAux.setDescription(category.getDescription());
			
		this.categoryForm.setCategory(categoryAux);
		return "updateCategory";
	}
	


	public void confirmUpdate() {
		String cliendId;
		FacesMessage message = new FacesMessage();
		try {
			controller.updateCategory(categoryForm.getCategory());
			reset();
			cliendId = null;
			message.setSummary("Category was successfully updated");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
		} catch (BusinessException e) {
			cliendId = "form:category:name";
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(cliendId, message);
	}

	public String delete(Category category) {
		this.categoryForm.setCategory(category);
		this.categoryDeleted = false;
		return "deleteCategory";
	}

	public void confirmDeletion() {
		controller.deleteCategory(categoryForm.getCategory());
		categoryDeleted = true;
		reset();
		FacesMessage message = new FacesMessage();
		message.setSummary("Category deleted");
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
	}
	
	
}

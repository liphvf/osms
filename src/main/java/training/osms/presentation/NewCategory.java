package training.osms.presentation;

import javax.faces.bean.ManagedBean;

import training.osms.business.Category;
import training.osms.business.CategoryController;

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

		CategoryController controller = new CategoryController();

		controller.saveCategory(category);
	}

}

package training.osms.presentation;

import java.util.List;

import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import training.osms.business.Category;
import training.osms.business.CategoryController;
import training.osms.business.CategorySearchOptions;

public class CategoryForm {

	public CategoryForm() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ApplicationContext applicationContext = FacesContextUtils
				.getWebApplicationContext(facesContext);

		CategoryController controller = applicationContext
				.getBean(CategoryController.class);
		categories = controller.searchCategory(new CategorySearchOptions());
		category = new Category();

	}

	private List<Category> categories;

	private Category category;

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Integer getFatherCategoryId() {
		Category fatherCategory = category.getFatherCategory();
		if (null == fatherCategory) {
			return null;
		}
		return fatherCategory.getId();
	}

	public void setFatherCategoryId(Integer fatherCategoryId) {
		if (null == fatherCategoryId) {
			category.setFatherCategory(null);
		} else {
			for (Category fatherCategory : categories) {
				if (fatherCategory.getId() == fatherCategoryId) {
					category.setFatherCategory(fatherCategory);
					break;
				}
			}
		}
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}

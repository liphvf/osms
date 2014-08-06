package training.osms.presentation;

import java.util.List;

import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import training.osms.business.Category;
import training.osms.business.CategoryController;
import training.osms.business.CategorySearchOptions;
import training.osms.business.Product;

public class ProductForm {
	private List<Category> categories;
	private Product product;	
	
	public ProductForm() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ApplicationContext applicationContext = FacesContextUtils.getWebApplicationContext(facesContext);		
		
		CategoryController controller = applicationContext.getBean(CategoryController.class);
		categories = controller.searchCategory(new CategorySearchOptions());
		
		
		product = new Product();
	}
	
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	public List<Category> getCategories() {
		return categories;
	}
	
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setCategoryId(Integer categoryId) {
		if (categoryId == null) {
			product.setCategory(null);
		} else {
			for (Category category : categories) {
				if (category.getId().equals(categoryId)) {
					product.setCategory(category);
					break;
				}
			}
		}
	}
	
	public Integer getCategoryId() {
		Category category = product.getCategory();
		if (category == null) {
			return null;
		} else {
			return category.getId();
		}
	}

}
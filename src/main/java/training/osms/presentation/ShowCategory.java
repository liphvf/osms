package training.osms.presentation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import training.osms.business.Category;
import training.osms.business.CategoryController;
import training.osms.business.CategorySearchOptions;
import training.osms.business.Product;
import training.osms.business.ProductController;
import training.osms.business.ProductSearchOptions;
@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ShowCategory {
	private @Autowired CategoryController controller;
	private @Autowired ProductController productController;
	private int categoryId;
	private Category category;
	private List<Product> products;
	
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
		
		CategorySearchOptions options = new CategorySearchOptions();
		options.setId(categoryId);
		List<Category> categories = controller.searchCategory(options);
		if (categories.size() > 0) {
			category = categories.get(0);
			
			ProductSearchOptions productOptions = new ProductSearchOptions();
			productOptions.setCategoryId(categoryId);
	//		productOptions.setOrder(Order.CREATION_DATE);
			productOptions.setDesc(true);			
			products = productController.searchProduct(productOptions);			
		}
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public List<Product> getProducts() {
		return products;
	}
	
	public String getProductDescriptionPreview(Product product) {
		StringBuilder builder = new StringBuilder(); 
		for (int i = 0; i < product.getDescription().length(); ++i) {
			char c = product.getDescription().charAt(i);
			if (c == '\n' || c == '\r') {
				break;
			} else {
				builder.append(c);
			}
		}
		return builder.toString();
	}
	
	public List<Category> getMenuFatherCategory() {
		List<Category> categories = controller
				.searchCategory(new CategorySearchOptions());
		List<Category> fathers = new ArrayList<Category>();
		for (Category category : categories) {
			if (null == category.getFatherCategory()) {
				fathers.add(category);
			}
		}
		return fathers;
	}
	
	
}
package training.osms.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.SetFactoryBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import training.osms.business.BusinessException;
import training.osms.business.Category;
import training.osms.business.Product;
import training.osms.business.ProductController;
import training.osms.business.ProductSearchOptions;

@Component
@Scope(WebApplicationContext.SCOPE_SESSION)
public class SearchProduct {
	private static final int RESULTS_PER_PAGE = 2;

	private @Autowired
	ProductController controller;
	private ProductSearchOptions options;
	private List<Integer> pages;
	private int page;
	private List<Product> result;
	private ProductForm productForm;
	private CategoryForm categoryForm;
	private boolean productDeleted;

	public SearchProduct() {
		reset();
	}

	public void reset() {
		setProductForm(new ProductForm());
		setCategoryForm(new CategoryForm());
		options = new ProductSearchOptions();
		result = null;
	}

	public void setOptions(ProductSearchOptions options) {
		this.options = options;
	}

	public ProductSearchOptions getOptions() {
		return options;
	}

	public void setPages(List<Integer> pages) {
		this.pages = pages;
	}

	public List<Integer> getPages() {
		return pages;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPage() {
		return page;
	}

	public void setResult(List<Product> result) {
		this.result = result;
	}

	public List<Product> getResult() {
		return result;
	}

	public ProductForm getProductForm() {
		return productForm;
	}

	public void setProductForm(ProductForm productForm) {
		this.productForm = productForm;
	}

	public void setProductDeleted(boolean productDeleted) {
		this.productDeleted = productDeleted;
	}

	public boolean getProductDeleted() {
		return productDeleted;
	}

	public void search() {
		int resultCount = controller.searchProductCount(options);
		int pageCount = resultCount / RESULTS_PER_PAGE;
		if (resultCount % RESULTS_PER_PAGE > 0) {
			++pageCount;
		}
		pages = new ArrayList<Integer>();
		for (int page = 1; page <= pageCount; ++page) {
			pages.add(page);
		}
		goToPage(1);
	}

	public void goToPage(int page) {
		this.page = page;

		int startPosition = (page - 1) * RESULTS_PER_PAGE;
		options.setStartPosition(startPosition);
		options.setMaxResults(RESULTS_PER_PAGE);
		result = controller.searchProduct(options);
	}

	public String getPageClass(int page) {
		if (page == this.page) {
			return "active";
		} else {
			return "";
		}
	}

//	public String update(Product product) {
//
//		Product productAux = product.clone();
//
//		productAux.setId(product.getId());
//		productAux.setName(product.getName());
//		productAux.setDescription(product.getDescription());
//		productAux.setCategory(product.getCategory());
//		productAux.setImg(product.getImg());
//		productAux.setPrice(product.getPrice());
//
//		this.productForm = new ProductForm();
//		this.productForm.setProduct(productAux);
//		return "updateProduct";
//	}

	 public String update(Product product) {
	 ProductSearchOptions options = new ProductSearchOptions();
	 options.setProductId(product.getId());
	 Product productAux = controller.searchProduct(options).get(0);
	 
	 this.productForm.setProduct(productAux);
	 return "updateProduct";
	 }

	public void confirmUpdate() {
		String cliendId;
		FacesMessage message = new FacesMessage();
		try {
			controller.updateProduct(productForm.getProduct());
			reset();
			cliendId = null;
			message.setSummary("Product was successfully updated");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
		} catch (BusinessException e) {
			cliendId = "form:product:name";
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(cliendId, message);
	}

	public String delete(Product product) {
		ProductSearchOptions options = new ProductSearchOptions();
		options.setProductId(product.getId());
		Product productAux = controller.searchProduct(options).get(0);

		this.productForm = new ProductForm();
		this.productForm.setProduct(productAux);
		this.productDeleted = false;
		return "deleteProduct";
	}

	public void confirmDeletion() {
		controller.deleteProduct(productForm.getProduct());
		productDeleted = true;
		reset();
		FacesMessage message = new FacesMessage();
		message.setSummary("Product was successfully deleted");
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
	}

	public CategoryForm getCategoryForm() {
		return categoryForm;
	}

	public void setCategoryForm(CategoryForm categoryForm) {
		this.categoryForm = categoryForm;
	}

}
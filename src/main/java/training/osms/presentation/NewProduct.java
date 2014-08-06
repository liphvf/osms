package training.osms.presentation;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import training.osms.business.BusinessException;
import training.osms.business.ProductController;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class NewProduct {
	private @Autowired
	ProductController controller;
	private ProductForm productForm;

	public NewProduct() {
		productForm = new ProductForm();
	}

	public ProductForm getProductForm() {
		return productForm;
	}

	public void setProductForm(ProductForm productForm) {
		this.productForm = productForm;
	}

	public void saveProduct() {
		String clientId;
		FacesMessage message = new FacesMessage();
		try {
			controller.saveProduct(productForm.getProduct());
			clientId = null;
			message.setSummary("Product saved");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
		} catch (BusinessException e) {
			clientId = "form:product:name";
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(clientId, message);
	}
}
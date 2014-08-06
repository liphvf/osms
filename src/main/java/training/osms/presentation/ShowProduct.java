package training.osms.presentation;

import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import training.osms.business.Product;
import training.osms.business.ProductController;
import training.osms.business.ProductSearchOptions;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ShowProduct {
	private @Autowired ProductController controller;
	private int productId;
	private Product product;
	
	public void setProductId(int productId) {
		this.productId = productId;
		
		ProductSearchOptions options = new ProductSearchOptions();
		options.setProductId(productId);
		List<Product> products = controller.searchProduct(options);
		if (products.size() > 0) {
			product = products.get(0);
		}
	}
	
	public int getProductId() {
		return productId;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public String getProductDescription() {
		String escapedDescription = StringEscapeUtils.escapeHtml(product.getDescription());

		StringBuilder description = new StringBuilder();
		description.append("<p>");
		escapedDescription = escapedDescription.replaceAll("[(\\n\\r)(\\n)(\\r)]+", "</p><p>");
		description.append(escapedDescription);
		description.append("</p>");
		return description.toString();
		
/*		
		for (int i = 0; i < escapedDescription.length(); ++i) {
			char c = escapedDescription.charAt(i);
			if (c == '\n' || c == '\r') {
				description.append("</p><p>");
			} else {
				description.append(c);
			}
		}
*/		
	}
}











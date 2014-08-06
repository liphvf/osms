package training.osms.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import training.osms.persistence.ProductDao;

@Component
public class ProductController {
	private @Autowired
	ProductDao dao;

	public void setDao(ProductDao dao) {
		this.dao = dao;
	}

	@Transactional
	public void saveProduct(Product product) {

		if (product.getCategory() == null) {
			throw new BusinessException("Category is not be null");
		} else {
			dao.insertProduct(product);
		}

	}

	public Integer searchProductCount(ProductSearchOptions options) {
		return dao.searchProductCount(options);
	}

	public List<Product> searchProduct(ProductSearchOptions options) {
		return dao.searchProduct(options);
	}

	@Transactional
	public void updateProduct(Product product) {
		dao.updateProduct(product);
	}

	@Transactional
	public void deleteProduct(Product product) {
		dao.deleteProduct(product);
	}
}
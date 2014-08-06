package training.osms.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import training.osms.business.Product;
import training.osms.business.ProductSearchOptions;

@Component
public class ProductDao {
	private @PersistenceContext
	EntityManager manager;

	public void insertProduct(Product product) {
		manager.persist(product);
	}

	public int searchProductCount(ProductSearchOptions options) {
		StringBuilder predicate = toPredicate(options);
		String jpql = "select count(product) from Product product where "
				+ predicate;
		TypedQuery<Long> query = manager.createQuery(jpql, Long.class);
		setParameters(options, query);
		Long result = query.getSingleResult();
		return result.intValue();
	}

	private StringBuilder toPredicate(ProductSearchOptions options) {
		StringBuilder predicate = new StringBuilder("1 = 1");
		if (options.getProductId() != null) {
			predicate.append(" and product.id = :productId");
		}
		if (options.getCategoryId() != null && options.getCategoryId() > 0) {
			predicate.append(" and product.category.id = :categoryId");
		}
		if (options.getName() != null && options.getName().length() > 0) {
			predicate.append(" and upper(product.name) like :productName");
		}
		if (options.getDescription() != null
				&& options.getDescription().length() > 0) {
			predicate
					.append(" and upper(product.description) like :productDescription");
		}

		if (options.getProductPriceMin() != null && options.getProductPriceMin() > 0) {
			predicate.append(" and product.price >= :productPriceMin");
		}

		if (options.getProductPriceMax() != null && options.getProductPriceMax() > 0) {
			predicate.append(" and product.price <= :productPriceMax");
		}

		return predicate;
	}

	private void setParameters(ProductSearchOptions options, TypedQuery<?> query) {
		if (options.getProductId() != null) {
			query.setParameter("productId", options.getProductId());
		}
		if (options.getCategoryId() != null && options.getCategoryId() > 0) {
			query.setParameter("categoryId", options.getCategoryId());
		}
		if (options.getName() != null && options.getName().length() > 0) {
			query.setParameter("productName", "%"
					+ options.getName().toUpperCase() + "%");
		}
		if (options.getDescription() != null
				&& options.getDescription().length() > 0) {
			query.setParameter("productDescription", "%"
					+ options.getDescription().toUpperCase() + "%");
		}

		if (options.getProductPriceMin() != null && options.getProductPriceMin() > 0) {
			query.setParameter("productPriceMin", options.getProductPriceMin());
		}

		if (options.getProductPriceMax() != null && options.getProductPriceMax() > 0) {
			query.setParameter("productPriceMax", options.getProductPriceMax());
		}

	}

	public List<Product> searchProduct(ProductSearchOptions options) {
		StringBuilder predicate = toPredicate(options);
		if (options.getOrder() != null) {
			predicate.append(" order by product.");
			predicate.append(options.getOrder().getValue());
			if (options.getDesc()) {
				predicate.append(" desc");
			}
		}
		String jpql = "select product from Product product where " + predicate;
		TypedQuery<Product> query = manager.createQuery(jpql, Product.class);
		setParameters(options, query);
		if (options.getStartPosition() != null) {
			query.setFirstResult(options.getStartPosition());
		}
		if (options.getMaxResults() != null) {
			query.setMaxResults(options.getMaxResults());
		}
		List<Product> result = query.getResultList();
		return result;
	}

	public void updateProduct(Product product) {
		manager.merge(product);
	}

	public void deleteProduct(Product product) {
		Product managedProduct = manager.find(Product.class, product.getId());
		manager.remove(managedProduct);
	}
}
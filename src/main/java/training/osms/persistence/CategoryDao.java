package training.osms.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import training.osms.business.Category;
import training.osms.business.CategorySearchOptions;

@Component
public class CategoryDao {
	private @PersistenceContext
	EntityManager manager;

	public void insertCategory(Category category) {
		manager.persist(category);
	}

	public List<Category> searchCategory(CategorySearchOptions options) {
		StringBuilder predicate = new StringBuilder("1 = 1");
		
		if (options.getFatherCategory() != null && options.getFatherCategory() > 0) {
			predicate.append(" and category.fatherCategory.id = :fatherCategory");
		}
		if (options.getId() != null) {
			predicate.append(" and category.id = :categoryId");
		}

		if (options.getName() != null && options.getName().length() > 0) {
			predicate.append(" and upper(category.name) like :categoryName");
		}
		
		if (options.getDescription() != null
				&& options.getDescription().length() > 0) {
			predicate
					.append(" and upper(category.description) like :categoryDescription");
		}
		String jpql = "select category from Category category where "
				+ predicate;
		TypedQuery<Category> query = manager.createQuery(jpql, Category.class);
		
		if (options.getFatherCategory() != null && options.getFatherCategory() > 0) {
			query.setParameter("fatherCategory", options.getFatherCategory());
		}
		
		if (options.getId() != null) {
			query.setParameter("categoryId", options.getId());
		}
		
		
		if (options.getName() != null && options.getName().length() > 0) {
			query.setParameter("categoryName", "%"
					+ options.getName().toUpperCase() + "%");
		}
		if (options.getDescription() != null
				&& options.getDescription().length() > 0) {
			query.setParameter("categoryDescription", "%"
					+ options.getDescription().toUpperCase() + "%");
		}
		List<Category> result = query.getResultList();
		return result;
	}

	public void updateCategory(Category category) {
		manager.merge(category);
	}

	public void deleteCategory(Category category) {
		Category managedCategory = manager.find(Category.class,
				category.getId());
		manager.remove(managedCategory);
	}

	public Category searchCategory(String categoryName) {
		TypedQuery<Category> query = manager
				.createQuery(
						"select category from training.osms.business.Category category where upper(category.name) = :categoryName",
						Category.class);
		query.setParameter("categoryName", categoryName.toUpperCase());
		List<Category> result = query.getResultList();
		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}


	}
}
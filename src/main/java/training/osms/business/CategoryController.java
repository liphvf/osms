package training.osms.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import training.osms.persistence.CategoryDao;

@Component
public class CategoryController {
	private @Autowired CategoryDao dao;
	
	public void setDao(CategoryDao dao) {
		this.dao = dao;
	}
	
	@Transactional
	public void saveCategory(Category category) {
		if (dao.searchCategory(category.getName()) != null) {
			throw new BusinessException("This category named " + category.getName() + " already");
		} else {
			dao.insertCategory(category);
		}
	}

	public List<Category> searchCategory(CategorySearchOptions options) {
		return dao.searchCategory(options);
	}

	@Transactional
	public void updateCategory(Category category) {
		Category databaseCategory = dao.searchCategory(category.getName());
		if (databaseCategory == null) {
			dao.updateCategory(category);
		} else {
			if (category.getId().equals(databaseCategory.getId())) {
				dao.updateCategory(category);
			} else {
				throw new BusinessException("This category named " + category.getName() + " already");
			}
		}
	}
	
	@Transactional
	public void deleteCategory(Category category) {
		dao.deleteCategory(category);
	}	
}
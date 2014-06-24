package training.osms.business;

import training.osms.persistence.CategoryDao;

public class CategoryController {

	public void saveCategory(Category category) {
		CategoryDao dao = new CategoryDao();

		if (dao.searchCategory(category.getName()) == null) {
			throw new BusinessException("This category name: "
					+ category.getName() + " Already");
		} else {
			dao.saveCategory(category);
		}

	}

}

package training.osms.business;

import training.osms.persistence.CategoryDao;

public class CategoryController {
	
	
	

	public void saveCategory(Category category) {
	
		
	CategoryDao dao = new CategoryDao();
	
	dao.saveCategory(category);
	
	}

}

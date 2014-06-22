package training.osms.presentation;

import javax.faces.bean.ManagedBean;

import training.osms.business.Category;

@ManagedBean
public class NewCategory {
	
	private Category category;
	
	public NewCategory() {

	category = new Category();
	category.setName("lalala");
	}

	public Category getCategory() {
		
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
		System.out.println(category);
	}

	@Override
	public String toString() {
		return "NewCategory [category=" + category + "]";
	}
	
	
	
	
	

}

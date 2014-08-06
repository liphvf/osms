package training.osms.business;

import java.util.List;

public class CategorySearchOptions {
	private Integer id;
	private String name;
	private String description;
	private Integer fatherCategory;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getFatherCategory() {
		return fatherCategory;
	}

	public void setFatherCategory(Integer fatherCategory) {
		this.fatherCategory = fatherCategory;
	}



}
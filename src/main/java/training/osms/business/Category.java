package training.osms.business;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CAT_CATEGORY")
public class Category implements Cloneable {
	private Integer id;
	private String name;
	private String description;
	private List<Product> products;
	private List<Category> subCategory;
	private Category fatherCategory;

	public Category() {
		products = new ArrayList<>();
		subCategory = new ArrayList<>();
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@OneToMany(mappedBy = "fatherCategory", cascade = CascadeType.REMOVE)
	public List<Category> getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(List<Category> subCategory) {
		this.subCategory = subCategory;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CAT_ID")
	public Integer getId() {
		return id;
	}

	@Size(min = 1, max = 100)
	@Column(name = "CAT_NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Size(min = 1, max = 1000)
	@Column(name = "CAT_DS")
	public String getDescription() {
		return description;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
	public List<Product> getProducts() {
		return products;
	}

	@Override
	public Category clone() {
		try {
			return (Category) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new Error(" A VM está louca!");
		}
	}

	@ManyToOne
	@JoinColumn(name = "CAT_FATHER_ID")
	public Category getFatherCategory() {
		return fatherCategory;
	}

	public void setFatherCategory(Category fatherCategory) {
		this.fatherCategory = fatherCategory;
	}

}
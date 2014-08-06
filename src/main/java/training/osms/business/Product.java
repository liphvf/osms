package training.osms.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "PRO_PRODUCT")
public class Product implements Cloneable {
	private Integer id;
	private String name;
	private String description;
	private Double price;
	private String img;
	private Category category;

	public void setId(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRO_ID")
	public Integer getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Size(min = 1, max = 100)
	@Column(name = "PRO_NAME")
	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Size(min = 1, max = 10000)
	@Column(name = "PRO_DESCRIPTION")
	public String getDescription() {
		return description;
	}

	@Column(name = "PRO_PRICE")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Size(min = 1, max = 100)
	@Column(name = "PRO_IMG")
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@ManyToOne
	@NotNull
	@JoinColumn(name = "CAT_ID")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public Product clone() {
		try {
			return (Product) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new Error(" A VM est� louca!");
		}
	}

}
package training.osms.business;

public class ProductSearchOptions {
	public enum Order {
		TITLE("name");

		private String value;

		private Order(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	private Integer productId;
	private Integer categoryId;
	private String name;
	private String description;
	private Double productPriceMin;
	private Double productPriceMax;
	private Order order;
	private boolean desc;
	private Integer startPosition;
	private Integer maxResults;

	public ProductSearchOptions() {
		order = Order.TITLE;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Order getOrder() {
		return order;
	}

	public void setDesc(boolean desc) {
		this.desc = desc;
	}

	public boolean getDesc() {
		return desc;
	}

	public void setStartPosition(Integer startPosition) {
		this.startPosition = startPosition;
	}

	public Integer getStartPosition() {
		return startPosition;
	}

	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}

	public Integer getMaxResults() {
		return maxResults;
	}

	public Double getProductPriceMin() {
		return productPriceMin;
	}

	public void setProductPriceMin(Double productPriceMin) {
		this.productPriceMin = productPriceMin;
	}

	public Double getProductPriceMax() {
		return productPriceMax;
	}

	public void setProductPriceMax(Double productPriceMax) {
		this.productPriceMax = productPriceMax;
	}


}
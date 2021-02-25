package com.ristoapp.models;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="category")
	private ProductCategory category;
	
	@Column(name = "price_amount", precision = 8, scale = 2)
	private BigDecimal priceAmount;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "product_ingredients", 
				joinColumns = @JoinColumn(name = "product_id"), 
				inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
	private List<Ingredient> ingredients;
	
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name="product_id")
	private List<Review> reviews;
	
	@Column(name = "average_reviews_number")
	private double averageReviewsNumber;
	
	@Column(name = "picByte", length = 1000)
	private byte[] picByte;
	
	public Product() {
		
	}
	
	public Product(String name, String description, ProductCategory category, BigDecimal priceAmount, List<Ingredient> ingredients,
			List<Review> reviews, double averageReviewsNumber, byte[] picByte) {
		this.name = name;
		this.description = description;
		this.category = category;
		this.priceAmount = priceAmount;
		this.ingredients = ingredients;
		this.reviews = reviews;
		this.averageReviewsNumber = averageReviewsNumber;
		this.picByte = picByte;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public BigDecimal getPriceAmount() {
		return priceAmount;
	}

	public void setPriceAmount(BigDecimal priceAmount) {
		this.priceAmount = priceAmount;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public double getAverageReviewsNumber() {
		return averageReviewsNumber;
	}

	public void setAverageReviewsNumber(double averageReviewsNumber) {
		this.averageReviewsNumber = averageReviewsNumber;
	}

	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", category=" + category
				+ ", priceAmount=" + priceAmount + ", ingredients=" + ingredients + ", reviews=" + reviews
				+ ", averageReviewsNumber=" + averageReviewsNumber + ", picByte=" + Arrays.toString(picByte) + "]";
	}
}

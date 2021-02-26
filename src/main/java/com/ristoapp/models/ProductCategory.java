package com.ristoapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ristoapp.enums.ProductCategoryEnum;
import com.ristoapp.entityResolver.EntityIdResolver;

@Entity
@Table(name = "product_categories")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope=ProductCategory.class )
public class ProductCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ProductCategoryEnum name;

	public ProductCategory() {
	}

	public ProductCategory(ProductCategoryEnum name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProductCategoryEnum getName() {
		return name;
	}

	public void setName(ProductCategoryEnum name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ProductCategory [id=" + id + ", name=" + name + "]";
	}
}

package com.ang.rental.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "category")
public class CategoryModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;
	private String category;
	@OneToMany(mappedBy = "category")
	@JsonIgnore
	private List<ListingModel> listingModel = new ArrayList<>();
	
	public CategoryModel(String category, List<ListingModel> listingModel) {
		super();
		this.category = category;
		this.listingModel = listingModel;
	}

	public CategoryModel() {
		super();
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<ListingModel> getListingModel() {
		return listingModel;
	}

	public void setListingModel(List<ListingModel> listingModel) {
		this.listingModel = listingModel;
	}

	@Override
	public String toString() {
		return "CategoryModel [categoryId=" + categoryId + ", category=" + category + ", listingModel=" + listingModel
				+ "]";
	}
	
}

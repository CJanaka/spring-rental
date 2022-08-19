package com.ang.rental.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "listId")
@Table(name = "listing")
public class ListingModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long listId;
	private String item;
	private String heading;
	private int contact;
	private String description;
	private String address;
	@ManyToOne
	@JoinColumn(name = "userId")
	private UserModel userModel;
	@OneToMany(targetEntity = ListingImagesModel.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "listId", referencedColumnName = "listId")
	private List<ListingImagesModel> listingImagesModel;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "priceId", referencedColumnName = "priceId")
	private PriceModel priceModel;
	@ManyToOne
	@JoinColumn(name = "categoryId")
	private CategoryModel category;

	public ListingModel() {
		super();

	}
	
	public ListingModel(String item, String heading, String description, UserModel userModel,
			List<ListingImagesModel> listingImagesModel, PriceModel priceModel, CategoryModel category, int contact, String address) {
		super();
		this.item = item;
		this.heading = heading;
		this.description = description;
		this.userModel = userModel;
		this.listingImagesModel = listingImagesModel;
		this.priceModel = priceModel;
		this.category = category;
		this.contact = contact;
		this.address = address;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getContact() {
		return contact;
	}

	public void setContact(int contact) {
		this.contact = contact;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public CategoryModel getCategory() {
		return category;
	}

	public void setCategory(CategoryModel category) {
		this.category = category;
	}

	public PriceModel getPriceModel() {
		return priceModel;
	}

	public void setPriceModel(PriceModel priceModel) {
		this.priceModel = priceModel;
	}

	public List<ListingImagesModel> getListingImagesModel() {
		return listingImagesModel;
	}

	public void setListingImagesModel(List<ListingImagesModel> listingImagesModel) {
		this.listingImagesModel = listingImagesModel;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String headding) {
		this.heading = headding;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public long getListId() {
		return listId;
	}

	public void setListId(long listId) {
		this.listId = listId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	@Override
//	public String toString() {
//		return "ListingModel [listId=" + listId + ", item=" + item + ", heading=" + heading + ", contact=" + contact
//				+ ", description=" + description + ", userModel=" + userModel + ", listingImagesModel="
//				+ listingImagesModel + ", priceModel=" + priceModel + ", category=" + category + "]";
//	}
}

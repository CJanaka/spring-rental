package com.ang.rental.model;

import java.util.Base64;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.util.Base64Utils;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "imageId")
@Table(name = "listingimages")
public class ListingImagesModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long imageId;
	private byte[] image;
	private String imgType;
	@ManyToOne
//	@JsonIgnore
//	@JsonBackReference
	@JoinColumn(name = "listId", referencedColumnName = "listId")
	private ListingModel listingModel;
	
	public ListingImagesModel(byte[] image, String imgType, ListingModel listingModel) {
		super();
		this.image = image;
		System.out.println(image);
		this.imgType = imgType;
		this.listingModel = listingModel;
	}
	
	public ListingImagesModel() {
		super();
	}

	public ListingModel getListingModel() {
		return listingModel;
	}

	public void setListingModel(ListingModel listingModel) {
		this.listingModel = listingModel;
	}

	public long getImageId() {
		return imageId;
	}
	public void setImageId(long imageId) {
		this.imageId = imageId;
	}
	public String getImage() {
		return new String(Base64Utils.decode(image));
		
	}
	public void setImage(String image) {
		this.image = Base64.getEncoder().encode(image.getBytes());
	}

	public String getImgType() {
		return imgType;
	}

	public void setImgType(String imgType) {
		this.imgType = imgType;
	}
}

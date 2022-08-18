package com.ang.rental.model;

import java.util.Base64;

import org.springframework.util.Base64Utils;

public class DisplayListing {

	private long listId;
	private String item;
	private String description;
	private String heading;
	private String timePeriod;
	private int price;
	private String imageType;
	private byte[] image;
	private int contact;
	private String additionalFeeDescription;
	private int additionalFee;
	
	
	public DisplayListing() {
		super();
	}
	
	public DisplayListing(long listId, String item, String description, String timePeriod, int price, String heading, 
			String imageType, byte[] image, int contact, String additionalFeeDescription, int additionalFee) {
		super();
		this.listId = listId;
		this.item = item;
		this.description = description;
		this.heading = heading;
		this.timePeriod = timePeriod;
		this.price = price;
		this.imageType = imageType;
		this.image = image;
		this.contact = contact;
		this.additionalFeeDescription = additionalFeeDescription;
		this.additionalFee = additionalFee;
	}
	public long getListId() {
		return listId;
	}
	public void setListId(long listId) {
		this.listId = listId;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public String getTimePeriod() {
		return timePeriod;
	}
	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	
	public String getImage() {
		return new String(Base64Utils.decode(image));
	}
		
	public void setImage(String image) {
		this.image = Base64.getEncoder().encode(image.getBytes());
	}
	
	public int getContact() {
		return contact;
	}
	public void setContact(int contact) {
		this.contact = contact;
	}
	public String getAdditionalFeeDescription() {
		return additionalFeeDescription;
	}
	public void setAdditionalFeeDescription(String additionalFeeDescription) {
		this.additionalFeeDescription = additionalFeeDescription;
	}
	public int getAdditionalFee() {
		return additionalFee;
	}
	public void setAdditionalFee(int additionalFee) {
		this.additionalFee = additionalFee;
	}


}
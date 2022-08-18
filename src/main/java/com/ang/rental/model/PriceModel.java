package com.ang.rental.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "price")
public class PriceModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long priceId;
	private String timePeriod;
	private int price;
	private String additionalFeeDescription;
	private int additionalFees;
	@OneToOne(mappedBy = "priceModel")
	private ListingModel listingModel;
	
	public PriceModel(String timePeriod, int price, String additionalFeeDescription, int additionalFees, ListingModel listingModel) {
	
		this.timePeriod = timePeriod;
		this.price = price;
		this.additionalFeeDescription = additionalFeeDescription;
		this.additionalFees = additionalFees;
		this.listingModel = listingModel;
	}

	public PriceModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getPriceId() {
		return priceId;
	}

	public void setPriceId(long priceId) {
		this.priceId = priceId;
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

	public String getAdditionalFeeDescription() {
		return additionalFeeDescription;
	}

	public void setAdditionalFeeDescription(String additionalFeeDescription) {
		this.additionalFeeDescription = additionalFeeDescription;
	}

	public int getAdditionalFees() {
		return additionalFees;
	}

	public void setAdditionalFees(int additionalFees) {
		this.additionalFees = additionalFees;
	}

	public ListingModel getListingModel() {
		return listingModel;
	}

	public void setListingModel(ListingModel listingModel) {
		this.listingModel = listingModel;
	}
	
	
}

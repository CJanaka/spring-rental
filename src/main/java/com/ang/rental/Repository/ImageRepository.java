package com.ang.rental.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ang.rental.model.DisplayListing;
import com.ang.rental.model.ListingImagesModel;

public interface ImageRepository extends JpaRepository<ListingImagesModel, Long> {

	@Query("SELECT new com.ang.rental.model.DisplayListing(l.listId, l.item, l.description, p.timePeriod, p.price, l.heading, i.imgType, i.image, l.contact, p.additionalFeeDescription , p.additionalFees)"
			+ " FROM ListingImagesModel i JOIN i.listingModel l Join l.priceModel p WHERE i.imgType = 'M' ")
	public List<DisplayListing> getListWithMainImage();
}

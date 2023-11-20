package com.ang.rental.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ang.rental.model.DisplayListing;
import com.ang.rental.model.ListingModel;

public interface ListRepository extends JpaRepository<ListingModel, Long> {

	//not madnotory
	@Query("SELECT new com.ang.rental.model.DisplayListing(l.listId, l.item, l.description, p.timePeriod, p.price, l.heading, i.imgType, i.image, l.contact, p.additionalFeeDescription , p.additionalFees)"
			+ " FROM ListingModel l JOIN l.priceModel p JOIN l.listingImagesModel i WHERE item like lower(concat('%', :item,'%')) and i.imgType = 'M'")
	public List<DisplayListing> getDetailedListing(String item);

//	@Query("SELECT new com.ang.rental.model.DisplayListing(l.listId, l.item, l.description, p.timePeriod, p.price, l.heading, i.imgType, i.image, l.contact, p.additionalFeeDescription , p.additionalFees)"
//			+ " FROM ListingModel l JOIN l.priceModel p JOIN l.listingImagesModel i WHERE l.item LIKE %:item%") 
	public List<ListingModel> findByItemIgnoreCase(String item);
	
	public List<ListingModel> findByUserModelUserId(long userId);
	
	public List<ListingModel> findByCategoryCategoryIdAndListingImagesModelImgType(long categoryId, String imgType);
	
	@Query("SELECT distinct l.item FROM ListingModel l WHERE lower(l.item) LIKE (concat('%', :item,'%'))")
	public List<String> getByItemLike(String item);

}
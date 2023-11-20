package com.ang.rental.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ang.rental.Repository.ListRepository;
import com.ang.rental.model.DisplayListing;
import com.ang.rental.model.ListingModel;

@Service
public class ListingService {

	@Autowired
	private ListRepository listRepo;
		
	public ListingModel createListin(ListingModel listingModel) {
		String itemName = listingModel.getItem().toLowerCase();
		listingModel.setItem(itemName);

		return listRepo.save(listingModel);
	}

	public List<ListingModel> getAllListings(long id) {
		return listRepo.findByUserModelUserId(id);
	}
	
	public List<DisplayListing> displayListing(String item){
		return listRepo.getDetailedListing(item);
	}
	public ListingModel displayListingById(long id){
		return listRepo.findById(id).get();
	}
	
	public List<String> getKeyByRelease(String item){
		return listRepo.getByItemLike(item.toLowerCase());
	}
	
	public List<ListingModel> getByName(String item) {
		return listRepo.findByItemIgnoreCase(item);
	}

	public ListingModel update(long id, ListingModel listingModel) {
		ListingModel existingListingModel = listRepo.getById(id);
		existingListingModel.setCategory(listingModel.getCategory());
		existingListingModel.setContact(listingModel.getContact());
		existingListingModel.setDescription(listingModel.getDescription());
		existingListingModel.setHeading(listingModel.getHeading());
		existingListingModel.setItem(listingModel.getItem());
		existingListingModel.setAddress(listingModel.getAddress());
		existingListingModel.setListingImagesModel(listingModel.getListingImagesModel());
		existingListingModel.setPriceModel(listingModel.getPriceModel());
		return listRepo.save(existingListingModel);
	}

	public Optional<ListingModel> delete(long id) {
		listRepo.deleteById(id);
		return listRepo.findById(id);
	}

	public List<ListingModel> displayListByCategoryId(int categoryId) {
		return listRepo.findByCategoryCategoryIdAndListingImagesModelImgType(categoryId, "M");
	}
	
}

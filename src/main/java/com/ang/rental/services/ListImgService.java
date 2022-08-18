package com.ang.rental.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ang.rental.Repository.ImageRepository;
import com.ang.rental.model.DisplayListing;

@Service
public class ListImgService {

	@Autowired
	private ImageRepository imageRepo;
	
	public List<DisplayListing> getListWithMainImage(){
		return imageRepo.getListWithMainImage();
	}
}

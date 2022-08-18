package com.ang.rental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ang.rental.Repository.PriceRepository;
import com.ang.rental.model.PriceModel;

@Service
public class AddPriceService {

	@Autowired
	private PriceRepository priceRepo;

	public PriceModel setPrice(PriceModel priceModel) {		
		return priceRepo.save(priceModel);
	}
	
	
}

package com.ang.rental.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ang.rental.model.PriceModel;

public interface PriceRepository extends JpaRepository<PriceModel, Long>{

}

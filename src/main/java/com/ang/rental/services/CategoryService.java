package com.ang.rental.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ang.rental.Repository.CategoryRepository;
import com.ang.rental.model.CategoryModel;


@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepo;

	public CategoryModel setCategory(CategoryModel categoryModel) {
		return categoryRepo.save(categoryModel);
	}

	public List<CategoryModel> getCategories() {
		return (List<CategoryModel>) categoryRepo.findAll();
	}

	public CategoryModel update(long id, CategoryModel category) {
		CategoryModel existingcategory = categoryRepo.findById(id).get();
		existingcategory.setCategory(category.getCategory());
		return categoryRepo.save(existingcategory);
	}
	
	public void delete(long id) {
		categoryRepo.deleteById(id);
	}
}

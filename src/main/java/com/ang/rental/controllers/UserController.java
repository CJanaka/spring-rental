package com.ang.rental.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ang.rental.jwtutill.JwtUtil;
import com.ang.rental.model.AuthRequest;
import com.ang.rental.model.CategoryModel;
import com.ang.rental.model.DisplayListing;
import com.ang.rental.model.ListingModel;
import com.ang.rental.model.Roles;
import com.ang.rental.model.UserModel;
import com.ang.rental.services.CategoryService;
import com.ang.rental.services.ListImgService;
import com.ang.rental.services.ListingService;
import com.ang.rental.services.UserService;

@CrossOrigin(origins = "http://localhost:4200/**")
@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ListingService listingService;

	@Autowired
	private ListImgService listImage;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/auth")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		Authentication authentication;
		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		} catch (Exception e) {
			throw new Exception("Authentication Error "+ e);
		}
		return jwtUtil.generateToken(authentication, authRequest.getUserName());
	}

	@PostMapping("/add")
	public UserModel addUsers(@RequestBody UserModel userModel) {
		return userService.addUser(userModel);
	}

	@GetMapping("/auth/all/users")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<UserModel> getAllusers() {
		return userService.getAll();
	}

	@GetMapping("/find/{mail}")
	public UserModel getUserByMail(@PathVariable String mail) {
		return userService.validateUser(mail);
	}

	@GetMapping("/findby/id/{userId}")
	public Optional<UserModel> getById(@PathVariable("userId") long id) {
		return userService.userGetById(id);
	}

	@PostMapping("/update/user/{id}")
	public UserModel updateUse(@PathVariable("id") long id, @RequestBody UserModel userModel) {
		return userService.update(id, userModel);
	}

	@PostMapping("/auth/delete/user")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public void deleteUser(@RequestBody long id) {
		userService.delete(id);
	}

	@PostMapping("/auth/restrict/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public UserModel restrictUser(@PathVariable("id") long id, @RequestBody boolean restrict) {
		return userService.restrict(id, restrict);
	}

	@PostMapping("/auth/verify/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public UserModel verifyUser(@PathVariable("id") long id, @RequestBody boolean verify) {
		return userService.verify(id, verify);
	}

	@PostMapping("/create/listing")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")

	public ListingModel createList(@RequestBody ListingModel listingModel) {
		return listingService.createListin(listingModel);
	}

	@PostMapping("/auth/add/category")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public CategoryModel addCategory(@RequestBody CategoryModel categoryModel) {
		return categoryService.setCategory(categoryModel);
	}

	@GetMapping("/all/categories")
	public List<CategoryModel> getAllCategories() {
		return categoryService.getCategories();
	}

	@PostMapping("/update/category/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public CategoryModel updateCategory(@PathVariable("id") long id, @RequestBody CategoryModel category) {
		return categoryService.update(id, category);
	}

	@PostMapping("/delete/category")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public void deleteCategory(@RequestBody long id) {
		categoryService.delete(id);
	}

	@GetMapping("/all/listings/{id}")
	public List<ListingModel> allListings(@PathVariable("id") long userId) {
		return listingService.getAllListings(userId);
	}

	@PostMapping("/update/listing/{id}")
	public ListingModel updateListing(@PathVariable("id") long id, @RequestBody ListingModel listingModel) {
		return listingService.update(id, listingModel);
	}

	@PostMapping("/delete/listing")
	public Optional<ListingModel> removeListing(@RequestBody long id) {
		return listingService.delete(id);
	}

	@GetMapping("/listby/categoryid/{id}")
	public List<ListingModel> displayListingByCategoryId(@PathVariable("id") int categoryId) {
		return listingService.displayListByCategoryId(categoryId);
	}

	@GetMapping("/detailed/listing/{listId}")
	public ListingModel displayListingById(@PathVariable("listId") long id) {
		return listingService.displayListingById(id);
	}

	@GetMapping("/search/{item}")
	public List<String> getListKeyRelease(@PathVariable("item") String item) {
		return listingService.getKeyByRelease(item);
	}

	@GetMapping("/get/list/{name}")
	public List<ListingModel> search(@PathVariable("name") String name) {
		return listingService.getByName(name);
	}

	@GetMapping("/list/and/main/image")
	public List<DisplayListing> getListingWithMainImage() {
		return listImage.getListWithMainImage();
	}

}

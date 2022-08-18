package com.ang.rental.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rental")
public class UserController {

	public static final String DEFAULT_ROLE = "ROLE_USER";
	public static final String ADMIN_ROLE = "ROLE_ADMIN";

	@Autowired
	private UserService userService;

	@Autowired
	private ListingService listingService;

	@Autowired
	private ListImgService listImage;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/auth")
	public String genarateToken(@RequestBody AuthRequest authRequest) throws Exception {
		Authentication authentication;
		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		} catch (Exception e) {
			throw new Exception("Invalid user name");
		}
		return jwtUtil.generateToken(authentication, authRequest.getUserName());
	}

	@PostMapping("/add")
	public UserModel addusers(@RequestBody UserModel userModel) {
		String encryptedpassword = passwordEncoder.encode(userModel.getPassword());
//		Roles role = new Roles();
//		Set<Roles> roleList = new HashSet<>();
//		roleList.add(role);
//		role.setName(ADMIN_ROLE);

//		userModel.setRoles(roleList);
		userModel.setPassword(encryptedpassword);
		return userService.addUser(userModel);
	}

	@GetMapping("/all-users")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<UserModel> getAllusers() {
		return userService.getAll();
	}

	@GetMapping("/find/{mail}")
	public UserModel getUserByMail(@PathVariable String mail) {
		return userService.validateUser(mail);
	}

	@GetMapping("/findbyid/{userId}")
	public Optional<UserModel> getById(@PathVariable("userId") long id) {
		return userService.userGetById(id);
	}

	@PutMapping("/update_user/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public UserModel updateUse(@PathVariable("id") long id, @RequestBody UserModel userModel) {
		return userService.update(id, userModel);
	}

	@DeleteMapping("/delete-user")
	public void deleteUser(@PathVariable("id") long id) {
		userService.delete(id);
	}

	@PutMapping("/restrict/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public UserModel restrictUser(@PathVariable("id") long id, @RequestBody boolean restrict) {
		return userService.restrict(id, restrict);
	}

	@PutMapping("/verify/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public UserModel verfyUser(@PathVariable("id") long id, @RequestBody boolean varify) {
		return userService.verfy(id, varify);
	}

	@PostMapping("/create-listing")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ListingModel createList(@RequestBody ListingModel listingModel) {
		return listingService.createListin(listingModel);
	}

	@PostMapping("/add-category")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public CategoryModel addCategory(@RequestBody CategoryModel categoryModel) {
		return categoryService.setCategory(categoryModel);
	}

	@GetMapping("/all-categories")
	public List<CategoryModel> getAllCategories() {
		return categoryService.getCategories();
	}

	@PutMapping("/update-category/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public CategoryModel updateCategory(@PathVariable("id") long id, @RequestBody CategoryModel category) {
		return categoryService.update(id, category);
	}

	@DeleteMapping("/delete-category/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public void deleteCategory(@PathVariable("id") long id) {
		categoryService.delete(id);
	}

	@GetMapping("/all-listings/{id}")
	public List<ListingModel> allListings(@PathVariable("id") long userId) {
		return listingService.getAllListings(userId);
	}

	@PutMapping("/updatelisting/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ListingModel updateListing(@PathVariable("id") long id, @RequestBody ListingModel listingModel) {
		return listingService.update(id, listingModel);
	}

	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER') and hasAuthority('ROLE_ADMIN')")
	public Optional<ListingModel> removeListing(@PathVariable("id") long id) {
		return listingService.delete(id);
	}

	@GetMapping("/listbyname/{item}")
	public List<DisplayListing> displayListing(@PathVariable("item") String item) {
		return listingService.displayListing(item);
	}

	@GetMapping("/detailed-listing/{listId}")
	public ListingModel displayListingById(@PathVariable("listId") long id) {
		return listingService.displayListingById(id);
	}

	@GetMapping("/search/{item}")
	public List<String> getListByName(@PathVariable("item") String item) {
		return listingService.getkeyByRelease(item);
	}

	@GetMapping("/getlist/{name}")
	public List<ListingModel> search(@PathVariable("name") String name) {
		return listingService.getByName(name);
	}

	@GetMapping("/list_and_mainimage")
	public List<DisplayListing> getListingWithMainImage() {
		return listImage.getListWithMainImage();
	}

}

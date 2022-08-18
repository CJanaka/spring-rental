package com.ang.rental.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ang.rental.Repository.UserRepository;
import com.ang.rental.model.Roles;
import com.ang.rental.model.UserModel;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public UserModel addUser(UserModel userModel) {

		return userRepository.save(userModel);
	}

	public UserModel validateUser(String mail) {
		return userRepository.findByEmail(mail).get();
	}

	public Optional<UserModel> userGetById(long id) {
		return userRepository.findById(id);
	}

	public UserModel update(long id, UserModel userModel) {
		UserModel currentUser = userRepository.getById(id);
		currentUser.setFirstName(userModel.getFirstName());
		currentUser.setLastName(userModel.getLastName());
		currentUser.setEmail(userModel.getEmail());
		currentUser.setAddress(userModel.getAddress());
		currentUser.setContact(userModel.getContact());
		currentUser.setPassword(userModel.getPassword());
		return userRepository.save(currentUser);
	}

	public List<UserModel> getAll() {
		return userRepository.findAll();
	}

	public void delete(long id) {
		userRepository.deleteById(id);
	}

	public UserModel restrict(long id, boolean restrict) {
		UserModel existingUser = userRepository.findById(id).get();
		existingUser.setActive(restrict);
		return userRepository.save(existingUser);
	}

	public UserModel verfy(long id, boolean varify) {
		UserModel existingUser = userRepository.findById(id).get();
		existingUser.setVerify(varify);
		return userRepository.save(existingUser);
	}
}

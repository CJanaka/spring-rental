package com.ang.rental.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.ang.rental.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ang.rental.Repository.UserRepository;
import com.ang.rental.model.Roles;
import com.ang.rental.model.UserModel;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UserModel addUser(UserModel userModel) {
		String encryptedPassword = passwordEncoder.encode(userModel.getPassword());
		Roles role = new Roles();
		Set<Roles> roleList = new HashSet<>();
		roleList.add(role);
		role.setName(Common.DEFAULT_ROLE);

		userModel.setRoles(roleList);
		userModel.setPassword(encryptedPassword);
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

	public UserModel verify(long id, boolean varify) {
		UserModel existingUser = userRepository.findById(id).get();
		existingUser.setVerify(varify);
		return userRepository.save(existingUser);
	}
}

package com.ang.rental.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.ang.rental.Common;
import com.ang.rental.jwtutill.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ang.rental.Repository.UserRepository;
import com.ang.rental.model.Roles;
import com.ang.rental.model.UserModel;

@Service
public class UserService {

	private static final Logger log = LoggerFactory.getLogger(UserService.class);

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
		userRepository.save(userModel);

		//If the user is an Admin, below statuses will added by the system.
		Set<Roles> roles = userModel.getRoles();
		for (Roles rol: roles) {
			if (Common.ADMIN_ROLE.equals(rol.getName())){
				userModel.setActive(true);
				userModel.setVerify(true);
				userRepository.save(userModel);
			}
		}

		return userModel;
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
		if (userModel.getPassword() != null && !userModel.getPassword().isEmpty()){
			currentUser.setPassword(passwordEncoder.encode(userModel.getPassword()));
		}
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
		Set<Roles> roles = existingUser.getRoles();
		for (Roles role: roles) {
			if (Common.ADMIN_ROLE.equals(role.getName())){
				log.warn("[restrict] message = Admin can't restrict admin roles!");
				return existingUser;
			}
		}
		existingUser.setActive(restrict);
		return userRepository.save(existingUser);
	}

	public UserModel verify(long id, boolean varify) {
		UserModel existingUser = userRepository.findById(id).get();
		existingUser.setVerify(varify);
		return userRepository.save(existingUser);
	}
}

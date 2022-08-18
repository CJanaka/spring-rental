package com.ang.rental.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ang.rental.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
	Optional<UserModel> findByEmail(String mail);
}

package com.example.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.models.Roles;
import com.example.app.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User getSystemUserByUsername(String username);

	User findByUsername(String username);

	User findByHashCode(String hashCode);

	User findByEmail(String email);

	User deleteByEmail(String email);

	List<User> findByRoles(Roles roles);
}

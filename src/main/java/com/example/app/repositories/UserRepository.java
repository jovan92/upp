package com.example.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User getSystemUserByUsername(String username);

	User findByUsername(String username);

	User findByHashCode(String hashCode);

	User findByEmail(String email);
}

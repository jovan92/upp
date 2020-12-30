package com.example.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.models.Roles;

public interface RoleRepository extends JpaRepository<Roles, Long>{

	List<Roles> findByName(String name);

}

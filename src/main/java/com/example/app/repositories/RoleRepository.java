package com.example.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.models.Roles;

public interface RoleRepository extends JpaRepository<Roles, Long>{

	Roles findByName(String name);

}

package com.example.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.models.CheckRequest;

public interface CheckRequestRepository extends JpaRepository<CheckRequest, Long> {

}

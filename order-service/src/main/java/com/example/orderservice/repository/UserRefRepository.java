package com.example.orderservice.repository;

import com.example.orderservice.model.UserRef;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRefRepository extends JpaRepository<UserRef, Long> {
}

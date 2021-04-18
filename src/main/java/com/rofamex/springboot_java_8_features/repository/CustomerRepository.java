package com.rofamex.springboot_java_8_features.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rofamex.springboot_java_8_features.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
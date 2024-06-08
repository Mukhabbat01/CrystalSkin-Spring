package com.crystalskin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crystalskin.domain.Phone;

public interface PhoneRepository extends JpaRepository<Phone, String>{

	Optional<Phone> findById(String phone);
    boolean existsById(String phone);
}

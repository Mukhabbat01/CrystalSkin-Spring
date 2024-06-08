package com.crystalskin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crystalskin.domain.File;

public interface FileRepository extends JpaRepository<File, String>{

}

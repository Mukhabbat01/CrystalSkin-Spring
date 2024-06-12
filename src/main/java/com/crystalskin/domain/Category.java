package com.crystalskin.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category {
	@Id
	private String catId;
	
	private String brand;
	
	@OneToMany(mappedBy="category")
	private List<Product> product;
}

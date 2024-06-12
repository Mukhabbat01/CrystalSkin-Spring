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
public class SkinCat {
	@Id
	private String skinId;
	
	private String skinType;

//	@OneToMany(mappedBy="skinCat")
//	private List<Product> product;
	
	private String skinFile;
	
//	@OneToMany(mappedBy="skinCat")
//	private List<User> user;
}

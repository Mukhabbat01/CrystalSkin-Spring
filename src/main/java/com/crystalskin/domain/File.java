package com.crystalskin.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class File {
	@Id
	private String fileId;
	
	private String fileDes;
	
	
//	@OneToMany(mappedBy="file")
//	private List<Product> product;
	
	@ManyToOne()
	@JoinColumn(name="prd_id")
	private Product product;
}

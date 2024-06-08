package com.crystalskin.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prdId;
	
	@Column(length=60, nullable= false)
	private String title;
	
	@Column(length=700, nullable= false)
	private String des;
	
	private int price;
	
	
	@ManyToOne()
	@JoinColumn(name="cat_id")
	private Category category;
	
	@ManyToOne()
	@JoinColumn(name="skin_id")
	private SkinCat skinCat;
	
//	@ManyToOne()
//	@JoinColumn(name="file_id")
//	private File file;
	
	@OneToMany(mappedBy="product")
	private List<File> file;
	

}

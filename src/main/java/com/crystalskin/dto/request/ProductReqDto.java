package com.crystalskin.dto.request;

import com.crystalskin.domain.Category;
import com.crystalskin.domain.File;
import com.crystalskin.domain.Product;
import com.crystalskin.domain.SkinCat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductReqDto {
	
	private int prdId;
	private String title;
	private int price;
	private String catId;
	private String fileId;
	private String skinId;
	
	
	 public ProductReqDto(Product product) {
	        this.prdId = product.getPrdId();
	        this.title = product.getTitle();
	        
	        this.price = product.getPrice();
	        
	        Category category = new Category();
	        this.catId = category.getCatId();
	        
	        File file = new File();
	        this.fileId = file.getFileId();
	        
	        SkinCat  skinCat = new SkinCat();
	        this.skinId = skinCat.getSkinId();
	    }

}

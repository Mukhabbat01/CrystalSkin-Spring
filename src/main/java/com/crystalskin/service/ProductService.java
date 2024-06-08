package com.crystalskin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crystalskin.dto.response.ProductResDto;
import com.crystalskin.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;
	
	
    public List<ProductResDto> getAllProducts() { 
        return productRepository.findAllProducts();
    }


    public ProductResDto getProductById(int id) {
        return productRepository.findProductById(id);
    }
	
    
    public List<ProductResDto> productsForMbtiType(String usrId){
    	return productRepository.selectProductsForMbti(usrId);
    }
}

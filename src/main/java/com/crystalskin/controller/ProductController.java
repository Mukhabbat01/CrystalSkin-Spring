package com.crystalskin.controller;


import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.crystalskin.dto.response.FileResponse;
import com.crystalskin.dto.response.ProductResDto;
import com.crystalskin.service.FileService;
import com.crystalskin.service.ProductService;
import com.crystalskin.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;
	private final FileService fileService;
	private final UserService userService;

	@GetMapping("/products")
	public ResponseEntity<List<ProductResDto>> getAllProducts() {
		try {
			List<ProductResDto> products = productService.getAllProducts();
			return ResponseEntity.ok(products);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/uploads/{name}")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(@PathVariable("name") String fileId) {
		FileResponse res = fileService.getFile(fileId);
		if (res.getContentType() != null) {
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type", res.getContentType());
			header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileId + "\"");
			return new ResponseEntity<>(res.getBytes(), header, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/files/{fileDes}")
	@ResponseBody
	public ResponseEntity<byte[]> getFiles(@PathVariable("fileDes") String fileDes) {
		FileResponse res = fileService.getFiles(fileDes);
		if (res.getContentType() != null) {
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type", res.getContentType());
			header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDes + "\"");
			return new ResponseEntity<>(res.getBytes(), header, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	 @GetMapping("/products/{id}")
	    public ResponseEntity<ProductResDto> getProductById(@PathVariable("id") int id) {
	        try {
	            ProductResDto product = productService.getProductById(id);
	            if (product != null) {
	                return ResponseEntity.ok(product);
	            } else {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }
	
	 
	 @GetMapping("/productsForMbti")
	 public ResponseEntity<List<ProductResDto>> productsForMbti(@RequestHeader("Authorization") String accessToken){
		 accessToken = accessToken.substring(7);
		 String usrId = userService.getUserIdFromToken(accessToken);
		 return ResponseEntity.status(200).body(productService.productsForMbtiType(usrId));
	 }
}

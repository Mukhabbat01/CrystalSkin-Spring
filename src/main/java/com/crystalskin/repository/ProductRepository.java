package com.crystalskin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crystalskin.domain.Product;
import com.crystalskin.dto.response.ProductResDto;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query(value="SELECT distinct(p.prd_id), p.price, p.title, p.cat_id,  max(f.file_id) as file_id FROM product p "
			+ "left join file f on f.prd_id = p.prd_id "
			+ "group by p.prd_id, p.price, p.title, p.cat_id" , nativeQuery = true)
	List<ProductResDto> findAllProducts();

		
	
	 @Query(value = "SELECT distinct(p.prd_id), p.price, p.title, p.cat_id, max(f.file_id) as file_id, f.file_des FROM product p "
	            + "LEFT JOIN file f ON f.prd_id = p.prd_id WHERE p.prd_id = :id "
	            + "group by p.prd_id, p.price, p.title, p.cat_id,f.file_des ", nativeQuery = true)
	 ProductResDto findProductById(@Param("id") int id);


	 @Query(value = "select p.prd_id, p.title, p.price, p.cat_id, f.file_id, r.skin_id "
	 		+ "from product p "
	 		+ "left join product_skin_type ps on ps.prd_id = p.prd_id "
	 		+ "left join result r on r.skin_id = ps.skin_id "
	 		+ "left join user u on u.usr_id = r.usr_id "
	 		+ "left JOIN file f ON f.prd_id = p.prd_id  "
	 		+ "where r.skin_id = ps.skin_id  and r.usr_id = :usrId ", nativeQuery = true)
	 List<ProductResDto> selectProductsForMbti(@Param("usrId") String usrId);


}

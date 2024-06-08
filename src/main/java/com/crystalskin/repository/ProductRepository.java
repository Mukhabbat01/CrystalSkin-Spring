package com.crystalskin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crystalskin.domain.Product;
import com.crystalskin.dto.response.ProductResDto;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query(value="SELECT p.prd_id, p.des, p.price, p.title, p.cat_id, p.skin_id, f.file_id FROM product p "
			+ "left join file f on f.prd_id = p.prd_id " , nativeQuery = true)
	public List<ProductResDto> findAllProducts();
//
//	@Query(value="WITH ranked_products AS ( "
//			+ "SELECT * , "
//			+ "ROW_NUMBER() OVER(PARTITION BY skin_id ORDER BY prd_id) AS row_num "
//			+ "FROM product\n"
//			+ "WHERE cat_id = 'oil' + 'CleansingFoam' ) "
//			+ "SELECT * "
//			+ "FROM ranked_products "
//			+ "WHERE row_num = 1; " , nativeQuery = true)
//	public List<ProductResDto> findAllProducts();

		
	
	 @Query(value = "SELECT p.prd_id, p.des, p.price, p.title, p.cat_id, p.skin_id, f.file_id, f.file_des FROM product p "
	            + "LEFT JOIN file f ON f.prd_id = p.prd_id WHERE p.prd_id = :id", nativeQuery = true)
	 public ProductResDto findProductById(@Param("id") int id);


	 @Query(value = "select p.prd_id, p.title, p.price, p.cat_id, f.file_id from product p "
	 		+ "left join result r on r.skin_id = p.skin_id "
	 		+ "left join user u on u.usr_id = r.usr_id "
	 		+ "left JOIN file f ON f.prd_id = p.prd_id "
	 		+ "where r.skin_id = p.skin_id and r.usr_id = :usrId ", nativeQuery = true)
	public List<ProductResDto> selectProductsForMbti(@Param("usrId") String usrId);


}

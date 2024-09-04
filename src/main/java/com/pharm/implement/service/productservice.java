package com.pharm.implement.service;

import java.util.List;



import com.pharm.implement.entity.product;

public interface productservice {
	List<product> getAllproduct();
	
	product saveproduct(product Product);
	product getproductbyId(Long serial);
	product updateproduct(product Product);
	void deleteproductbyId(Long serial);
	List<product> searchProducts(String query);
	public List<product> deleteExpiredProducts();
}

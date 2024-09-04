package com.pharm.implement.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pharm.implement.entity.product;
import com.pharm.implement.repository.productrepository;
import com.pharm.implement.service.productservice;

@Service
public class  productserviceimpl implements productservice{
	
    
	

	private productrepository productRepository;
	
	public productserviceimpl(productrepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

@Override
	public List<product> getAllproduct() {
		return productRepository.findByIsDeletedFalse();
	}

@Override
public product saveproduct(product Product) {
	return productRepository.save(Product);
}

@Override
public product getproductbyId(Long serial) {
	return productRepository.findById(serial).get();
}

@Override
public product updateproduct(product Product) {
	return productRepository.save(Product);
}

@Override
public void deleteproductbyId(Long serial) {
	Optional<product> optionalProduct = productRepository.findById(serial);

    optionalProduct.ifPresent(product -> {
        // Set isDeleted to true
        product.setDeleted(true);

        // Save the updated product entity
        productRepository.save(product);
    });
}

@Override
public List<product> searchProducts(String query) {
	List<product> products= productRepository.searchproducts(query);
	 List<product> filteredProducts = products.stream()
	            .filter(product -> !product.isDeleted())
	            .collect(Collectors.toList());

	    return filteredProducts;
}

@Override
public List<product> deleteExpiredProducts() {
	 List<product> products = productRepository.findAll();
	 List<product> deletedMedicines = new ArrayList<>();
	 LocalDate currentDate = LocalDate.now();
	 for (product product : products) {
         if (product.getExpiryDate() != null && product.getExpiryDate().isBefore(currentDate)){
        	 deletedMedicines.add(product);
        	 product.setDeleted(true);
             // Save the updated product entity
             productRepository.save(product);
         }
}
	   return deletedMedicines;

}
}

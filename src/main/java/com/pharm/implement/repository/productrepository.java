package com.pharm.implement.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.pharm.implement.entity.product;

public interface productrepository extends JpaRepository<product, Long> {

    @Query("SELECT p FROM product p WHERE p.name LIKE CONCAT('%', :query, '%') OR p.manufacturer LIKE CONCAT('%', :query, '%')OR p.compound LIKE CONCAT('%', :query, '%')")
    List<product> searchproducts(String query);
    List<product> findByIsDeletedFalse();
}

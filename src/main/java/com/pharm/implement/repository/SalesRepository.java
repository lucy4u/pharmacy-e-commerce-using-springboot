package com.pharm.implement.repository;




import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pharm.implement.entity.Sales;

public interface SalesRepository extends JpaRepository<Sales, Date>{

	Sales findByDate(Date date);
	 @Query("SELECT COALESCE(SUM(s.dailyProfit), 0) FROM Sales s WHERE s.date BETWEEN ?1 AND ?2")
	 double calculateProfitBetweenDates(Date startDate, Date endDate);
}

package com.pharm.implement.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharm.implement.repository.SalesRepository;
import com.pharm.implement.service.SalesService;

@Service
public class SalesServiceImpl implements SalesService{

	 @Autowired
	    private SalesRepository salesRepository;

	 @Override
	    public double calculateMonthlyProfit() {
	        LocalDate currentDate = LocalDate.now();
	        LocalDate thirtyDaysAgo = currentDate.minusDays(30);

	        // Convert LocalDate to Date
	        Date startDate = Date.from(thirtyDaysAgo.atStartOfDay(ZoneId.systemDefault()).toInstant());
	        Date endDate = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

	        // Calculate profit for the last 30 days
	        return salesRepository.calculateProfitBetweenDates(startDate, endDate);
	    }

	    @Override
	    public double calculateYearlyProfit() {
	        LocalDate currentDate = LocalDate.now();
	        LocalDate oneYearAgo = currentDate.minusYears(1);

	        // Convert LocalDate to Date
	        Date startDate = Date.from(oneYearAgo.atStartOfDay(ZoneId.systemDefault()).toInstant());
	        Date endDate = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

	        // Calculate profit for the last 365 days
	        return salesRepository.calculateProfitBetweenDates(startDate, endDate);
	    }

}

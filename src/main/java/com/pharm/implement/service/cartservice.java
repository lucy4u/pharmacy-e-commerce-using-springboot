package com.pharm.implement.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.pharm.implement.dto.CartViewDTO;

import jakarta.servlet.http.HttpSession;


@Service
public interface cartservice {

	public List<CartViewDTO> viewCart(int userId);
	public void deleteCartItem(Long userId, Long cartId);
	void clearCart(int userId);
	public double buy(HttpSession session, String street, String city, String state, String zipCode);
 void addToCart(int userId, Long productId, int quantity);
 public boolean isCartEmpty(int userId);
    }

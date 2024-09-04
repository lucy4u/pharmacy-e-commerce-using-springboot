package com.pharm.implement.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.pharm.implement.dto.CartViewDTO;
import com.pharm.implement.entity.Address;
import com.pharm.implement.entity.Bill;
import com.pharm.implement.entity.BillMedicine;
import com.pharm.implement.entity.BillMedicineId;
import com.pharm.implement.entity.User;
import com.pharm.implement.entity.cart;
import com.pharm.implement.entity.product;
import com.pharm.implement.repository.AddressRepository;
import com.pharm.implement.repository.BillMedicineRepository;
import com.pharm.implement.repository.BillRepository;
import com.pharm.implement.repository.CartRepository;
import com.pharm.implement.repository.Userrepository;
import com.pharm.implement.service.cartservice;
import com.pharm.implement.service.productservice;

import jakarta.servlet.http.HttpSession;

@Service
public class  cartserviceimpl implements cartservice{

	private CartRepository cartRepository;
	 @Autowired
	    private productservice productService;
	  @Autowired
	    private BillRepository billRepository;

	    @Autowired
	    private BillMedicineRepository billMedicineRepository;
	    @Autowired
	    private Userrepository UserRepository;
	    @Autowired
	    private AddressRepository addressRepository;

	public cartserviceimpl(CartRepository cartRepository) {
		super();
		this.cartRepository = cartRepository;
	}
	@Override
	public List<CartViewDTO> viewCart(int userId) {
	    List<CartViewDTO> cartViewList = new ArrayList<>();

	    List<cart> userCart = cartRepository.findByuserid(userId);

	    for (cart cartItem : userCart) {
	        product product = productService.getproductbyId(cartItem.getSerial());

	        if (product != null) {
	            double totalPrice = product.getPrice() * cartItem.getQuantity();

	            CartViewDTO cartViewDTO = new CartViewDTO(
	                    cartItem.getCart_id(),
	                    product.getName(),
	                    product.getManufacturer(),
	                    product.getPrice(),
	                    cartItem.getQuantity(),
	                    totalPrice
	            );

	            cartViewList.add(cartViewDTO);
	        } else {
	            // Log or handle the case where the product is not found
	            // You can skip this cartItem or handle it in a way that makes sense for your application
	            System.out.println("Product not found for cartItem with serial: " + cartItem.getSerial());
	        }
	    }

	    return cartViewList;
	}
	
	@Transactional
	@Override
	public void deleteCartItem(Long userId, Long cartId) {
		
		cart cartItem = cartRepository.findByCartid(cartId);
		 if (cartItem != null) {
			 Long productId = cartItem.getSerial();
		        int quantity = cartItem.getQuantity();
		 
		 
		   product product = productService.getproductbyId(productId);
	        if (product != null) {

	                product.setStocks(product.getStocks() + quantity);
	                productService.saveproduct(product);
	            }
		 }
		cartRepository.deleteByUseridAndCartid(userId, cartId);
		

}

		   @Transactional
		    @Override
		    public void clearCart(int userId) {
		        // Implement your logic to clear the cart by deleting all cart items for the user
		        List<cart> cartItems = cartRepository.findByuserid(userId);
		        cartRepository.deleteAll(cartItems);
		    }

		@Override
		 @Transactional
		    public double buy(HttpSession session ,String street, String city, String state, String zipCode) {
		        // Fetch the userId from the session
		        int userId = (int) session.getAttribute("userId");
		        User user = UserRepository.findById(userId);

		        // Fetch the cart items for the user
		        List<cart> cartItems = cartRepository.findByuserid(userId);
		        double totalProfit = 0.0;
		 
		        // Create a new Address
		        Address address = new Address(street, city, state, zipCode);
		      

		        // Save the address
		        addressRepository.save(address);
		        
		        Bill bill = new Bill(user, LocalDateTime.now(),address);
		        billRepository.save(bill);

		        for (cart cartItem : cartItems) {
		            // Fetch the product for the cart item
		            Long serial = cartItem.getSerial();
		            product product = productService.getproductbyId(serial);
		            double profit = product.getPrice() - product.getWholesaleRate();
		            totalProfit += profit;
		            // Create a new BillMedicineId with the Bill and Product
		            BillMedicineId billMedicineId = new BillMedicineId(bill, product);

		            // Create a new BillMedicine entry
		            BillMedicine billMedicine = new BillMedicine();
		            billMedicine.setId(billMedicineId);
		            billMedicine.setQuantity(cartItem.getQuantity());

		            // Save the BillMedicine entry
		            billMedicineRepository.save(billMedicine);
		    
		        }
		        // Clear the cart
		        cartRepository.deleteByUserid(userId);
		        return totalProfit;
		    }
		@Transactional
		@Override
		public void addToCart(int userId, Long productId, int quantity) {
	        product product = productService.getproductbyId(productId);

	        if (product != null) {
	            if (product.getStocks() >= quantity) {
	                // Update product stocks
	                product.setStocks(product.getStocks() - quantity);
	                productService.saveproduct(product);

	                int biggestCartId = getBiggestCartId(userId);
	                cart cartItem = new cart();
	                cartItem.setCart_id(biggestCartId+ 1);
	                cartItem.setQuantity(quantity);
	                cartItem.setUser_id(userId);
	                cartItem.setSerial(productId); 
	                cartRepository.save(cartItem);
	            } else {
	                // Handle insufficient stock error
	                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient stock for product: " + productId);
	            }
	        } else {
	            // Handle product not found error
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found: " + productId);
	        }
	    }
		public int getBiggestCartId(int userId) {
	        // Retrieve the biggest cart ID for the given user from the database
	        Integer biggestCartId = cartRepository.findBiggestCartIdByUserId(userId);
	        return (biggestCartId != null) ? biggestCartId : 0;
	    }
		@Override
		public boolean isCartEmpty(int userId) {
			  List<cart> userCart = cartRepository.findByuserid(userId);

		        // Check if the cart is empty
		        return userCart == null || userCart.isEmpty();
		    }
		}
		
		



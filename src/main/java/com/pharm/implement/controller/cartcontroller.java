package com.pharm.implement.controller;






import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import com.pharm.implement.dto.BillViewDTO;
import com.pharm.implement.dto.CartViewDTO;
import com.pharm.implement.entity.Address;
import com.pharm.implement.entity.Sales;
import com.pharm.implement.entity.User;
import com.pharm.implement.repository.BillRepository;
import com.pharm.implement.repository.SalesRepository;
import com.pharm.implement.service.BillService;
import com.pharm.implement.service.cartservice;
import jakarta.servlet.http.HttpSession;

@Controller
public class cartcontroller {
	
	   @Autowired
	    private BillService billService;
	    @Autowired
	    private BillRepository billRepository;

	 @Autowired
	    private cartservice cartService;
	
	 @Autowired
	    private SalesRepository salesRepository;
	 
	    @GetMapping("/home/cart")
	    public String viewCart( Model model,HttpSession session) {
	    	
	    	int userId = (int) session.getAttribute("userId");
	        List<CartViewDTO> cartItems = cartService.viewCart(userId);
	        double totalCartPrice = cartItems.stream()
	                .mapToDouble(CartViewDTO::getTotalPrice)
	                .sum();

	        model.addAttribute("cartItems", cartItems);
	        model.addAttribute("totalCartPrice", totalCartPrice);
	        model.addAttribute("addressForm", new Address());

	        return "cart"; 
	    }


	    @PostMapping("/cart/add")
	    public String addToCart(@RequestParam("quantity") int quantity,
	                            @RequestParam("serial") Long serial,
	                            @SessionAttribute("userId") int userId) {
	        try {
	            cartService.addToCart(userId, serial, quantity);
	            return "redirect:/home";
	        } catch (ResponseStatusException ex) {
	            return "redirect:/error?message=" + ex.getReason();
	        }
	    }

	    @Transactional
	    @GetMapping("/cart/delete/{cartid}")
	    public String deleteCartItem(@PathVariable Long cartid, @SessionAttribute("userId") Long userId) {
	        // Check if the user is logged in
	        if (userId == null) {
	            // Redirect to the login page or handle as appropriate
	            return "redirect:/login";
	        }

	        // Call the service to delete the item from the cart
	        cartService.deleteCartItem(userId, cartid);

	        // Redirect to the cart page or any other page after deletion
	        return "redirect:/home/cart";
	    }
	    
	    
	    @Transactional
	    @PostMapping("/home/cart")
	    public ModelAndView buy(@ModelAttribute("addressForm") Address addressForm,HttpSession session, Model model) {
	        // Fetch the userId from the session
	        Integer userId = (Integer) session.getAttribute("userId");
	        ModelAndView modelAndView = new ModelAndView();
	        // Check if the user is logged in
	        if (userId == null) {
	            // You can add a message to display on the login page
	            model.addAttribute("loginMessage", "Please log in to make a purchase.");

	            modelAndView.setViewName("redirect:/login");
	            modelAndView.addAllObjects(model.asMap());
	            return modelAndView;
	        }
	        // Check if the cart is empty
	        if (cartService.isCartEmpty(userId)) {
	            // You can add a message to display on the cart page
	            model.addAttribute("cartMessage", "Your cart is empty. Add items before making a purchase.");

	            // Redirect to the cart page or handle as appropriate
	            modelAndView.setViewName("redirect:/home/cart");
	            modelAndView.addAllObjects(model.asMap());
	            return modelAndView;
	        }
	        String street = addressForm.getStreet();
	        String city = addressForm.getCity();
	        String state = addressForm.getState();
	        String zipCode = addressForm.getZipCode();
	        double totalProfit = cartService.buy(session, street, city, state, zipCode);

	        // Get the current date
	        LocalDate currentDate = LocalDate.now();
	        
	        // Convert LocalDate to Date
	        Date currentDateAsDate = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

	        // Check if the current date exists in the Sales table
	        Sales sales = salesRepository.findByDate(currentDateAsDate);

	        if (sales != null) {
	            // Update the existing entry with the totalProfit
	            double updatedDailyProfit = sales.getDailyProfit() + totalProfit;
	            sales.setDailyProfit(updatedDailyProfit);
	        } else {
	            // Create a new entry with the current date and totalProfit
	            sales = new Sales(currentDateAsDate, totalProfit);
	        }

	        // Save the Sales entry
	        salesRepository.save(sales);
	        Long largestBillId = billRepository.findLargestBillIdByUserId(userId);
	        User user = new User();
	        user.setUser_id(userId);

	        List<BillViewDTO> billDetails = billService.getBillDetails(largestBillId, user);

	        if (!billDetails.isEmpty()) {
	        	
	        	 modelAndView.setViewName("billDetails");
	            modelAndView.addObject("billDetails", billDetails);
	            modelAndView.addObject("address", addressForm);
	            return modelAndView;
	        } else {
	            // Handle not found scenario or redirect to an error page
	            return new ModelAndView("redirect:/error");
	        }
	 

	        // Redirect to the cart page or any other page after the buy operation
	        
	    }
}
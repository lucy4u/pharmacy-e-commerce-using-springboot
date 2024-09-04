package com.pharm.implement.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.pharm.implement.dto.Userregistrationdto;
import com.pharm.implement.entity.Sales;
import com.pharm.implement.entity.User;
import com.pharm.implement.entity.product;
import com.pharm.implement.repository.SalesRepository;
import com.pharm.implement.service.SalesService;
import com.pharm.implement.service.Userservice;
import com.pharm.implement.service.productservice;

import jakarta.servlet.http.HttpSession;

@Controller
public class Userregistraioncontroller {
	
	private Userservice UserService;
	@Autowired
	private productservice productService;
	@Autowired
	private SalesService salesService;
	 @Autowired
	    private SalesRepository salesRepository;
	 
	public Userregistraioncontroller(Userservice userService) {
		super();
		UserService = userService;
	}
	

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        // Get all products from the productService
        List<product> products = productService.getAllproduct();

        // Add products to the model
        model.addAttribute("products", products);
            // Retrieve userId from the session and add it to the model
            Integer userId = (Integer) session.getAttribute("userId");
            model.addAttribute("userId", userId);
 
        return "home";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    
    @GetMapping("/AdminHome")
    public String AdminHome(Model model) {
        double monthlyProfit = salesService.calculateMonthlyProfit();
        double yearlyProfit = salesService.calculateYearlyProfit();
        // Get the daily profit for the current date
        LocalDate currentDate = LocalDate.now();
        Date today = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Sales sales = salesRepository.findByDate(today);

        // Check if sales for today exist
        double dailyProfit = (sales != null) ? sales.getDailyProfit() : 0.0;

        model.addAttribute("monthlyProfit", monthlyProfit);
        model.addAttribute("yearlyProfit", yearlyProfit);
        model.addAttribute("dailyProfit", dailyProfit);
        return "AdminHome";
        
    }
	
    @GetMapping("/registration")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        Userregistrationdto user = new  Userregistrationdto();
        model.addAttribute("user", user);
        return "registration";
    }
	
    @PostMapping("/registration/save")
    public String registration(@Validated @ModelAttribute("user") Userregistrationdto registrationdto,
                               BindingResult result,
                               Model model,
                               HttpSession session) {
        User existingUser = UserService.findUserByEmail(registrationdto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", registrationdto);
            return "/registration";
        }

        // Save the user
        UserService.save(registrationdto);

        // Retrieve the saved user using the email
        User savedUser = UserService.findUserByEmail(registrationdto.getEmail());
        // Set the session attribute userId with the user_id of the saved user
        session.setAttribute("userId", savedUser.getUser_id());

        return "redirect:/home";
    }
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login?logout=true";
    }
    }
    
 


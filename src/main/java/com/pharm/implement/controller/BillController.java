package com.pharm.implement.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.pharm.implement.dto.BillViewDTO;
import com.pharm.implement.entity.Address;
import com.pharm.implement.entity.Bill;
import com.pharm.implement.entity.User;
import com.pharm.implement.repository.BillRepository;
import com.pharm.implement.repository.Userrepository;
import com.pharm.implement.service.BillService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BillController {

    @Autowired
    private BillService billService;
    @Autowired
	private Userrepository UserRepository;
    @Autowired
    private BillRepository billRepository;

    @GetMapping("/bill")
    public String viewBillHistory(@SessionAttribute("userId") int userId, Model model) {
        User user = UserRepository.findById(userId);
        boolean isAdmin = false;
        if (userId == 2) {
            // If userId is 2, set isAdmin to true
            isAdmin = true;
        }// Assuming you have a UserService to retrieve the User
        List<Bill> billHistory = billService.viewBillHistory(user);
        model.addAttribute("billHistory", billHistory);
        model.addAttribute("isAdmin", isAdmin);
        return "bill";
        }
    
    @GetMapping("/bill/{billId}")
    public ModelAndView getBillDetails(@PathVariable Long billId, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        boolean isAdmin = false;

        if (userId == null) {
            // Redirect to login page or handle unauthorized access
            return new ModelAndView("redirect:/login");
        }

        if (userId == 2) {
            // If userId is 2, retrieve the corresponding user ID based on the bill ID
        	  Long correspondingUserId = billRepository.findUserIdByBillId(billId);

              // Use the corresponding user ID
        	  userId = correspondingUserId.intValue();
        	  isAdmin = true;
        }

        User user = new User();
        user.setUser_id(userId);

        List<BillViewDTO> billDetails = billService.getBillDetails(billId, user);
        Address address = billService.viewAddress(billId);
        
      

        if (!billDetails.isEmpty()) {
            ModelAndView modelAndView = new ModelAndView("billDetails");
            modelAndView.addObject("billDetails", billDetails);
            modelAndView.addObject("address", address);
            modelAndView.addObject("isAdmin", isAdmin); 
            return modelAndView;
        } else {
            // Handle not found scenario or redirect to an error page
            return new ModelAndView("redirect:/error");
        }
    }
    
}
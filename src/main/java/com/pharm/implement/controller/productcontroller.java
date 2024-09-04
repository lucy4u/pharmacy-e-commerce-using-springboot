package com.pharm.implement.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pharm.implement.entity.product;
import com.pharm.implement.service.productservice;

@Controller
public class productcontroller {

	private productservice productService;

	public productcontroller(productservice productService) {
		super();
		this.productService = productService;
	}
	@GetMapping("/products")
	public String listProducts(Model model) {
	model.addAttribute("products", productService.getAllproduct());
	return "products";
	}

	@GetMapping("/products/new")
	public String addproduct(Model model) {
		product Product= new product();
		model.addAttribute("Product", Product);
		return "add_products";
		
	}
	
	@PostMapping("/products")
	public String saveproduct(@ModelAttribute("product")product Product) {
		productService.saveproduct(Product);
		return "redirect:/products";
	}
	@GetMapping("/products/update/{serial}")
	public String editproductform(@PathVariable Long serial, Model model) {
	    model.addAttribute("product", productService.getproductbyId(serial)); // Change "products" to "product"
	    return "update_product";
	}

	@PostMapping("/products/{serial}")
	public String updateProduct(@PathVariable Long serial, @ModelAttribute product Product) {
		product existingproduct= productService.getproductbyId(serial);
		existingproduct.setSerial(serial);
		existingproduct.setName(Product.getName());
		existingproduct.setPrice(Product.getPrice());
		existingproduct.setStocks(Product.getStocks());
		existingproduct.setManufacturer(Product.getManufacturer());
		existingproduct.setCompound(Product.getCompound());
		existingproduct.setManufactdate(Product.getManufactdate());
		existingproduct.setExpiryDate(Product.getExpiryDate());
		existingproduct.setWholesaleRate(Product.getWholesaleRate());
		productService.updateproduct(existingproduct);
	    return "redirect:/products";
	}
	@GetMapping("/products/delete/{serial}")
      public String deleteproduct(@PathVariable Long serial) {
	productService.deleteproductbyId(serial);
	return "redirect:/products";
      }
	@GetMapping("/home/search")
	public String searchProducts(@RequestParam String query, Model model) {
	    List<product> searchResults = productService.searchProducts(query);
	    if (searchResults.isEmpty()) {
	        model.addAttribute("noResults", true);
	    } else {
	        model.addAttribute("searchResults", searchResults);
	    }
	    return "search"; // Replace with the actual view name for search results
	}
	  @GetMapping("/home/searchbar")
	    public String search(){
	        return "search";
	    }
	  @GetMapping("/products/deletedMedicines")
	    public String getDeletedMedicines(Model model) {
	        List<product> deletedMedicines = productService.deleteExpiredProducts();

	        // Add the list of deleted medicines to the model
	        model.addAttribute("deletedMedicines", deletedMedicines);

	        // Return the name of the Thymeleaf template to display
	        return "Expired";
	    }
}

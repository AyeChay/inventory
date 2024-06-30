package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dto.CategoryDTO;
import com.dto.LocationDTO;
import com.dto.ProductDTO;
import com.model.Location;
import com.model.Product;
import com.repository.CategoryRepository;
import com.repository.LocationRepository;
import com.repository.ProductOneRepository;

@Controller
@RequestMapping(value="/product")
public class ProductController {
	@Autowired
    ModelMapper modelMapper;

    @Autowired
    ProductOneRepository productRepo;
   
    
    @GetMapping(value="/productregister")
    public ModelAndView showRegisterForm() {
        return new ModelAndView("productRegister", "product", new Product());
    }

    @PostMapping(value="/doregister")
    public String registerProduct(@ModelAttribute("product") @Valid Product productDTO, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "productRegister";
        }
        
        if (productDTO.getProductCode().length() > 10) {
            model.addAttribute("error", "Product Code exceeds 10 characters. Please enter a valid Product Code.");
            return "productRegister";
        }else if(productDTO.getProductName().length() > 10) {
        	model.addAttribute("error", "Product Name exceeds 10 characters. Please enter a valid Product Name.");
            return "productRegister";
        }
        
        String trimmedDescription = productDTO.getDescription().trim();
        if (trimmedDescription.length() > 25) {
            model.addAttribute("error", "Description must be 20 characters or less.");
            return "productRegister";
        }
        
        
        ProductDTO dto = modelMapper.map(productDTO, ProductDTO.class);
        int result = productRepo.insertProduct(dto);

        if (result > 0) {
            return "redirect:/product/showproducts";
        } else {
            model.addAttribute("error", "Failed to register product. Please try again.");
            return "productRegister";
        }
    }

    @GetMapping(value="/showproducts")
    public String showAllProducts(Model model) {
        List<ProductDTO> productList = productRepo.getAllProducts();
        model.addAttribute("productList", productList);
        return "productList";
    }
    
//    @GetMapping("/search")
//    public String searchProducts(@RequestParam("productCode") String productCode, Model model) {
//        List<ProductDTO> productList = productRepo.searchProductsByCode(productCode);
//        model.addAttribute("productList", productList);
//        return "productList"; // Assuming "productList.html" is your view for displaying products
//    }

    @GetMapping(value="/editproduct/{id}")
    public String showProductById(@PathVariable("id") int id, Model model) {
        ProductDTO dto = productRepo.getProductById(id);
        if (dto != null) {
            Product product = modelMapper.map(dto, Product.class);
            model.addAttribute("product", product);
            return "productEdit";
        } else {
            return "redirect:/product/showproducts";
        }
    }

    @PostMapping(value="/doupdate")
    public String updateProduct(@ModelAttribute("product") @Valid Product productDTO, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "productEdit";
        }

        ProductDTO dto = modelMapper.map(productDTO, ProductDTO.class);
        int result = productRepo.updateProduct(dto);

        if (result > 0) {
            return "redirect:/product/showproducts";
        } else {
            model.addAttribute("error", "Failed to update product. Please try again.");
            return "productEdit";
        }
    }

    @GetMapping(value="/deleteproduct/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        int result = productRepo.softDeleteProduct(id);
        return "redirect:/product/showproducts";
    }
    
    @ModelAttribute("UOMList") 
	public List<String> getUOMList()
	{
		List<String> UOMList = new ArrayList<String>();
		UOMList.add("KG"); 
		UOMList.add("BAG");
		UOMList.add("BOTTLE"); 
		UOMList.add("EA");
		
		
		return UOMList;
	}
}

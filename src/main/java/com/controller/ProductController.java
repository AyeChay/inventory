package spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import spring.dto.CategoryDTO;
import spring.dto.ProductDTO;
import spring.dto.ProductLotDTO;
import spring.model.ProductBean;
import spring.model.ProductLotBean;
import spring.service.CategoryService;
import spring.service.ProductService;

@Controller
@RequestMapping(value="/product")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ModelMapper mapper;
	
	@GetMapping(value="/lists")
	public String showProducts(ModelMap model)
	{
		List<ProductDTO> dbRs = productService.getAll();
		model.addAttribute("products", dbRs);
		return "product-lists";
	}
	
	@GetMapping(value="/add")
	public ModelAndView showAdd()
	{
		return new ModelAndView("product-add", "productLot", new ProductLotBean());
	}
	
	@PostMapping(value="/add")
	public String doAdd(@ModelAttribute("productLot")ProductLotBean productLot)
	{
		ProductLotDTO dto=mapper.map(productLot, ProductLotDTO.class);
		int dbRs = productService.insertOne(dto);
		return "redirect:./lists";
	}
	
	@GetMapping(value="/update/{id}")
	public String getProduct(@PathVariable("id")Integer id, ModelMap model)
	{
		ProductDTO dbRs = productService.getOne(id);
		ProductBean bean = mapper.map(dbRs, ProductBean.class);
		model.addAttribute("product", bean);
		return "product-update";
	}
	
	@PostMapping(value="/update/doupdate")
	public String updateProduct(@ModelAttribute("product")ProductBean product)
	{
		ProductDTO dto=mapper.map(product, ProductDTO.class);
		int dbRs = productService.updateOne(dto);
		return "redirect:../lists";
	}
	
	@GetMapping(value="/delete/{id}")
	public String deleteProduct(@PathVariable("id")Integer id)
	{
		int dbRs = productService.deleteOne(id);
		return "redirect:../lists";
	}
	
	@ModelAttribute("categories")
	public List<String> getCategories()
	{
		List<CategoryDTO> dbRs = categoryService.getAll();
		List<String> categories=new ArrayList<>();
		for(CategoryDTO dto: dbRs)
		{
			categories.add(dto.getName());
		}
		return categories;
	}
	
	@ModelAttribute("uom")
	public List<String> getUoms()
	{
		List<String> uoms = new ArrayList<>();
		uoms.add("KG");
		uoms.add("Bag");
		uoms.add("EA");
		uoms.add("Box");
		uoms.add("Bottle");
		return uoms;
	}
}

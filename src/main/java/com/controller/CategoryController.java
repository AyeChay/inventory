package com.controller;

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

import com.dto.CategoryDTO;
import com.model.CategoryBean;
import com.repository.CategoryService;

@Controller
@RequestMapping(value="/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ModelMapper mapper;
	
	@GetMapping(value="/home")
	public String showHome()
	{
		return "home";
	}
	
	@GetMapping(value="/lists")
	public String getCategories(ModelMap model)
	{
		List<CategoryDTO> dbRs = categoryService.getAll();
		model.addAttribute("categories", dbRs);
		return "category-lists";
	}
	
	@GetMapping(value="/add")
	public ModelAndView showAddForm()
	{
		return new ModelAndView("category-add", "category", new CategoryBean());
	}
	
	@PostMapping(value="/add")
	public String addCategory(@ModelAttribute("category")CategoryBean category)
	{
		CategoryDTO dto=mapper.map(category, CategoryDTO.class);
		int dbRs = categoryService.insertOne(dto);
		return "redirect:./lists";
	}
	
	@GetMapping(value="/update/{id}")
	public String showCategory(@PathVariable("id")Integer id, ModelMap model)
	{
		CategoryDTO dbRs = categoryService.getOne(id);
		CategoryBean bean = mapper.map(dbRs, CategoryBean.class);
		model.addAttribute("category", bean);
		return "category-update";
	}
	
	@PostMapping(value="/update/{id}")
	public String updateCategory(@ModelAttribute("category")CategoryBean category)
	{
		CategoryDTO dto = mapper.map(category, CategoryDTO.class);
		int dbRs = categoryService.updateOne(dto);
		return "redirect:../lists";
	}
	
	@GetMapping(value="/delete/{id}")
	public String deleteCategory(@PathVariable("id") Integer id)
	{
		int dbRs = categoryService.deleteOne(id);
		return "redirect:../lists";
	}
	
}

package com.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.dto.CategoryDTO;
import com.model.Category;
import com.repository.CategoryRepository;

@Controller
@RequestMapping(value="/category")
public class CategoryController {
	@Autowired
    ModelMapper modelMapper;

    @Autowired
    CategoryRepository categoryRepo;

    @GetMapping(value="/categoryregister")
    public ModelAndView showRegisterForm() {
        return new ModelAndView("categoryRegister", "category", new Category());
    }

    @PostMapping(value="/doregister")
    public String registerCategory(@ModelAttribute("category") @Valid Category categoryDTO, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "categoryRegister";
        }

        CategoryDTO dto = modelMapper.map(categoryDTO, CategoryDTO.class);
        int result = categoryRepo.insertCategory(dto);

        if (result > 0) {
            return "redirect:/category/showcategories";
        } else {
            model.addAttribute("error", "Failed to register category. Please try again.");
            return "categoryRegister";
        }
    }

    @GetMapping(value="/showcategories")
    public String showAllCategories(Model model) {
        List<CategoryDTO> categoryList = categoryRepo.getAllCategories();
        model.addAttribute("categoryList", categoryList);
        return "categoryList";
    }

    @GetMapping(value="/editcategory/{id}")
    public String showCategoryById(@PathVariable("id") int id, Model model) {
        CategoryDTO dto = categoryRepo.getCategoryById(id);
        if (dto != null) {
            Category category = modelMapper.map(dto, Category.class);
            model.addAttribute("category", category);
            return "categoryEdit";
        } else {
            return "redirect:/category/showcategories";
        }
    }

    @PostMapping(value="/doupdate")
    public String updateCategory(@ModelAttribute("category") @Valid Category categoryDTO, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "categoryEdit";
        }

        CategoryDTO dto = modelMapper.map(categoryDTO, CategoryDTO.class);
        int result = categoryRepo.updateCategory(dto);

        if (result > 0) {
            return "redirect:/category/showcategories";
        } else {
            model.addAttribute("error", "Failed to update category. Please try again.");
            return "categoryEdit";
        }
    }

    @GetMapping(value="/deletecategory/{id}")
    public String deleteCategory(@PathVariable("id") int id) {
        int result = categoryRepo.softDeleteCategory(id);
        return "redirect:/category/showcategories";
    }
}

package com.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dto.UserDTO;
import com.dto.UserRoleDTO;
import com.model.User;
import com.repository.UserRepository;
import com.repository.UserRoleRepository;






@Controller
public class UserController {
	
	@Autowired
	UserRoleRepository roleRepo;
	static UserRepository userRepo = new UserRepository();
	
	static ModelMapper modelMappeer = new ModelMapper();
	@GetMapping(value = "/user_registration")
	public ModelAndView showRegister() {

		User user = new User();
		return new ModelAndView("user_registration", "user", user) ;
	}
	
	@PostMapping(value = "/doregistration")
	public String doRegister(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "user_registration";
		}
		
		if(user != null) {
			UserDTO userDto = modelMappeer.map(user, UserDTO.class);
			int i = userRepo.insert(userDto);
			if(i > 0) {
				return "redirect:/hello";
			}else {
				model.addAttribute("user",new User());
				return "user_registration";
			}
		}else {
			model.addAttribute("user", new User());
			return "user_registration";
		}
		
		
	}
	
	
	
	@GetMapping(value = "/showusers")
	public String showAllUser(Model model) {
		List<UserDTO> userDto = userRepo.showAllUser();
		model.addAttribute("dtoList", userDto);
		return "user_list";
		
	}

	
	@ModelAttribute("userRoles")
	public List<UserRoleDTO> getUserRoles() {
	    List<UserRoleDTO> roles = roleRepo.showAllUserRole().stream()
	            .map(role -> new UserRoleDTO(role.getId(), role.getName()))
	            .collect(Collectors.toList());

	    // Debugging output
	    System.out.println("Roles: " + roles);

	    return roles;
	}

	

}

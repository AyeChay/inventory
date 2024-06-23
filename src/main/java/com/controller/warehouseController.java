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

import com.dto.LocationDTO;
import com.dto.warehouseDTO;
import com.model.Warehouse;
import com.repository.LocationRepository;
import com.repository.WarehouseRepository;

@Controller
@RequestMapping(value="/warehouse")
public class warehouseController {
	@Autowired
    ModelMapper modelMapper;

    @Autowired
    WarehouseRepository warehouseRepo;

    @Autowired
    LocationRepository locationRepo;
   
    @GetMapping(value="hello")
    public String hello(){
    	return "Hello World";
    }
    
    @GetMapping(value="/register")
    public ModelAndView showRegisterForm() {
        ModelAndView mav = new ModelAndView("warehouseRegister");
        mav.addObject("warehouse", new Warehouse());
        List<LocationDTO> locations = locationRepo.getAllLocations();
        mav.addObject("locations", locations);
        return mav;
    }

    @PostMapping(value="/doregister")
    public String registerWarehouse(@ModelAttribute("warehouse") @Valid Warehouse warehouseDTO, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("locations", locationRepo.getAllLocations());
            return "warehouseRegister";
        }

        warehouseDTO dto = modelMapper.map(warehouseDTO, warehouseDTO.class);
        int result = warehouseRepo.insertWarehouse(dto);

        if (result > 0) {
            return "redirect:/warehouse/showwarehouses";
        } else {
            model.addAttribute("error", "Failed to register warehouse. Please try again.");
            return "warehouseRegister";
        }
    }

    @GetMapping(value="/showwarehouses")
    public String showAllWarehouses(Model model) {
        List<warehouseDTO> warehouseList = warehouseRepo.getAllWarehouses();
        model.addAttribute("warehouseList", warehouseList);
        return "warehouseList";
    }

    @GetMapping(value="/editwarehouse/{id}")
    public String showWarehouseById(@PathVariable("id") int id, Model model) {
        warehouseDTO dto = warehouseRepo.getWarehouseById(id);
        if (dto != null) {
            Warehouse warehouse = modelMapper.map(dto, Warehouse.class);
            model.addAttribute("warehouse", warehouse);
            model.addAttribute("locations", locationRepo.getAllLocations());
            return "warehouseEdit";
        } else {
            return "redirect:/warehouse/showwarehouses";
        }
    }

    @PostMapping(value="/doupdate")
    public String updateWarehouse(@ModelAttribute("warehouse") @Valid Warehouse warehouseDTO, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("locations", locationRepo.getAllLocations());
            return "warehouseEdit";
        }

        warehouseDTO dto = modelMapper.map(warehouseDTO, warehouseDTO.class);
        int result = warehouseRepo.updateWarehouse(dto);

        if (result > 0) {
            return "redirect:/warehouse/showwarehouses";
        } else {
            model.addAttribute("error", "Failed to update warehouse. Please try again.");
            return "warehouseEdit";
        }
    }
 
    
    @GetMapping(value="/deletewarehouse/{id}")
    public String deletewarehouse(@PathVariable("id") int id) {
        int result = warehouseRepo.softDeleteWarehouse(id);
        if(result>0) {
        	return "redirect:/warehouse/showwarehouses";
        }else {
        	return "error";
        }
        
    }
}

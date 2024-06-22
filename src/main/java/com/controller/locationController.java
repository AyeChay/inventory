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
import com.model.Location;
import com.repository.LocationRepository;

@Controller
@RequestMapping(value="/location")
public class locationController {
	@Autowired
    ModelMapper modelMapper;

    @Autowired
    LocationRepository locationRepo;

    
    @GetMapping(value="/locationregister")
    public ModelAndView showRegisterForm() {
        return new ModelAndView("locationRegister", "location", new Location());
    }

    @PostMapping(value="/doregister")
    public String registerLocation(@ModelAttribute("location") @Valid Location locationDTO, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "locationRegister";
        }

        LocationDTO dto = modelMapper.map(locationDTO, LocationDTO.class);
        int result = locationRepo.insertLocation(dto);

        if (result > 0) {
            return "redirect:/location/showlocations";
        } else {
            model.addAttribute("error", "Failed to register location. Please try again.");
            return "locationRegister";
        }
    }

    
    @GetMapping(value="/showlocations")
    public String showAllLocations(Model model) {
        List<LocationDTO> locationList = locationRepo.getAllLocations();
        model.addAttribute("locationList", locationList);
        return "locationList";
    }

    // Show a specific location for editing
    @GetMapping(value="/editlocation/{id}")
    public String showLocationById(@PathVariable("id") int id, Model model) {
        LocationDTO dto = locationRepo.getLocationById(id);
        if (dto != null) {
            Location location = modelMapper.map(dto, Location.class);
            model.addAttribute("location", location);
            return "locationEdit";
        } else {
            return "redirect:/location/showlocations";
        }
    }

    @PostMapping(value="/doupdate")
    public String updateLocation(@ModelAttribute("location") @Valid Location locationDTO, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "locationEdit";
        }

        LocationDTO dto = modelMapper.map(locationDTO, LocationDTO.class);
        int result = locationRepo.updateLocation(dto);

        if (result > 0) {
            return "redirect:/location/showlocations";
        } else {
            model.addAttribute("error", "Failed to update location. Please try again.");
            return "locationEdit";
        }
    }

    // Soft delete a location
    @GetMapping(value="/deletelocation/{id}")
    public String deleteLocation(@PathVariable("id") int id) {
        int result = locationRepo.softDeleteLocation(id);
        return "redirect:/location/showlocations";
    }
}

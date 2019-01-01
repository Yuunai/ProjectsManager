package pl.poznan.put.student.spacjalive.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.student.spacjalive.erp.entity.Equipment;
import pl.poznan.put.student.spacjalive.erp.entity.EquipmentCategory;
import pl.poznan.put.student.spacjalive.erp.service.EquipmentService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/equipment")
public class EquipmentController {
	
	@Autowired
	EquipmentService equipmentService;
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/list")
	public String listEquipment(Model model) {
		List<Equipment> equipment = equipmentService.getEquipmentList();
		model.addAttribute("equipment", equipment);
		
		return "list-equipment";
	}
	
	@GetMapping("/addEquipmentForm")
	public String addEquipmentForm(Model model) {
		Equipment equipment = new Equipment();
		model.addAttribute("equipment", equipment);
		model.addAttribute("categories", equipmentService.getCategories());
		
		return "add-equipment-form";
	}
	
	@PostMapping("/addEquipment")
	public String addEquipment(@ModelAttribute("equipment") @Valid Equipment equipment, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("categories", equipmentService.getCategories());
			return "add-equipment-form";
		}
		equipmentService.saveEquipment(equipment);
		
		return "redirect:/equipment/list";
	}
	
	@GetMapping("/deleteEquipment")
	public String deleteEquipment(@RequestParam("itemId") int itemId) {
		equipmentService.deleteEquipment(itemId);
		
		return "redirect:/equipment/list";
	}
	
	@GetMapping("/updateEquipmentForm")
	public String updateEquipmentForm(@RequestParam("itemId") int itemId, Model model) {
		Equipment equipment = equipmentService.getEquipment(itemId);
		model.addAttribute("equipment", equipment);
		List<EquipmentCategory> categories = equipmentService.getCategories();
		categories.removeIf(e -> e.getId() == equipment.getCategory().getId());
		model.addAttribute("categories", categories);
		
		return "add-equipment-form";
	}
	
}

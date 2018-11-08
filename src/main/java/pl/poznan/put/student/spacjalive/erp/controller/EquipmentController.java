package pl.poznan.put.student.spacjalive.erp.controller;

import org.hibernate.JDBCException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.orm.hibernate5.HibernateJdbcException;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.student.spacjalive.erp.entity.Equipment;
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
		
		return "add-equipment-form";
	}
	
	@PostMapping("/addEquipment")
	public String addEquipment(@ModelAttribute("equipment") @Valid Equipment equipment, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-equipment-form";
		}
		
		try {
			equipmentService.saveEquipment(equipment);
		} catch (JDBCConnectionException e) {
			result.reject(String.valueOf(e.getErrorCode()), "Brak połączenia z bazą danych, skontaktuj się z administratorem.");
		} catch (SQLGrammarException e) {
			result.reject(String.valueOf(e.getErrorCode()), "Niepoprawna składnia zapytania, skontaktuj się z administratorem.");
		} catch (JDBCException e) {
			
			if (e.getSQLState().equalsIgnoreCase("12345")) {
				result.reject(String.valueOf(e.getErrorCode()), e.getSQLException().getMessage());
			} else {
				result.reject(String.valueOf(e.getErrorCode()), "Niepoprawne dane!");
			}
			return "add-equipment-form";
		} catch (HibernateJdbcException e) {
			
			if (e.getSQLException().getSQLState().equalsIgnoreCase("12346")) {
				Equipment eq = equipmentService.getEquipment(equipment.getId());
				model.addAttribute("equipment", eq);
				
				model.addAttribute("message", e.getSQLException().getMessage());
			} else {
				model.addAttribute("equipment", equipment);
				model.addAttribute("message", "Nieznany błąd, skontaktuj się administratorem!");
			}
			return "add-equipment-form";
		} catch (HibernateOptimisticLockingFailureException e) {
			return "redirect:/equipment/list";
		}
		
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
		
		return "add-equipment-form";
	}
	
}

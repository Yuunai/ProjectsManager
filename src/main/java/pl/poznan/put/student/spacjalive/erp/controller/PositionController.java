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
import pl.poznan.put.student.spacjalive.erp.entity.Position;
import pl.poznan.put.student.spacjalive.erp.service.PositionService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/position")
public class PositionController {
	
	@Autowired
	PositionService positionService;
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/list")
	public String listPositions(Model model) {
		List<Position> positions = positionService.getPositions();
		model.addAttribute("positions", positions);
		
		return "list-positions";
	}
	
	@GetMapping("/addPositionForm")
	public String addPositionForm(Model model) {
		Position position = new Position();
		model.addAttribute("position", position);
		
		return "add-position-form";
	}
	
	@GetMapping("/deletePosition")
	public String deletePosition(@RequestParam("positionId") int positionId, Model model) {
		try {
			positionService.deletePosition(positionId);
		} catch (JDBCConnectionException e) {
			model.addAttribute("message", "Brak połączenia z bazą danych, skontaktuj się z administratorem.");
		} catch (SQLGrammarException e) {
			model.addAttribute("message", "Niepoprawna składnia zapytania, skontaktuj się z administratorem.");
		} catch (JDBCException e) {
			List<Position> positions = positionService.getPositions();
			
			model.addAttribute("positions", positions);
			
			if (e.getSQLState().equalsIgnoreCase("12345")) {
				model.addAttribute("message", e.getSQLException().getMessage());
			} else {
				model.addAttribute("message", e.getSQLException().getMessage());
			}
			return "list-positions";
		}
		
		return "redirect:/position/list";
	}
	
	@GetMapping("updatePositionForm")
	public String updatePositionForm(@RequestParam("positionId") int positionId, Model model) {
		Position position = positionService.getPosition(positionId);
		model.addAttribute("position", position);
		
		return "add-position-form";
	}
	
	@PostMapping("/addPosition")
	public String addPosition(@ModelAttribute("position") @Valid Position position, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-position-form";
		}
		
		try {
			positionService.savePosition(position);
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
			return "add-position-form";
		} catch (HibernateJdbcException e) {
			
			if (e.getSQLException().getSQLState().equalsIgnoreCase("12346")) {
				Position pos = positionService.getPosition(position.getId());
				model.addAttribute("position", pos);
				model.addAttribute("message", e.getSQLException().getMessage());
			} else {
				model.addAttribute("position", position);
				model.addAttribute("message", "Nieznany błąd, skontaktuj się administratorem!");
			}
			return "add-event-form";
		} catch (HibernateOptimisticLockingFailureException e) {
			return "redirect:/position/list";
		}
		
		return "redirect:/position/list";
	}
	
}

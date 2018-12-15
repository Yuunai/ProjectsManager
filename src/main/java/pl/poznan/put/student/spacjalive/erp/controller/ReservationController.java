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
import pl.poznan.put.student.spacjalive.erp.entity.*;
import pl.poznan.put.student.spacjalive.erp.service.UserService;
import pl.poznan.put.student.spacjalive.erp.service.EquipmentService;
import pl.poznan.put.student.spacjalive.erp.service.EventService;
import pl.poznan.put.student.spacjalive.erp.service.ReservationService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/reservation")
public class ReservationController {
	
	private static final Logger logger = Logger.getLogger(ReservationController.class.getSimpleName());
	
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("u-M-d H:m");
	
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	EventService eventService;
	
	@Autowired
	EquipmentService equipmentService;
	
	@Autowired
	UserService userService;
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/list")
	public String listReservations(Model model) {
		//TODO exception handling
		List<Reservation> reservations = reservationService.getReservations();
		model.addAttribute("reservations", reservations);
		
		return "list-reservations";
	}
	
	@GetMapping("/addReservationForm")
	public String addReservationForm(Model model,
	                                 @RequestParam(name = "date-since") String dateSince,
	                                 @RequestParam(name = "time-since") String timeSince,
	                                 @RequestParam(name = "date-to") String dateTo,
	                                 @RequestParam(name = "time-to") String timeTo) {
		try {
			LocalDateTime since = LocalDateTime.parse(dateSince + " " + timeSince, dateTimeFormatter);
			LocalDateTime to = LocalDateTime.parse(dateTo + " " + timeTo, dateTimeFormatter);
			if(since.isBefore(LocalDateTime.now()) || to.isBefore(LocalDateTime.now())) {
				model.addAttribute("message", "Rezerwacje mogą dotyczyć wyłącznie przyszłości!");
				return listReservations(model);
			} else if(since.isAfter(to)) {
				model.addAttribute("message", "Data wypożyczenia nie może być po dacie oddania!");
				return listReservations(model);
			}
		} catch (DateTimeParseException e) {
			model.addAttribute("message", "Niepoprawny format daty/godziny!");
			return listReservations(model);
		}
		
		Reservation reservation = new Reservation(dateSince, timeSince, dateTo, timeTo);
		model.addAttribute("reservation", reservation);
		
		List<Equipment> equipmentList = equipmentService.getFreeEquipment(dateSince, timeSince, dateTo, timeTo);
		model.addAttribute("equipmentList", equipmentList);
		
		List<Event> events = eventService.getEvents(0);
		model.addAttribute("events", events);
		
		List<UserDetails> users = userService.getUsersDetails(true);
		model.addAttribute("users", users);
		
		return "add-reservation-form";
	}
	
	@PostMapping("/addReservation")
	public String addReservation(@ModelAttribute("reservation") @Valid Reservation reservation, BindingResult result,
	                           Model model) {
		logger.info(reservation.toString());
		
		if (result.hasErrors()) {
			model.addAttribute("reservation", reservation);
			
//			List<Equipment> equipmentList = equipmentService.getFreeEquipment();
//			model.addAttribute("equipmentList", equipmentList);
			
			List<Event> events = eventService.getEvents(0);
			model.addAttribute("events", events);
			
			List<User> users = userService.getUsers(true);
			model.addAttribute("employees", users);
			
			return "add-reservation-form";
		}
		
		try {
			reservationService.saveReservation(reservation);
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
			return "add-reservation-form";
		} catch (HibernateJdbcException e) {
			
//			List<Equipment> equipmentList = equipmentService.getFreeEquipment();
//			model.addAttribute("equipmentList", equipmentList);
			
			List<Event> events = eventService.getEvents(0);
			model.addAttribute("events", events);
			
			List<User> users = userService.getUsers(true);
			model.addAttribute("employees", users);
			
			if (e.getSQLException().getSQLState().equalsIgnoreCase("12346")) {
				Reservation len = reservationService.getReservation(reservation.getId());
				model.addAttribute("reservation", len);
				model.addAttribute("message", e.getSQLException().getMessage());
			} else {
				model.addAttribute("reservation", reservation);
				model.addAttribute("message", "Nieznany błąd, skontaktuj się administratorem!");
			}
			return "add-reservation-form";
		} catch (HibernateOptimisticLockingFailureException e) {
			return "redirect:/reservation/list";
		}
		
		return "redirect:/reservation/list";
	}
	
	@GetMapping("/updateReservationForm")
	public String updateLendingForm(Model model, @RequestParam("reservationId") int reservationId) {
		Reservation reservation = reservationService.getReservation(reservationId);
		model.addAttribute("reservation", reservation);
		
		List<Equipment> equipmentList = equipmentService.getFreeEquipment(reservation.getDateSince(), reservation
				.getTimeSince(), reservation.getDateTo(), reservation.getTimeTo());
		model.addAttribute("equipmentList", equipmentList);
		
		List<Event> events = eventService.getEvents(0);
		events.remove(reservation.getEvent());
		model.addAttribute("events", events);
		
		List<User> users = userService.getUsers(true);
		users.remove(reservation.getUser());
		model.addAttribute("employees", users);
		
		return "add-reservation-form";
	}
	
	@GetMapping("/deleteReservation")
	public String deleteLending(@RequestParam("reservationId") int reservationId) {
		reservationService.deleteReservation(reservationId);
		
		return "redirect:/reservation/list";
	}
	
}

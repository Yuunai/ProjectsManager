package pl.poznan.put.student.spacjalive.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.student.spacjalive.erp.entity.*;
import pl.poznan.put.student.spacjalive.erp.exceptions.NotFoundException;
import pl.poznan.put.student.spacjalive.erp.service.*;
import pl.poznan.put.student.spacjalive.erp.viewmodel.ReservationViewModel;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/reservation")
public class ReservationController {
	
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("u-M-d H:m:s");
	
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
		List<Reservation> reservations = reservationService.getReservations();
		model.addAttribute("reservations", reservations);

		List<UserDetails> users = userService.getUsersDetails(true);
		model.addAttribute("users", users);

		List<Integer> userIds = users.stream().map(e -> e.getUserId()).collect(Collectors.toList());
		Map<Integer, String> usersNames = userService.getUsersNamesMap(userIds);
		model.addAttribute("usersNames", usersNames);

		return "list-reservations";
	}
	
	@GetMapping("/addReservationForm")
	public String addReservationForm(Model model,
	                                 @RequestParam(name = "eventId") int eventId) throws NotFoundException {
		Event event = eventService.getEvent(eventId);
		LocalDateTime dateTimeTo = LocalDateTime.parse(event.getDate() + " " + event.getTime(), dateTimeFormatter);
		dateTimeTo = dateTimeTo.plusHours(2);
		String stringDateTimeTo = dateTimeTo.format(dateTimeFormatter);
		
		Reservation reservation =
				new Reservation(event.getDate(), event.getTime(),
						stringDateTimeTo.split(" ")[0], stringDateTimeTo.split(" ")[1]);
		ReservationViewModel reservationView = new ReservationViewModel(reservation);
		reservationView.setEventId(eventId);
		model.addAttribute("reservation", reservationView);
		
		List<Equipment> equipmentList = equipmentService.getFreeEquipment(event.getDate(), event.getTime(),
				stringDateTimeTo.split(" ")[0], stringDateTimeTo.split(" ")[1]);
		model.addAttribute("equipmentList", equipmentList);
		
		return "add-reservation-form";
	}
	
	@PostMapping("/addReservation")
	public String addReservation(
			HttpServletRequest request,
			@ModelAttribute("reservation") @Valid ReservationViewModel reservationViewModel,
			BindingResult result) throws NotFoundException {
		if (result.hasErrors()) {
			return "add-reservation-form";
		}
		
		Reservation reservation = new Reservation();
		reservation.setId(reservationViewModel.getId());
		reservation.setComments(reservationViewModel.getComments());
		reservation.setDateSince(reservationViewModel.getDateSince());
		reservation.setTimeSince(reservationViewModel.getTimeSince());
		reservation.setDateTo(reservationViewModel.getDateTo());
		reservation.setTimeTo(reservationViewModel.getTimeTo());
		reservation.setUser(userService.getUser((int) request.getSession().getAttribute("userId")));
		reservation.setEvent(eventService.getEvent(reservationViewModel.getEventId()));
		reservation.setEquipmentList(reservationViewModel.getEquipmentList());
		reservation.setLastUpdate(reservationViewModel.getLastUpdate());
		reservationService.saveReservation(reservation);
		
		return "redirect:/reservation/list";
	}
	
	@GetMapping("/updateReservationForm")
	public String updateLendingForm(Model model, @RequestParam("reservationId") int reservationId) throws NotFoundException {
		Reservation reservation = reservationService.getReservation(reservationId);
		model.addAttribute("reservation", new ReservationViewModel(reservation));
		
		List<Equipment> equipmentList = equipmentService.getFreeEquipment(reservation.getDateSince(), reservation
				.getTimeSince(), reservation.getDateTo(), reservation.getTimeTo());
		model.addAttribute("equipmentList", equipmentList);
		return "reservation-details";
	}
	
	@GetMapping("/deleteReservation")
	public String deleteLending(@RequestParam("reservationId") int reservationId) {
		reservationService.deleteReservation(reservationId);
		
		return "redirect:/reservation/list";
	}
	
}

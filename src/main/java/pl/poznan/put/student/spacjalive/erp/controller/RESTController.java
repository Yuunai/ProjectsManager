package pl.poznan.put.student.spacjalive.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.student.spacjalive.erp.service.EquipmentService;

@RestController
@RequestMapping("/api")
public class RESTController {

	@Autowired
	private EquipmentService equipmentService;
	
	@GetMapping(value = "/getFreeEquipment", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getFreeEquipment(@RequestParam(name = "date-since") String dateSince,
	                               @RequestParam(name = "time-since") String timeSince,
	                               @RequestParam(name = "date-to") String dateTo,
	                               @RequestParam(name = "time-to") String timeTo) {
		return equipmentService.getFreeEquipmentJson(dateSince, timeSince, dateTo, timeTo).toString();
	}
	
}

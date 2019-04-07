package pl.poznan.put.student.projectsmanager.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.student.projectsmanager.exceptions.NotFoundException;

import java.util.List;

@Service("equipmentService")
@Transactional
public class EquipmentServiceImpl implements EquipmentService {
	
	@Autowired
	EquipmentRepository equipmentRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Override
	public List<Equipment> getEquipmentList() {
		return equipmentRepository.getEquipmentList();
	}
	
	@Override
	public EquipmentCategory getCategory(int id) throws NotFoundException {
		return equipmentRepository.getCategory(id);
	}
	
	@Override
	public List<Equipment> getEquipmentByCategory(int id) {
		return equipmentRepository.getEquipmentByCategory(id);
	}
	
	@Override
	public List<EquipmentCategory> getCategories() {
		return equipmentRepository.getCategories();
	}
	
	@Override
	public void saveEquipment(Equipment equipment) {
		equipmentRepository.saveEquipment(equipment);
	}
	
	@Override
	public Equipment getEquipment(int id) throws NotFoundException {
		return equipmentRepository.getEquipment(id);
	}
	
	@Override
	public void deleteEquipment(int id) {
		equipmentRepository.deleteEquipment(id);
	}
	
	@Override
	public List<Equipment> getFreeEquipment(String dateSince, String timeSince, String dateTo, String timeTo) {
		List<Equipment> equipment = equipmentRepository.getEquipmentList();
		List<Reservation> reservations = reservationRepository.getReservations(dateSince, timeSince, dateTo, timeTo);
		reservations.stream().forEach(r -> equipment.removeAll(r.getEquipmentList()));
		
		return equipment;
	}
	
	@Override
	public JSONArray getFreeEquipmentJson(String dateSince, String timeSince, String dateTo, String timeTo) {
		List<Equipment> equipment = getFreeEquipment(dateSince, timeSince, dateTo, timeTo);
		JSONArray result = new JSONArray();
		for(Equipment eq : equipment) {
			JSONObject eqToPut = new JSONObject();
			eqToPut.put("id", eq.getId());
			eqToPut.put("name", eq.getName());
			eqToPut.put("category", eq.getCategory().getName());
			eqToPut.put("state", eq.getState());
			result.put(eqToPut);
		}
		
		return result;
	}
}

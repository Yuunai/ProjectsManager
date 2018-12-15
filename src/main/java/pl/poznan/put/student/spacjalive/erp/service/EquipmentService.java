package pl.poznan.put.student.spacjalive.erp.service;

import pl.poznan.put.student.spacjalive.erp.entity.Equipment;
import pl.poznan.put.student.spacjalive.erp.entity.EquipmentCategory;

import java.util.List;

public interface EquipmentService {
	
	List<Equipment> getEquipmentList();
	
	EquipmentCategory getCategory(int id);
	
	List<Equipment> getEquipmentByCategory(int id);
	
	List<EquipmentCategory> getCategories();
	
	void saveEquipment(Equipment equipment);
	
	Equipment getEquipment(int id);
	
	void deleteEquipment(int id);
	
	List<Equipment> getFreeEquipment(String dateSince, String timeSince, String dateTo, String timeTo);
	
}

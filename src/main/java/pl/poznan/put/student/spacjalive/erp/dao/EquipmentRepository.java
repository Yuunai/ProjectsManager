package pl.poznan.put.student.spacjalive.erp.dao;

import pl.poznan.put.student.spacjalive.erp.entity.Equipment;
import pl.poznan.put.student.spacjalive.erp.entity.EquipmentCategory;

import java.util.List;

public interface EquipmentRepository {
	
	List<Equipment> getEquipmentList();
	
	List<Equipment> getEquipmentByCategory(int id);
	
	EquipmentCategory getCategory(int id);
	
	List<EquipmentCategory> getCategories();
	
	void saveEquipment(Equipment equipment);
	
	Equipment getEquipment(int id);
	
	void deleteEquipment(int id);
	
}

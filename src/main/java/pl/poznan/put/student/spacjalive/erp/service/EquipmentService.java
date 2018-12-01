package pl.poznan.put.student.spacjalive.erp.service;

import pl.poznan.put.student.spacjalive.erp.entity.Equipment;
import pl.poznan.put.student.spacjalive.erp.entity.EquipmentCategory;

import java.util.List;

public interface EquipmentService {
	
	public List<Equipment> getFreeEquipment();
	
	public List<Equipment> getEquipmentList();
	
	public EquipmentCategory getCategory(int id);
	
	public List<Equipment> getEquipmentByCategory(int id);
	
	public List<EquipmentCategory> getCategories();
	
	public void saveEquipment(Equipment equipment);
	
	public Equipment getEquipment(int id);
	
	public void deleteEquipment(int id);
	
}

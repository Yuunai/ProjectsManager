package pl.poznan.put.student.spacjalive.erp.dao;

import pl.poznan.put.student.spacjalive.erp.entity.Equipment;

import java.util.List;

public interface EquipmentRepository {
	
	public List<Equipment> getFreeEquipment();
	
	public List<Equipment> getEquipmentList();
	
	public void saveEquipment(Equipment equipment);
	
	public Equipment getEquipment(int id);
	
	public void deleteEquipment(int id);
	
}

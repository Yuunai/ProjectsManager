package pl.poznan.put.student.spacjalive.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.student.spacjalive.erp.dao.EquipmentRepository;
import pl.poznan.put.student.spacjalive.erp.entity.Equipment;
import pl.poznan.put.student.spacjalive.erp.entity.EquipmentCategory;

import java.util.List;

@Service("equipmentService")
@Transactional
public class EquipmentServiceImpl implements EquipmentService {
	
	@Autowired
	EquipmentRepository equipmentRepository;
	
	@Override
	public List<Equipment> getFreeEquipment() {
		return equipmentRepository.getFreeEquipment();
	}
	
	@Override
	public List<Equipment> getEquipmentList() {
		return equipmentRepository.getEquipmentList();
	}
	
	@Override
	public EquipmentCategory getCategory(int id) {
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
	public Equipment getEquipment(int id) {
		return equipmentRepository.getEquipment(id);
	}
	
	@Override
	public void deleteEquipment(int id) {
		equipmentRepository.deleteEquipment(id);
	}
}

package pl.poznan.put.student.spacjalive.erp.converter;

import org.springframework.core.convert.converter.Converter;
import pl.poznan.put.student.spacjalive.erp.entity.Equipment;
import pl.poznan.put.student.spacjalive.erp.exceptions.NotFoundException;
import pl.poznan.put.student.spacjalive.erp.service.EquipmentService;

public class StringEquipmentIdToEquipmentConverter implements Converter<String, Equipment> {
	
	private final EquipmentService equipmentService;
	
	public StringEquipmentIdToEquipmentConverter(EquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}
	
	@Override
	public Equipment convert(String s) {
		Equipment equipment;
		try {
			equipment = equipmentService.getEquipment(Integer.valueOf(s));
		} catch (NotFoundException e) {
			equipment = null;
		}
		
		return equipment;
	}
}

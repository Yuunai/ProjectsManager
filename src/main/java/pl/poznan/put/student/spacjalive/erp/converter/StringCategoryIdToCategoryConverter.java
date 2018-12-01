package pl.poznan.put.student.spacjalive.erp.converter;

import org.springframework.core.convert.converter.Converter;
import pl.poznan.put.student.spacjalive.erp.entity.Equipment;
import pl.poznan.put.student.spacjalive.erp.entity.EquipmentCategory;
import pl.poznan.put.student.spacjalive.erp.service.EquipmentService;

public class StringCategoryIdToCategoryConverter implements Converter<String, EquipmentCategory> {
	
	private final EquipmentService equipmentService;
	
	public StringCategoryIdToCategoryConverter(EquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}
	
	@Override
	public EquipmentCategory convert(String s) {
		EquipmentCategory category;
		category = equipmentService.getCategory(Integer.valueOf(s));
		
		return category;
	}
}

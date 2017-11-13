package pl.poznan.put.student.spacjalive.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.student.spacjalive.erp.dao.EquipmentDAO;
import pl.poznan.put.student.spacjalive.erp.entity.Equipment;

import java.util.List;

@Service("equipmentService")
@Transactional
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    EquipmentDAO equipmentDAO;

    @Override
    public List<Equipment> getEquipments() {
        return equipmentDAO.getEquipments();
    }

    @Override
    public void saveEquipment(Equipment equipment) {
        equipmentDAO.saveEquipment(equipment);
    }

    @Override
    public Equipment getEquipment(int id) {
        return equipmentDAO.getEquipment(id);
    }

    @Override
    public void deleteEquipment(int id) {
        equipmentDAO.deleteEquipment(id);
    }
}

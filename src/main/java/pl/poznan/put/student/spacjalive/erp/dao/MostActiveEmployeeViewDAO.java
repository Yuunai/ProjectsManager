package pl.poznan.put.student.spacjalive.erp.dao;

import pl.poznan.put.student.spacjalive.erp.entity.MostActiveEmployeeView;

import java.util.List;

public interface MostActiveEmployeeViewDAO {

    List<MostActiveEmployeeView> getEmployees();

}

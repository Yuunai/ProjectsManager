package pl.poznan.put.student.spacjalive.erp.service;

import pl.poznan.put.student.spacjalive.erp.entity.MostActiveEmployeeView;

import java.util.List;

public interface MostActiveEmployeeViewService {

    List<MostActiveEmployeeView> getEmployees();
}

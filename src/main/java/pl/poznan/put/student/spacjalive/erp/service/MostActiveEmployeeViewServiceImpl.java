package pl.poznan.put.student.spacjalive.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.student.spacjalive.erp.dao.MostActiveEmployeeViewDAO;
import pl.poznan.put.student.spacjalive.erp.entity.MostActiveEmployeeView;

import java.util.List;

@Transactional
@Service("mostActiveEmployeeViewService")
public class MostActiveEmployeeViewServiceImpl implements MostActiveEmployeeViewService {

    @Autowired
    MostActiveEmployeeViewDAO mostActiveEmployeeViewDAO;

    @Override
    public List<MostActiveEmployeeView> getEmployees() {
        return mostActiveEmployeeViewDAO.getEmployees();
    }
}

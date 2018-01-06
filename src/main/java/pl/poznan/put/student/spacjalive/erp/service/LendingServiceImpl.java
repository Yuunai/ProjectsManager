package pl.poznan.put.student.spacjalive.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.student.spacjalive.erp.dao.LendingDAO;
import pl.poznan.put.student.spacjalive.erp.entity.Lending;

import java.util.List;

@Transactional
@Service("lendingService")
public class LendingServiceImpl implements LendingService {

    @Autowired
    LendingDAO lendingDAO;

    @Override
    public List<Lending> getLendings() {
        return lendingDAO.getLendings();
    }

    @Override
    public void saveLending(Lending lending) {
        lendingDAO.saveLending(lending);
    }

    @Override
    public void deleteLending(int id) {
        lendingDAO.deleteLending(id);
    }

    @Override
    public Lending getLending(int id) {
        return lendingDAO.getLending(id);
    }
}

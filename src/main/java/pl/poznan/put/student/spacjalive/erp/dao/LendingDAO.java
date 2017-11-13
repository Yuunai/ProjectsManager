package pl.poznan.put.student.spacjalive.erp.dao;

import pl.poznan.put.student.spacjalive.erp.entity.Lending;

import java.util.List;

public interface LendingDAO {

    public List<Lending> getLendings();

    public void saveLending(Lending lending);

    public void deleteLending(int id);

    public Lending getLending(int id);

}

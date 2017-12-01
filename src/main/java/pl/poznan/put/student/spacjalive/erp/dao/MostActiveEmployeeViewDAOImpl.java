package pl.poznan.put.student.spacjalive.erp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.poznan.put.student.spacjalive.erp.entity.MostActiveEmployeeView;

import java.util.List;

@Repository
public class MostActiveEmployeeViewDAOImpl implements MostActiveEmployeeViewDAO {

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public List<MostActiveEmployeeView> getEmployees() {
        Session session = sessionFactory.getCurrentSession();

        Query<MostActiveEmployeeView> query = session.createQuery("FROM MostActiveEmployeeView");

        List<MostActiveEmployeeView> employees = query.getResultList();

        return employees;
    }
}

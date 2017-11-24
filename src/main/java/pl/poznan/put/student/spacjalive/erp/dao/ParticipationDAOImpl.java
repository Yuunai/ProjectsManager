package pl.poznan.put.student.spacjalive.erp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.poznan.put.student.spacjalive.erp.entity.Employee;
import pl.poznan.put.student.spacjalive.erp.entity.Event;
import pl.poznan.put.student.spacjalive.erp.entity.Participation;
import pl.poznan.put.student.spacjalive.erp.entity.Role;

import java.util.List;
import java.util.logging.Logger;

@Repository
public class ParticipationDAOImpl implements ParticipationDAO {

    Logger logger = Logger.getLogger(ParticipationDAOImpl.class.getName());

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Participation> getParticipationsByEventId(int id) {

        Session session = sessionFactory.getCurrentSession();

        Query<Participation> query = session.createQuery("FROM Participation WHERE event.id=:eventId");
        query.setParameter("eventId", id);

        List<Participation> participations = query.getResultList();

        return participations;
    }

    @Override
    public List<Participation> getParticipationsByEmployeeId(int id) {

        Session session = sessionFactory.getCurrentSession();

        Query<Participation> query = session.createQuery("FROM Participation WHERE employee.id=:employeeId");

        query.setParameter("employeeId", id);

        List<Participation> participations = query.getResultList();

        return participations;
    }

    @Override
    public List<Participation> getParticipationsByRoleId(int id) {

        Session session = sessionFactory.getCurrentSession();

        Query<Participation> query = session.createQuery("FROM Participation WHERE role.id=:roleId");

        query.setParameter("roleId", id);

        List<Participation> participations = query.getResultList();

        return participations;
    }

    @Override
    public void deleteParticipation(int eventId, int roleId, int employeeId) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("DELETE FROM Participation WHERE event.id=:eventId AND role.id=:roleId AND employee.id=:employeeId");

        query.setParameter("eventId", eventId);

        query.setParameter("roleId", roleId);

        query.setParameter("employeeId", employeeId);

        query.executeUpdate();
    }

    @Override
    public void addParticipation(Participation participation) {

        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(participation);

        }
}

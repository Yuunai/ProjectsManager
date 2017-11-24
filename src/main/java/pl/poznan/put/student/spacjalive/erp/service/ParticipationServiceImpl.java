package pl.poznan.put.student.spacjalive.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.student.spacjalive.erp.dao.ParticipationDAO;
import pl.poznan.put.student.spacjalive.erp.entity.Employee;
import pl.poznan.put.student.spacjalive.erp.entity.Event;
import pl.poznan.put.student.spacjalive.erp.entity.Participation;
import pl.poznan.put.student.spacjalive.erp.entity.Role;

import java.util.List;

@Transactional
@Service("participationService")
public class ParticipationServiceImpl implements ParticipationService {

    @Autowired
    ParticipationDAO participationDAO;

    @Override
    public List<Participation> getParticipationsByEventId(int id) {
        return participationDAO.getParticipationsByEventId(id);
    }

    @Override
    public List<Participation> getParticipationsByEmployeeId(int id) {
        return participationDAO.getParticipationsByEmployeeId(id);
    }

    @Override
    public List<Participation> getParticipationsByRoleId(int id) {
        return participationDAO.getParticipationsByRoleId(id);
    }

    @Override
    public void deleteParticipation(int eventId, int roleId, int employeeId) {
        participationDAO.deleteParticipation(eventId, roleId, employeeId);
    }

    @Override
    public void addParticipation(Participation participation) {
        participationDAO.addParticipation(participation);
    }
}

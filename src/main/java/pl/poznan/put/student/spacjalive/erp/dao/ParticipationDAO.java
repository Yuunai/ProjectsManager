package pl.poznan.put.student.spacjalive.erp.dao;

import pl.poznan.put.student.spacjalive.erp.entity.Participation;

import java.util.List;

public interface ParticipationDAO {

    public List<Participation> getParticipationsByEventId(int id);

    public List<Participation> getParticipationsByEmployeeId(int id);

    public List<Participation> getParticipationsByRoleId(int id);

    public void deleteParticipation(int eventId, int roleId, int employeeId);

    public void addParticipation(Participation participation);

}

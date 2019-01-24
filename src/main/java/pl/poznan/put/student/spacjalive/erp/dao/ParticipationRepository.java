package pl.poznan.put.student.spacjalive.erp.dao;

import pl.poznan.put.student.spacjalive.erp.entity.Participation;

import java.util.List;

public interface ParticipationRepository {
	
	List<Participation> getParticipationsByEventId(int id);
	
	List<Participation> getParticipationsByUserId(int id);
	
	List<Participation> getParticipationsByRoleId(int id);
	
	void deleteParticipation(int eventId, int roleId, int employeeId);
	
	void addParticipation(Participation participation);
	
	void deleteEventParticipations(int eventId);
	
}

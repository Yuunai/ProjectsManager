package pl.poznan.put.student.projectsmanager.service;

import org.json.JSONArray;

import java.util.List;

public interface ParticipationService {
	
	List<Participation> getParticipationsByEventId(int id);
	
	JSONArray getParticipationsJsonByEventId(int id);
	
	List<Participation> getParticipationsByUserId(int id);
	
	List<Participation> getParticipationsByRoleId(int id);
	
	void deleteParticipation(int eventId, int roleId, int employeeId);
	
	void addParticipation(Participation participation);
	
}

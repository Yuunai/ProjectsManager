package pl.poznan.put.student.spacjalive.erp.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.student.spacjalive.erp.dao.ParticipationRepository;
import pl.poznan.put.student.spacjalive.erp.entity.Participation;
import pl.poznan.put.student.spacjalive.erp.entity.UserDetails;
import pl.poznan.put.student.spacjalive.erp.exceptions.NotFoundException;

import java.util.List;

@Transactional
@Service("participationService")
public class ParticipationServiceImpl implements ParticipationService {
	
	@Autowired
	ParticipationRepository participationRepository;
	
	@Autowired
	UserService userService;
	
	@Override
	public List<Participation> getParticipationsByEventId(int id) {
		return participationRepository.getParticipationsByEventId(id);
	}
	
	@Override
	public JSONArray getParticipationsJsonByEventId(int id) {
		JSONArray result = new JSONArray();
		List<Participation> participations = getParticipationsByEventId(id);
		for(Participation participation : participations) {
			JSONObject p = new JSONObject();
			try {
				UserDetails details = userService.getUserDetails(participation.getUser().getId());
				p.put("user", details.getLastName() + " " + details.getFirstName());
				p.put("roleId", participation.getRole().getId());
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
			result.put(p);
		}
		
		return result;
	}
	
	@Override
	public List<Participation> getParticipationsByUserId(int id) {
		return participationRepository.getParticipationsByUserId(id);
	}
	
	@Override
	public List<Participation> getParticipationsByRoleId(int id) {
		return participationRepository.getParticipationsByRoleId(id);
	}
	
	@Override
	public void deleteParticipation(int eventId, int roleId, int userId) {
		participationRepository.deleteParticipation(eventId, roleId, userId);
	}
	
	@Override
	public void addParticipation(Participation participation) {
		participationRepository.addParticipation(participation);
	}
}

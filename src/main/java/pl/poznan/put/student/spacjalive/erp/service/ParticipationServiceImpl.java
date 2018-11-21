package pl.poznan.put.student.spacjalive.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.student.spacjalive.erp.dao.ParticipationRepository;
import pl.poznan.put.student.spacjalive.erp.entity.Participation;

import java.util.List;

@Transactional
@Service("participationService")
public class ParticipationServiceImpl implements ParticipationService {
	
	@Autowired
	ParticipationRepository participationRepository;
	
	@Override
	public List<Participation> getParticipationsByEventId(int id) {
		return participationRepository.getParticipationsByEventId(id);
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

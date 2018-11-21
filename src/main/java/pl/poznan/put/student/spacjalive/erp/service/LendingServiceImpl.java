package pl.poznan.put.student.spacjalive.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.student.spacjalive.erp.dao.LendingRepository;
import pl.poznan.put.student.spacjalive.erp.entity.Lending;

import java.util.List;

@Transactional
@Service("lendingService")
public class LendingServiceImpl implements LendingService {
	
	@Autowired
	LendingRepository lendingRepository;
	
	@Override
	public List<Lending> getLendings() {
		return lendingRepository.getLendings();
	}
	
	@Override
	public void saveLending(Lending lending) {
		lendingRepository.saveLending(lending);
	}
	
	@Override
	public void deleteLending(int id) {
		lendingRepository.deleteLending(id);
	}
	
	@Override
	public Lending getLending(int id) {
		return lendingRepository.getLending(id);
	}
}

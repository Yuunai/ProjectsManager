package pl.poznan.put.student.spacjalive.erp.service;

import pl.poznan.put.student.spacjalive.erp.entity.Lending;

import java.util.List;

public interface LendingService {
	
	public List<Lending> getLendings();
	
	public void saveLending(Lending lending);
	
	public void deleteLending(int id);
	
	public Lending getLending(int id);
	
}

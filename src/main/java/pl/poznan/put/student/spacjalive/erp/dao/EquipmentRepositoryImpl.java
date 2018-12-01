package pl.poznan.put.student.spacjalive.erp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.poznan.put.student.spacjalive.erp.entity.Equipment;
import pl.poznan.put.student.spacjalive.erp.entity.EquipmentCategory;

import java.util.List;

@Repository
public class EquipmentRepositoryImpl implements EquipmentRepository {
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Equipment> getFreeEquipment() {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Equipment> query = session.getNamedQuery("getFreeEquipment");
		return query.getResultList();
	}
	
	@Override
	public List<Equipment> getEquipmentList() {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Equipment> query = session.createQuery("from Equipment " + "ORDER BY name", Equipment.class);
		return query.getResultList();
		
	}
	
	@Override
	public List<Equipment> getEquipmentByCategory(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Equipment> query = session.createQuery("FROM Equipment WHERE category.id=:category");
		query.setParameter("category", id);
		
		return query.getResultList();
	}
	
	@Override
	public EquipmentCategory getCategory(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(EquipmentCategory.class, id);
	}
	
	@Override
	public List<EquipmentCategory> getCategories() {
		Session session = sessionFactory.getCurrentSession();
		
		Query<EquipmentCategory> query = session.createQuery("FROM EquipmentCategory ORDER BY name");
		
		return query.getResultList();
	}
	
	@Override
	public void saveEquipment(Equipment equipment) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(equipment);
	}
	
	@Override
	public Equipment getEquipment(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Equipment.class, id);
	}
	
	@Override
	public void deleteEquipment(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("DELETE FROM Equipment WHERE id=:equipmentId");
		query.setParameter("equipmentId", id);
		
		query.executeUpdate();
	}
}

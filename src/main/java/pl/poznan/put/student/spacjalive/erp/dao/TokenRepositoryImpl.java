package pl.poznan.put.student.spacjalive.erp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.poznan.put.student.spacjalive.erp.entity.Token;

import javax.persistence.Query;
import java.util.List;

@Repository
public class TokenRepositoryImpl implements TokenRepository {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void saveToken(Token token) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(token);
	}
	
	@Override
	public void deleteToken(int userId, int tokenType) {
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("DELETE FROM Token WHERE userId=:userId and type=:tokenType");
		query.setParameter("userId", userId);
		query.setParameter("tokenType", tokenType);
		query.executeUpdate();
	}
	
	@Override
	public Token getTokenByHash(String hash) {
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("FROM Token WHERE hash=:hash");
		query.setParameter("hash", hash);
		List<Token> queryResult = query.getResultList();
		if(queryResult.isEmpty() || queryResult.size() > 1) {
			return null;
		} else {
			return queryResult.get(0);
		}
	}
	
	@Override
	public List<Token> getUserTokens(int userId) {
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("FROM Token WHERE userId=:userId");
		query.setParameter("userId", userId);
		return query.getResultList();
	}
}

package pl.poznan.put.student.spacjalive.erp.dao;

import pl.poznan.put.student.spacjalive.erp.entity.Token;

import java.util.List;

public interface TokenRepository {
	
	void saveToken(Token token);
	
	void deleteToken(int userId, int tokenType);
	
	Token getTokenByHash(String hash);
	
	List<Token> getUserTokens(int userId);
	
}

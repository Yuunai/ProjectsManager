package pl.poznan.put.student.projectsmanager.dao;

import pl.poznan.put.student.projectsmanager.entity.Token;

import java.util.List;

public interface TokenRepository {
	
	void saveToken(Token token);
	
	void deleteToken(int userId, int tokenType);
	
	Token getTokenByHash(String hash);
	
	List<Token> getUserTokens(int userId);
	
}

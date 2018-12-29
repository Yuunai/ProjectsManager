package pl.poznan.put.student.spacjalive.erp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "token")
public class Token implements Serializable{
	
	public static final int RESET_PASSWORD_TOKEN = 1;
	
	/** In minutes **/
	public static final int TOKEN_EXPIRATION_TIME = 30;
	
	@Id
	@Column(name = "user_id")
	private int userId;
	
	@Id
	@Column(name = "type")
	private int type;
	
	@Column(name = "hash")
	private String hash;
	
	@Column(name = "expiration_date")
	private LocalDateTime expirationDate;
	
	public Token() {
	}
	
	public Token(int userId, int type, String hash, LocalDateTime expirationDate) {
		this.userId = userId;
		this.type = type;
		this.hash = hash;
		this.expirationDate = expirationDate;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public String getHash() {
		return hash;
	}
	
	public void setHash(String hash) {
		this.hash = hash;
	}
	
	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}
	
	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	@Override
	public String toString() {
		return "Token{" +
				"userId=" + userId +
				", type=" + type +
				", hash='" + hash + '\'' +
				", expirationDate=" + expirationDate +
				'}';
	}
}

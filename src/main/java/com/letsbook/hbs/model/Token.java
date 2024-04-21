package com.letsbook.hbs.model;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Token{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String tokenValue;
    private LocalDateTime expirationDateTime;
    
    
	public Token() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getTokenValue() {
		return tokenValue;
	}
	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}
	public LocalDateTime getExpirationDateTime() {
		return expirationDateTime;
	}
	public void setExpirationDateTime(LocalDateTime expirationDateTime) {
		this.expirationDateTime = expirationDateTime;
	}
	@Override
	public String toString() {
		return "Token [id=" + id + ", userId=" + userId + ", tokenValue=" + tokenValue + ", expirationDateTime="
				+ expirationDateTime + "]";
	}


}

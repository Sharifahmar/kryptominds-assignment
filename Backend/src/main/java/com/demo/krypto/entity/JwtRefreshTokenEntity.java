package com.demo.krypto.entity;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "REFRESH_TOKENS")
public class JwtRefreshTokenEntity {
	@Id
	private String token;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private UsersEntity user;

	private Instant expirationDateTime;

	public JwtRefreshTokenEntity() {

	}

	public JwtRefreshTokenEntity(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UsersEntity getUser() {
		return user;
	}


	public void setUser(UsersEntity user) {
		this.user = user;
	}

	public Instant getExpirationDateTime() {
		return expirationDateTime;
	}

	public void setExpirationDateTime(Instant expirationDateTime) {
		this.expirationDateTime = expirationDateTime;
	}
}

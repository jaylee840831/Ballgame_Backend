package com.example.ballgame.security;

import java.util.Date;

import com.example.ballgame.dao.UserDao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {

  @Id
  @GeneratedValue
  public Long id;

  @Column(unique = true)
  public String token;

  @Enumerated(EnumType.STRING)
  public TokenType tokenType = TokenType.BEARER;

  public boolean revoked;

  public boolean expired;
  
  public Date createDate;
  
  public Date updateDate;

  @ManyToOne
  @JoinColumn(name = "user_id")
  public UserDao user;
  

public String getToken() {
	return token;
}

public void setToken(String token) {
	this.token = token;
}

public Date getCreateDate() {
	return createDate;
}

public void setCreateDate(Date createDate) {
	this.createDate = createDate;
}

public Date getUpdateDate() {
	return updateDate;
}

public void setUpdateDate(Date updateDate) {
	this.updateDate = updateDate;
}

public TokenType getTokenType() {
	return tokenType;
}

public void setTokenType(TokenType tokenType) {
	this.tokenType = tokenType;
}

public UserDao getUser() {
	return user;
}

public void setUser(UserDao user) {
	this.user = user;
}

public boolean isRevoked() {
	return revoked;
}

public void setRevoked(boolean revoked) {
	this.revoked = revoked;
}

public boolean isExpired() {
	return expired;
}

public void setExpired(boolean expired) {
	this.expired = expired;
}
}
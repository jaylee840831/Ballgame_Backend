package com.example.ballgame.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


/*jwt token由三部分組成
 * 
 * 1. header: 包含聲明、演算法類型
 * 		例如:
 * 		{
	  	"alg": "HS256",
	  	"typ": "JWT"
		}
	
	2. payload: 這裡放聲明內容，可以說就是存放溝通訊息的地方，在定義上有 3 種聲明 (Claims)
		Reserved (註冊聲明)
		Public (公開聲明)
		Private (私有聲明)
		
		註冊聲明參數 (建議但不強制使用)
		iss (Issuer) - jwt簽發者
		sub (Subject) - jwt所面向的用戶
		aud (Audience) - 接收jwt的一方
		exp (Expiration Time) - jwt的過期時間，這個過期時間必須要大於簽發時間
		nbf (Not Before) - 定義在什麼時間之前，該jwt都是不可用的
		iat (Issued At) - jwt的簽發時間
		jti (JWT ID) - jwt的唯一身份標識，主要用來作為一次性token,從而迴避重放攻擊
		
		例如:
		{
		  "sub": "1234567890",
		  "name": "John Doe",
		  "iat": 1516239022
		}
		
	3. Signature: 由三個部分組成
		header (base64後的)
		payload (base64後的)
		secret(密鑰) 簽發驗證都需要此密鑰 不可外流
		
		例如: HMACSHA256(base64UrlEncode(header) + "." + base64UrlEncode(payload), 'secret')
		
		
	最後由三個部分由 . 組成
*/

@Service
public class JwtService {
	
	//密鑰
	private static final String SECRET_KEY = "";

	public String extractUserEmail(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	}
	
	//產生jwt token
	public String generateToken(Map<String, Object> extractClaims,
			UserDetails userDetails) {
		return Jwts
				.builder()
				.setClaims(extractClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000*60*24))
				.signWith(getSignInkey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	//檢查token是否正確
	public boolean isTokenVaild(String token, UserDetails userDetails){
		final String username = extractUserEmail(token);
		return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}
	
	//檢查token是否到期
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	//加入密鑰，解析jwt token，取得聲明 (Claims)
	private Claims extractAllClaims(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignInkey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	private Key getSignInkey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}

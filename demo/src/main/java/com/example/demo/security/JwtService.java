package com.example.demo.security;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

@Service
public class JwtService {
	public static final String USERNAME = "username";
	public static final String SECRET_KEY = "11111111111111111111111111111111111111111111111111111111111111";
	public static final int EXPIRE_TIME = 86400000;

	//CREATE TOKEN -> ENCODE TOKEN
	public String generateTokenLogin(String username) {
		String token = null;
		try {
			JWSSigner signer = new MACSigner(generateShareSecret());
			JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
			builder.claim(USERNAME, username);
			builder.expirationTime(generateExpirationDate()); // tao time het han

			JWTClaimsSet claimsSet = builder.build();
			SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet); // kieu ma hoa

			signedJWT.sign(signer); // add thong tin vao

			token = signedJWT.serialize(); // ma hoa token

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return token;
	}

	// GET CLAIMS FORM TOKEN -> DECODE TOKEN
	private JWTClaimsSet getClaimsFromToken(String token) {
		JWTClaimsSet claims = null;
		try {
			SignedJWT signedJWT = SignedJWT.parse(token);
			JWSVerifier verifier = new MACVerifier(generateShareSecret());
			if (signedJWT.verify(verifier)) {
				claims = signedJWT.getJWTClaimsSet();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return claims;
	}

	// CREATE PREFIX TOKEN
	private byte[] generateShareSecret() {
		// Generate 256-bit (32-byte) shared secret
		byte[] sharedSecret = new byte[32];
		sharedSecret = SECRET_KEY.getBytes();
		return sharedSecret;
	}

	// CREATE TIME OUT TOKEN
	private Date generateExpirationDate() {
		return new Date(System.currentTimeMillis() + EXPIRE_TIME);
	}

	// GET DATE FORM TOKEN
	private Date getExpirationDateFromToken(String token) {
		Date expiration = null;
		JWTClaimsSet claims = getClaimsFromToken(token);
		expiration = claims.getExpirationTime();
		return expiration;
	}

	// GET USERNAME FORM TOKEN
	public String getUsernameFromToken(String token) {
		String username = null;
		try {
			JWTClaimsSet claims = getClaimsFromToken(token);
			username = claims.getStringClaim(USERNAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return username;
	}
	
	//CHECK TIME ACTIVE TOKEN || IF TOKEN NOT ACTIVE RETURN TRUE
	private Boolean isTokenExpired(String token) {
		Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	//VALIDATE CHECK TOKEN 
	public Boolean validateTokenLogin(String token) {
		if (token == null || token.trim().length() == 0) {
			return false;
		}
		String username = getUsernameFromToken(token);
		if (username == null || username.isEmpty()) {
			return false;
		}
		if (isTokenExpired(token)) {
			return false;
		}
		return true;
	}

}

package com.example.userservice.config;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.userservice.dto.UsersDto;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserAuthProvider {
	
	@Value("${security.jwt.token.secret-key:secret-key")
	private String secretKey;
	
	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	public String createToken(UsersDto userDto) {
		Date now = new Date();
		Date validity = new Date(now.getTime() + 3_600_000);
		return JWT.create()
				.withIssuer(userDto.getLogin())
				.withIssuedAt(now)
				.withExpiresAt(validity)
				.withClaim("firstName", userDto.getFirstName())
				.withClaim("lastName", userDto.getLastName())
				.sign(Algorithm.HMAC256(secretKey));
	}
	
	public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);

        UsersDto user = new UsersDto(decoded.getSubject(),decoded.getClaim("firstName").asString(),
        		decoded.getClaim("lastName").asString());

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }

//    public Authentication validateTokenStrongly(String token) {
//        Algorithm algorithm = Algorithm.HMAC256(secretKey);
//
//        JWTVerifier verifier = JWT.require(algorithm)
//                .build();
//
//        DecodedJWT decoded = verifier.verify(token);
//
//        UsersDto user = userService.findByLogin(decoded.getSubject());
//
//        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
//    }


}

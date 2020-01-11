package com.demo.krypto.controller;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.krypto.constants.KryptoUriConstants;
import com.demo.krypto.entity.JwtRefreshTokenEntity;
import com.demo.krypto.entity.UsersEntity;
import com.demo.krypto.model.JwtAuthenticationResponse;
import com.demo.krypto.model.LoginRequest;
import com.demo.krypto.model.RefreshTokenRequest;
import com.demo.krypto.repository.JwtRefreshTokenRepository;
import com.demo.krypto.repository.UsersRepository;
import com.demo.krypto.securityconfig.JwtTokenProvider;
import com.demo.krypto.securityconfig.UserPrincipal;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UsersRepository userRepository;

	
	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private JwtRefreshTokenRepository jwtRefreshTokenRepository;

	@Value("${app.jwtExpirationInMs}")
	private long jwtExpirationInMs;

	String encryptKey = "";

	String privateKey = "";

	@PostMapping(KryptoUriConstants.LOGIN_TOKEN)
	public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		String accessToken = tokenProvider.generateToken(userPrincipal);
		String refreshToken = tokenProvider.generateRefreshToken();
		saveRefreshToken(userPrincipal, refreshToken);
		return ResponseEntity.ok(new JwtAuthenticationResponse(accessToken, refreshToken, jwtExpirationInMs));
	}

	@PostMapping(KryptoUriConstants.REFRESH_TOKEN_URI)
	public ResponseEntity<?> refreshAccessToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest)
			throws Exception {
		return jwtRefreshTokenRepository.findById(refreshTokenRequest.getRefreshToken()).map(jwtRefreshToken -> {
			UsersEntity user = jwtRefreshToken.getUser();
			String accessToken = tokenProvider.generateToken(UserPrincipal.create(user));
			return ResponseEntity
					.ok(new JwtAuthenticationResponse(accessToken, jwtRefreshToken.getToken(), jwtExpirationInMs));
		}).orElseThrow(() -> new Exception("Invalid Refresh Token"));
	}

	private void saveRefreshToken(UserPrincipal userPrincipal, String refreshToken) {
		// Persist Refresh Token
		JwtRefreshTokenEntity jwtRefreshToken = new JwtRefreshTokenEntity(refreshToken);
		Optional<UsersEntity> user=userRepository.findById(userPrincipal.getId());
		if(user.isPresent()){
		jwtRefreshToken.setUser(user.get());
		Instant expirationDateTime = Instant.now().plus(360, ChronoUnit.DAYS);
		jwtRefreshToken.setExpirationDateTime(expirationDateTime);
		jwtRefreshTokenRepository.save(jwtRefreshToken);
		}
	}

}

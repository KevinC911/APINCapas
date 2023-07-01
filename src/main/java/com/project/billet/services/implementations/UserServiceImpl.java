package com.project.billet.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.billet.models.dtos.UserDataDTO;
import com.project.billet.models.entities.Token;
import com.project.billet.models.entities.User;
import com.project.billet.repositories.TokenRepository;
import com.project.billet.repositories.UserRepository;
import com.project.billet.services.UserService;
import com.project.billet.utils.JWTTools;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTTools jwtTools;
	
	@Autowired
	private TokenRepository tokenRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void register(UserDataDTO registerInfo) throws Exception {
		User newUser = new User();
		
		newUser.setEmail(registerInfo.getEmail());
		newUser.setUsername(registerInfo.getUsername());
		newUser.setPassword(
				passwordEncoder.encode(registerInfo.getPassword()));
		
		userRepository.save(newUser);
		
	}
	
	@Override
	public Boolean comparePassword(String toCompare, String current) {
		return passwordEncoder.matches(toCompare, current);
	}

	
	@Override
	public User getUserByIdentifier(String identifier) {
		User tempUser =  null;
		List<User> userSearch = userRepository.findAll();
		if(userSearch.stream().anyMatch(user -> user.getEmail().equals(identifier))) {
			tempUser = userSearch.stream()
					.filter(user -> user.getEmail().equals(identifier))
					.findAny()
					.orElse(null);
		}
		else {
			tempUser = userSearch.stream()
			.filter(user -> user.getUsername().equals(identifier))
			.findAny()
			.orElse(null);
		}
		return tempUser;
	}


	@Override
	@Transactional(rollbackOn = Exception.class)
	public Token registerToken(User user) throws Exception {
		cleanTokens(user);
		
		String tokenString = jwtTools.generateToken(user);
		Token token = new Token(tokenString, user);
		
		tokenRepository.save(token);
		
		return token;
	}

	@Override
	public Boolean isTokenValid(User user, String token) {
		try {
			cleanTokens(user);
			List<Token> tokens = tokenRepository.findByUserAndActive(user, true);
			
			tokens.stream()
				.filter(tk -> tk.getContent().equals(token))
				.findAny()
				.orElseThrow(() -> new Exception());
			
			return true;
		} catch (Exception e) {
			return false;
		}		
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void cleanTokens(User user) throws Exception {
		List<Token> tokens = tokenRepository.findByUserAndActive(user, true);
		
		tokens.forEach(token -> {
			if(!jwtTools.verifyToken(token.getContent())) {
				token.setActive(false);
				tokenRepository.save(token);
			}
		});
		
	}
	
	@Override
	public User findUserAuthenticated() {
		String username = SecurityContextHolder
			.getContext()
			.getAuthentication()
			.getName();
		
		return userRepository.findOneByUsernameOrEmail(username, username);
	}

}

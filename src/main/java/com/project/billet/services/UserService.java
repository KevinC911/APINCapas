package com.project.billet.services;

import com.project.billet.models.dtos.UserDataDTO;
import com.project.billet.models.entities.Token;
import com.project.billet.models.entities.User;

public interface UserService {
	void register(UserDataDTO registerInfo) throws Exception;
	User getUserByIdentifier(String identifier);
	Token registerToken(User user) throws Exception;
	Boolean comparePassword(String toCompare, String current);
	Boolean isTokenValid(User user, String token);
	void cleanTokens(User user) throws Exception;
	User findUserAuthenticated();
}

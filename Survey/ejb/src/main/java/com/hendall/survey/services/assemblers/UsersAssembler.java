package com.hendall.survey.services.assemblers;

import com.hendall.survey.services.datamodel.UserSession;
import com.hendall.survey.services.entities.Users;

public class UsersAssembler {

	public static void assembleUserSession(Users user, UserSession userSession) {
		if (user == null || userSession == null) {
			return;
		}
		userSession.setFirstName(user.getFirstName());
		userSession.setLastName(user.getLastName());
		userSession.setUserKey(user.getUserKey());
		userSession.setUserName(user.getUserId());
		userSession.setRole(user.getRole());
		userSession.setEmail(user.getEmail()); 
		userSession.setState(user.getState());
	}
}

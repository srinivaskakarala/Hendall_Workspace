package com.hendall.surveyrest.assemblers;

import org.apache.commons.collections4.CollectionUtils;

import com.hendall.surveyrest.datamodel.UserData;
import com.hendall.surveyrest.entities.Users;

public class UserDataAssembler {
	public static void assembleUserDataVo(UserData userData, Users user) {
		if (user == null || userData == null) {
			return;
		}
		userData.setEmail(user.getEmail());
		userData.setFirstName(user.getFirstName());
		userData.setLastName(user.getLastName());
		userData.setRole(user.getRole());
		userData.setState(user.getState());
		userData.setUserKey(user.getUserKey());
		userData.setUserId(user.getUserId());
		userData.setPassword(user.getPassword());
		userData.setHasSurveys(CollectionUtils.isNotEmpty(user.getUserSurveyAccesses())?true:false);
	}
	public static void assembleUserEntity(UserData userData, Users user) {
		if (user == null || userData == null) {
			return;
		}
		user.setUserKey(userData.getUserKey());
		user.setFirstName(userData.getFirstName());
		user.setLastName(userData.getLastName());
		user.setRole(userData.getRole());
		user.setState(userData.getState());
		user.setPassword(userData.getPassword());
		user.setUserId(userData.getUserId());
		user.setEmail(userData.getEmail());
		user.setRole(0);
		
	}
}

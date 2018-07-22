package com.store.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.store.model.User;

public class SessionUtil {

	private static Map<String,User> se = new HashMap<String,User>();

	private static SessionUtil instace;

	public static SessionUtil getInstance() {
		if (instace == null) {
			instace = new SessionUtil();
		}
		return instace;

	}

	
	public User getSession(String id) {
		return se.get(id);
	}

	public void putSession(String id,User user) {
		 se.put(id, user);
	}

}

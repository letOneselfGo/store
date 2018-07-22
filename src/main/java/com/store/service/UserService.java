package com.store.service;

import org.springframework.web.servlet.ModelAndView;

import com.store.model.User;

public interface UserService {

	


	User login(String username, String password);

}

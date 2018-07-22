package com.store.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.store.dao.ProductDao;
import com.store.dao.UserDao;
import com.store.model.User;
import com.store.service.UserService;
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("username", username.trim());
		m.put("password", password.trim());
		
		User byUsernameAndPwd = userDao.getByUsernameAndPwd(m);
		return byUsernameAndPwd;
	}

}

package com.store.dao;

import java.util.Map;

import com.store.model.User;

public interface UserDao {



	User getByUsernameAndPwd(Map<String, Object> m);






}

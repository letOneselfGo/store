package com.store.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.store.constant.Constants;
import com.store.dao.ProductDao;
import com.store.dao.UserDao;
import com.store.model.User;
import com.store.service.UserService;
import com.store.util.SendMail;
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
  /**
   * 用户登录
   */
	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("username", username.trim());
		m.put("password", password.trim());
		
		User byUsernameAndPwd = userDao.getByUsernameAndPwd(m);
		return byUsernameAndPwd;
	}
	  /**
	    * 用户注册
	   * @throws Exception 
	   */
		@Override
	public void regist(User user ){
        userDao.save(user);
        try {
			SendMail mail = new SendMail();
		    String code = user.getCode();
			String emailMsg ="恭喜"+user.getName()+"成为我们商城的一�?<a href='http://localhost:8080/store_project/user/active?code="+code+"'>点此�?�?</a>";
			mail.sendMail(user.getEmail(), emailMsg);
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("邮件发�?�错�?");
		}

	}
@Override
	public User active( String code) {
		User user =   userDao.getByCode(code);
		
		//通过�?活码查询用户
		if(user == null) {
			return null;
		}
		
		//获取到用户后进行修改
		
		user.setState(Constants.USER_IS_ACTIVE);
		user.setCode(null);
		
		userDao.update(user);
		return null;
	}

}

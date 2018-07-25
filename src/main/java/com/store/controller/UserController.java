package com.store.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.store.constant.Constants;
import com.store.model.User;
import com.store.service.UserService;
import com.store.util.SessionUtil;
import com.store.util.UUIDUtils;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
    HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;
	
	@Autowired
	HttpSession httpSession;
	
	@RequestMapping("active")
	/**
	 * 用户�?�?
	 */
	 public ModelAndView active() {
			 ModelAndView mv = new ModelAndView();
		 String code =request.getParameter("code");
		 User user = userService.active(code);
		 if(user == null) {
			  mv.addObject("msg", "�?活失败请重新�?活或者注册！");
			  mv.setViewName("jsp/msg");
		 }
		 mv.addObject("msg", "恭喜你激活成�?");
		return mv;
	}
	@RequestMapping("regist")
	/**
	 * 用户注册
	 */
	public ModelAndView regist() {
		   ModelAndView mv = new ModelAndView();
		 try {
			User user = new User();
			 BeanUtils.populate(user, request.getParameterMap());
			 user.setUid(UUIDUtils.getId());
			 user.setCode(UUIDUtils.getCode());
			 user.setState(Constants.USER_IS_NOT_ACTIVE);
			 userService.regist(user);
			 mv.addObject("msg", "恭喜你注册成�?");
			 mv.setViewName("jsp/msg");
			 
		} catch (Exception e) {
			e.printStackTrace();
			 mv.addObject("msg", "用户注册异常");
			 mv.setViewName("jsp/msg");
			 return mv;
		}
	 
		
			return mv;
	}
	/**
	 * 用户�?�?
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/logout")
	public String logout() throws IOException{
		 request.getSession().invalidate();
			response.sendRedirect(request.getContextPath()+"/index/index");
		return null;
	}
	  /*
	   * 用户登陆
	   */
	  @RequestMapping("/login")
   public ModelAndView login() throws Exception {
	  ModelAndView mv  = new ModelAndView();
          try {
			 String username = request.getParameter("username");
			 String password =  request.getParameter("password");
			 String webCode = request.getParameter("piccode").toLowerCase();
			 String tcode = ((String) request.getSession().getAttribute("piccode")).toLowerCase();
			 User user = userService.login(username,password);
			 if(user == null ){
				 mv.addObject("msg", "用户名密码错�?");
				  mv.setViewName("jsp/login");
				  return mv;
			 }
			 
			 if(user.getState() != Constants.USER_IS_ACTIVE) {
				 mv.addObject("msg", "用户未激�?");
				 mv.setViewName("jsp/msg");
				 return mv;
				 
			 }
			 
			 if(!webCode.equals(tcode )) {
				 mv.addObject("msg", "驗證碼错�?");
				  mv.setViewName("jsp/login");
				  return mv;
			 }
			 httpSession.setAttribute("user", user);
			 SessionUtil.getInstance().putSession(httpSession.getId(),user);
			 
			response.sendRedirect(request.getContextPath()+"/index/index");
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("msg", "用户登录异常�?");
			 mv.setViewName("jsp/msg");
			return mv;
			
		}

	     return null;
	
  }
  /**
   * 跳转登录
   * @return
   */
  @RequestMapping("/loginUI")
   public String loginUI() {
	  return "/jsp/login";
  }
  
  @RequestMapping("registUI")
   public String registUI() {
	  return "/jsp/register";
  }
  
   /**
    * 验证码生�?
    */
  @RequestMapping(value = "/verifyCode", method = RequestMethod.GET)
  @ResponseBody
  public void VerifyCode() {

      // 创建�?个宽100,�?50,且不带�?�明色的image对象 100 50
      BufferedImage bi = new BufferedImage(100, 30, BufferedImage.TYPE_INT_RGB);
      Graphics g = bi.getGraphics();
      //RGB色彩
      Color c = new Color(200, 150, 255);
      // 框中的背景色
      g.setColor(c);
      // 颜色填充像素
      g.fillRect(0, 0, 100, 30);

      // 定义验证码字符数�?
      char[] ch = "ABCDEFGHIJKLMNPQRSTUVWXYZ0123456798".toCharArray();
      Random r = new Random();
      int len = ch.length;
      int index;
      StringBuffer sb = new StringBuffer();
      // 取出四个数字
      for (int i = 0; i < 4; i++) {
          // 循环四次随机取长度定义为索引
          index = r.nextInt(len);
          g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));
          Font font = new Font("Times New Roman", Font.ITALIC, 18);
          g.setFont(font);
          g.drawString(ch[index] + "", (i * 18) + 10,20);
          sb.append(ch[index]);
      }
      System.out.println( sb.toString());
      //放入Session�?
      request.getSession().setAttribute("piccode", sb.toString());
      try {
          ImageIO.write(bi, "JPG", response.getOutputStream());
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
} 

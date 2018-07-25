package com.store.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.store.constant.Constants;
import com.store.model.Cart;
import com.store.model.CartItem;
import com.store.model.Order;
import com.store.model.OrderItem;
import com.store.model.User;
import com.store.service.OrderService;
import com.store.util.UUIDUtils;


@Controller("/order")
public class OrderController {
	@Autowired
	OrderService orderservice;
	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpServletResponse response;
	
	
	@Autowired
	HttpSession httpSession;
	
	@RequestMapping("/save")
	public ModelAndView save() {
		ModelAndView mv = new ModelAndView();
		User user =(User) request.getSession().getAttribute("user");
		if(user == null) {
			mv.addObject("msg", "请先登录");
			mv.setViewName("jsp/msg");
		}
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		Order order = new Order();
		order.setOid(UUIDUtils.getId());
		order.setOrdertime();
		Date date = new Date();
		System.out.println(date.getTime());
		order.setTotal(cart.getTotal());
		order.setState(Constants.USER_IS_NOT_ACTIVE);
		order.setUser(user);
		for (CartItem ci : cart.getCartItems()) {
			OrderItem oi = new OrderItem();
			oi.setItemid(UUIDUtils.getId());
			oi.setCount(ci.getCount());
			oi.setSubtotal(ci.getSubtotal());
			oi.setProduct(ci.getProduct());
			oi.setOrder(order);
			order.getItems().add(oi);
		}
		orderservice.save(order);
		mv.addObject("bean", order);
		mv.setViewName("jsp/order_info");
		return null;
	}	

}

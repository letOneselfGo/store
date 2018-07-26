	package com.store.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.store.constant.Constants;
import com.store.model.Cart;
import com.store.model.CartItem;
import com.store.model.Order;
import com.store.model.OrderItem;
import com.store.model.PageBean;
import com.store.model.Product;
import com.store.model.User;
import com.store.service.OrderService;
import com.store.util.PaymentUtil;
import com.store.util.UUIDUtils;


@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	OrderService orderservice;
	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpServletResponse response;
	
	
	@Autowired
	HttpSession httpSession;
	@RequestMapping("/findMyOrdersByPage")
	public ModelAndView findMyOrdersByPage(@RequestParam(required=true,defaultValue="1") Integer page) {
		ModelAndView mv = new ModelAndView();
		
		Map<String, Object> m = new HashMap<String, Object>();
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			mv.addObject("msg", "请先登录");
			mv.setViewName("jsp/msg");
			return mv;
		}
		String uid = user.getUid();
		m.put("uid", uid);
		PageHelper.startPage(page, 3);
		List<Order> bean = orderservice.findMyOrdersByPage(m);
		PageInfo<Order> pageInfo = new PageInfo<Order>(bean);
		for(Order order : bean) {
			List<Map<String, Object>> maplist = orderservice.getList(order.getOid());
			for (Map<String, Object> map : maplist) {
				// 创建orderitem
				OrderItem oi = new OrderItem();
				// 封装
				try {
					BeanUtils.populate(oi, map);
					// 手动封装porduct
					Product product = new Product();
					BeanUtils.populate(product, map);

					oi.setProduct(product);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					mv.addObject("msg", "查询订单列表异常");
					mv.setViewName("jsp/msg");
					return mv;
				}

				// 讲orderitem加入到订单的items中
				order.getItems().add(oi);

			
			}
		}
		
			mv.addObject("pb", bean);
			mv.addObject("page",pageInfo);
			mv.setViewName("jsp/order_list");
			return mv;
	}
	
	/* 
	 * 保存订单
	 */
	@RequestMapping("/save")
	public ModelAndView save() {
		ModelAndView mv = new ModelAndView();
		User user =(User) request.getSession().getAttribute("user");
		if(user == null) {
			mv.addObject("msg", "请先登录");
			mv.setViewName("jsp/msg");
			return mv;
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
		return mv;
	}
	
	/**
	 * 订单支付
	 * @return
	 */
	@RequestMapping("/pay")
	public ModelAndView pay () {
		  ModelAndView mv = new ModelAndView();
		 try {
				//1.获取收货人信息    oid  银行
				String oid21 = request.getParameter("oid");

				//接受参数
						String address=request.getParameter("address");
						String name=request.getParameter("name");
						String telephone=request.getParameter("telephone");
						String oid=request.getParameter("oid");
						
						
						//通过id获取order
						Order order = orderservice.getById(oid);
						List<Map<String, Object>> maplist = orderservice.getList(oid);
						for (Map<String, Object> map : maplist) {
							// 创建orderitem
							OrderItem oi = new OrderItem();
							// 封装
							BeanUtils.populate(oi, map);
							// 手动封装porduct
							Product product = new Product();
							BeanUtils.populate(product, map);

							oi.setProduct(product);
                        
							// 讲orderitem加入到订单的items中
							order.getItems().add(oi);
						}
						order.setAddress(address);
						order.setName(name);
						order.setTelephone(telephone);
						
						//更新order
						orderservice.update(order);
						

						// 组织发送支付公司需要哪些数据
						String pd_FrpId = request.getParameter("pd_FrpId");
						String p0_Cmd = "Buy";
						String p1_MerId = ResourceBundle.getBundle("merchantInfo").getString("p1_MerId");
						String p2_Order = oid;
						String p3_Amt = "0.01";
						String p4_Cur = "CNY";
						String p5_Pid = "";
						String p6_Pcat = "";
						String p7_Pdesc = "";
						// 支付成功回调地址 ---- 第三方支付公司会访问、用户访问
						// 第三方支付可以访问网址
						String p8_Url = ResourceBundle.getBundle("merchantInfo").getString("responseURL");
						String p9_SAF = "";
						String pa_MP = "";
						String pr_NeedResponse = "1";
						// 加密hmac 需要密钥
						String keyValue = ResourceBundle.getBundle("merchantInfo").getString("keyValue");
						String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
								p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
								pd_FrpId, pr_NeedResponse, keyValue);
					
						
						//发送给第三方
						StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
						sb.append("p0_Cmd=").append(p0_Cmd).append("&");
						sb.append("p1_MerId=").append(p1_MerId).append("&");
						sb.append("p2_Order=").append(p2_Order).append("&");
						sb.append("p3_Amt=").append(p3_Amt).append("&");
						sb.append("p4_Cur=").append(p4_Cur).append("&");
						sb.append("p5_Pid=").append(p5_Pid).append("&");
						sb.append("p6_Pcat=").append(p6_Pcat).append("&");
						sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
						sb.append("p8_Url=").append(p8_Url).append("&");
						sb.append("p9_SAF=").append(p9_SAF).append("&");
						sb.append("pa_MP=").append(pa_MP).append("&");
						sb.append("pd_FrpId=").append(pd_FrpId).append("&");
						sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
						sb.append("hmac=").append(hmac);
						
						response.sendRedirect(sb.toString());
			} catch (Exception e) {
				
				e.printStackTrace();
				mv.addObject("msg", "支付异常");
				mv.setViewName("jsp/msg");
				return mv;
			}
					
					return null;
	}
   

}

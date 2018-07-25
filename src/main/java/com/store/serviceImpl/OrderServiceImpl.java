package com.store.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;

import com.store.dao.OrderDao;
import com.store.model.Order;
import com.store.service.OrderService;

@Service
public class OrderServiceImpl  implements OrderService{
    @Resource
    OrderDao orderdao;

	@Override
	@Transactional
	public void save(Order order) {
		orderdao.save(order);
	}
}

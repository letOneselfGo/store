package com.store.service;

import java.util.List;
import java.util.Map;

import com.store.model.Order;

public interface OrderService {

	void save(Order order);

	void update(Order order);

	Order getById(String oid);

	List<Map<String, Object>> getList(String oid);

	List<Order> findMyOrdersByPage(Map<String, Object> m);

	List<String> getByuid(String uid);

	List<Order> findAllByState(String state);





}

package com.store.dao;

import java.util.List;
import java.util.Map;

import com.store.model.Order;
import com.store.model.OrderItem;
import com.store.model.PageBean;

public interface OrderDao {

	void saveItem(OrderItem oi);

	void save(Order order);

	void update(Order order);

	Order getById(String oid);

	List<Map<String, Object>> getList(String oid);

	List<Order> findMyOrdersByPage(Map<String, Object> m);

	List<String> getByuid(String uid);



}

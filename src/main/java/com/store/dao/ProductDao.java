package com.store.dao;

import java.util.List;
import java.util.Map;

import com.store.model.PageBean;
import com.store.model.Product;

public interface ProductDao {

	List<Product> findHot(Map<String,Object> m);

	List<Product> findNew(Map<String,Object> m);

	

	int getTotalRecord(String cid);

	List<Product> findByPage(Map<String, Object> m);

	Product getById(String pid);

	List<Product> selectByList(Map<String, Object> m);

	Integer findCid(String pid);

}

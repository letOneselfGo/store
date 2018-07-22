package com.store.dao;

import java.util.List;
import java.util.Map;

import com.store.model.Product;

public interface ProductDao {

	List<Product> findHot(Map<String,Object> m);

	List<Product> findNew(Map<String,Object> m);

}

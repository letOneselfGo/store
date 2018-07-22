package com.store.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.store.dao.ProductDao;
import com.store.model.Product;
import com.store.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductDao ProDao;
    /**
     * 热门商品查询
     */
	@Override
	public List<Product> findHot() {
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("pflag",0);
		m.put("is_hot",1);
		return ProDao.findHot( m);
	}
	/**
	 * 最新商品查询
	 */

	@Override
	public List<Product> findNew() {
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("pflag", 0);
		return ProDao.findNew(m);
	}

}

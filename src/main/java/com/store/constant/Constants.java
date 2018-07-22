package com.store.constant;
/**
 * 常量存储
 * @author 林立阳
 *
 */
public interface Constants {
	int USER_IS_NOT_ACTIVE = 0;

	/**
	 * �û�����
	 */
	int USER_IS_ACTIVE = 1;

	/**
	 * 记住用户名
	 */
	String SAVA_NAME = "ok";

	/***
	 * 存储分类列表的Key
	 */

	String STORE_CATEGORY_LIST = "STORE_CATEGORY_LIST";

	/**
	 * redis的服务器地址
	 * 
	 */
	String REDIS_HOST = "192.168.17.136";
	/**
	 * redis的端口号
	 */

	int REDIS_PORT = 6379;

	/**
	 * 热门商品
	 */
	int PRODUCT_IS_HOT = 1;

	/**
	 * 没有下架
	 */
	int PRODUCT_IS_UP = 0;

	/**
	 * 已经下架
	 */

	int PRODUCT_IS_DOWN = 1;
	  /**
	   * 订单状态 :未付款
	   */

	int ORDER_UNPAY =0;
	  /**
	   * 订单状态 ：已付款
	   */
	int ORDER_PAY   =1;
	  /**
	   * 订单状态 ： 已发货
	   */
	int ORDER_TRANS =2;
	  /**
	   * 订单状态 ： 已完成
	   */
	int ORDER_COMPLETE=3;
}

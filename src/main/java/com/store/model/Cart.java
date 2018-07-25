package com.store.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * g购物车
 * 
 * @author Administrator
 *
 */
public class Cart {
	private Map<String, CartItem> itemMap = new HashMap<String, CartItem>();
	private Double total = 0.0;

	/**
	 * 获取所有的购物项
	 * 
	 * @return
	 */
	public Collection<CartItem> getCartItems() {
		return itemMap.values();
	}

	public Map<String, CartItem> getItemMap() {
		return itemMap;
	}

	public void setItemMap(Map<String, CartItem> itemMap) {
		this.itemMap = itemMap;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	/**
	 * 添加到购物车
	 * 
	 * @param item
	 */
	public void add2cart(CartItem item) {
		// 获取商品的id
		String pid = item.getProduct().getPid();

		// 判断购物车中是否有
		if (itemMap.containsKey(pid)) {
			// 有 修改数量 = 原来数量+新加的数量
			// 原有的购物项
			CartItem oItem = itemMap.get(pid);

			oItem.setCount(oItem.getCount() + item.getCount());
		} else {
			// 无
			itemMap.put(pid, item);
		}

		// 修改总金额
		total += item.getSubtotal();
	}

	/**
	 * 移除单个商品
	 * 
	 * @param pid
	 */
	public void removeFromCart(String pid) {
		// 1.从购物车（Map）中移除 购物项
		CartItem item = itemMap.remove(pid);
		/// .修改总金额
		total -= item.getSubtotal();
	}

	/**
	 * 清空购物车
	 */
	public void clearCart() {
		// 1.清空map
		itemMap.clear();
		// 2.修改总金额
		total = 0.0;
	}
}

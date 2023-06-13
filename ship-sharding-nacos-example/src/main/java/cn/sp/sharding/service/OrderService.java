package cn.sp.sharding.service;

import cn.sp.sharding.bean.Order;

/**
 * @Author: Ship
 * @Description:
 * @Date: Created in 2023/6/8
 */
public interface OrderService {
    /**
     * 查询订单
     * @param orderCode
     * @return
     */
    Order queryOrder(String orderCode);
}

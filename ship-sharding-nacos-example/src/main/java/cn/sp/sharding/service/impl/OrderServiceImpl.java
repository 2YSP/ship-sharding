package cn.sp.sharding.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.sp.sharding.bean.Order;
import cn.sp.sharding.dao.OrderMapper;
import cn.sp.sharding.service.OrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Ship
 * @Description:
 * @Date: Created in 2023/6/8
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;


    @Override
    public Order queryOrder(String orderCode) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Order::getOrderCode, orderCode);
        List<Order> orders = orderMapper.selectList(wrapper);
        return CollectionUtil.isEmpty(orders) ? null : orders.get(0);
    }
}

package cn.sp.sharding.service.impl;

import cn.sp.sharding.dao.OrderMapper;
import cn.sp.sharding.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}

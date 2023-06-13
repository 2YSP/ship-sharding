package cn.sp.sharding.controller;

import cn.hutool.json.JSONUtil;
import cn.sp.sharding.bean.Order;
import cn.sp.sharding.service.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Ship
 * @Description:
 * @Date: Created in 2023/6/8
 */
@RequestMapping("/order")
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @Value("${ship.sharding.jdbc.datasource.names}")
    private String names;


    @GetMapping("/query")
    public String queryOrder(@RequestParam("orderCode")String orderCode){
        System.out.println(names);
        Order order = orderService.queryOrder(orderCode);
        return JSONUtil.toJsonStr(order);
    }
}

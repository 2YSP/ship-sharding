package cn.sp.sharding;

import cn.hutool.json.JSONUtil;
import cn.sp.sharding.bean.Order;
import cn.sp.sharding.dao.OrderMapper;
import com.alibaba.druid.support.json.JSONUtils;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class ShipShardingExampleApplicationTests {

    @Resource
    private OrderMapper orderMapper;

    @Test
    public void testInsert() {
        Order order = new Order();
        order.setOrderCode("OC001");
        order.setCreateTime(System.currentTimeMillis());
        orderMapper.insert(order);
    }


    @Test
    public void testQuery(){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Order::getOrderCode,"OC001");
        List<Order> orders = orderMapper.selectList(wrapper);
        System.out.println(JSONUtil.toJsonStr(orders));
    }

}

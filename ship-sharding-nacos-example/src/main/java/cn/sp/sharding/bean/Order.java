package cn.sp.sharding.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: Ship
 * @Description:
 * @Date: Created in 2023/6/8
 */
@TableName("t_order")
@Data
public class Order {

    @TableId
    private Long id;

    @TableField("order_code")
    private String orderCode;

    @TableField("create_time")
    private Long createTime;


}

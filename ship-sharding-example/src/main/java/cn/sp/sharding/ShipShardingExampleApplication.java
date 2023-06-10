package cn.sp.sharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("cn.sp.sharding.dao")
@SpringBootApplication
public class ShipShardingExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShipShardingExampleApplication.class, args);
    }

}

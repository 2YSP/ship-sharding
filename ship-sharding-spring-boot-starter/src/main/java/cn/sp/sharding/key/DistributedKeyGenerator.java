package cn.sp.sharding.key;

import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;

import java.util.Properties;

/**
 * @Author: Ship
 * @Description: 分布式id生成器，雪花算法实现
 * @Date: Created in 2023/6/8
 */
public class DistributedKeyGenerator implements ShardingKeyGenerator {

    @Override
    public Comparable<?> generateKey() {
        return KeyGeneratorFactory.getInstance().generateKey();
    }

    @Override
    public String getType() {
        return "DISTRIBUTED";
    }

    @Override
    public Properties getProperties() {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}

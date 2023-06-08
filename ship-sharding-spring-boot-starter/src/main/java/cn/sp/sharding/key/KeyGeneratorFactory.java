package cn.sp.sharding.key;

import cn.sp.sharding.util.IpUtil;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;

import java.util.Properties;

/**
 * @author Ship
 * @version 1.0.0
 * @description:
 */
public final class KeyGeneratorFactory {
    /**
     * 使用shardingsphere提供的雪花算法 id生成器
     */
    private static volatile SnowflakeShardingKeyGenerator keyGenerator = null;

    private KeyGeneratorFactory() {

    }

    /**
     * 单例模式
     *
     * @return
     */
    public static SnowflakeShardingKeyGenerator getInstance() {
        if (keyGenerator == null) {
            synchronized (KeyGeneratorFactory.class) {
                if (keyGenerator == null) {
                    // 用ip地址当作机器id，机器范围0-1024
                    Long workerId = Long.valueOf(IpUtil.getLocalIpAddress().replace(".", "")) % 1024;
                    keyGenerator = new SnowflakeShardingKeyGenerator();
                    Properties properties = new Properties();
                    properties.setProperty("worker.id", workerId.toString());
                    keyGenerator.setProperties(properties);
                }
            }
        }
        return keyGenerator;
    }


}

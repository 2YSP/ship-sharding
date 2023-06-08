package cn.sp.sharding.properties;

import cn.sp.sharding.constant.CommonConstants;
import org.apache.shardingsphere.core.yaml.config.sharding.YamlShardingRuleConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Ship
 * @version 1.0.0
 * @description:
 * @date 2023/06/06
 */
@ConfigurationProperties(prefix = CommonConstants.COMMON_CONFIG_PREFIX + ".config")
public class ShardingRuleConfigurationProperties extends YamlShardingRuleConfiguration {


}

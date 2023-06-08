package cn.sp.sharding.properties;

import cn.sp.sharding.constant.CommonConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

/**
 * @Author: Ship
 * @Description:
 * @Date: Created in 2023/6/6
 */
@ConfigurationProperties(prefix = CommonConstants.COMMON_CONFIG_PREFIX + ".map")
public class ConfigMapConfigurationProperties {

    private Properties props = new Properties();


    public Properties getProps() {
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }
}

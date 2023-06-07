package cn.sp.sharding.config;

import cn.sp.sharding.properties.CommonConstants;
import cn.sp.sharding.properties.ConfigMapConfigurationProperties;
import cn.sp.sharding.properties.ShardingRuleConfigurationProperties;
import cn.sp.sharding.util.DataSourceUtil;
import cn.sp.sharding.util.PropertyUtil;
import com.google.common.collect.Maps;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.core.yaml.swapper.ShardingRuleConfigurationYamlSwapper;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ship
 * @version 1.0.0
 * @description:
 * @date 2023/06/06
 */
@Configuration
@EnableConfigurationProperties(value = {ShardingRuleConfigurationProperties.class, ConfigMapConfigurationProperties.class})
public class ShardingAutoConfig implements EnvironmentAware {


    private Map<String, DataSource> dataSourceMap = new HashMap<>();

    @ConditionalOnMissingBean
    @Bean
    public DataSource shardingDataSource(@Autowired ShardingRuleConfigurationProperties configurationProperties,
                                         @Autowired ConfigMapConfigurationProperties configMapConfigurationProperties) throws SQLException {
        ShardingRuleConfigurationYamlSwapper yamlSwapper = new ShardingRuleConfigurationYamlSwapper();
        ShardingRuleConfiguration shardingRuleConfiguration = yamlSwapper.swap(configurationProperties);
        return ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfiguration, configMapConfigurationProperties.getProps());
    }

    @Override
    public void setEnvironment(Environment environment) {
        setDataSourceMap(environment);
    }

    private void setDataSourceMap(Environment environment) {
        String names = environment.getProperty(CommonConstants.DATA_SOURCE_CONFIG_PREFIX + ".names");
        for (String name : names.split(",")) {
            try {
                String propertiesPrefix = CommonConstants.DATA_SOURCE_CONFIG_PREFIX + "." + name;
                Map<String, Object> dataSourceProps = PropertyUtil.handle(environment, propertiesPrefix, Map.class);
                // 反射创建数据源
                DataSource dataSource = DataSourceUtil.getDataSource(dataSourceProps.get("type").toString(), dataSourceProps);
                dataSourceMap.put(name, dataSource);
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
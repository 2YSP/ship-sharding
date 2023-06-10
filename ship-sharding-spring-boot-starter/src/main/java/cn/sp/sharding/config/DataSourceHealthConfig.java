package cn.sp.sharding.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.actuate.autoconfigure.jdbc.DataSourceHealthContributorAutoConfiguration;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.jdbc.DataSourceHealthIndicator;
import org.springframework.boot.jdbc.metadata.DataSourcePoolMetadataProvider;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @Author: Ship
 * @Description:
 * @Date: Created in 2023/6/7
 */
public class DataSourceHealthConfig extends DataSourceHealthContributorAutoConfiguration {

    private static String validQuery = "SELECT 1";

    public DataSourceHealthConfig(Map<String, DataSource> dataSources, ObjectProvider<DataSourcePoolMetadataProvider> metadataProviders) {
        super(dataSources, metadataProviders);
    }

    @Override
    protected AbstractHealthIndicator createIndicator(DataSource source) {
        DataSourceHealthIndicator healthIndicator = (DataSourceHealthIndicator) super.createIndicator(source);
        healthIndicator.setQuery(validQuery);
        return healthIndicator;
    }
}

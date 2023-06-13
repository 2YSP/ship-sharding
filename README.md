# ship-sharding
This is spring-boot-stater of sharding-jdbc,help you achieve database and table partitioning faster!

## Project Structure

| project name | description |
| --- | --- |
| ship-sharding-spring-boot-starter | core module |
| ship-sharding-example | an example of how to use ship-sharding-spring-boot-starter |
| ship-sharding-nacos-example | an example of how to use ship-sharding-spring-boot-starter,when using nacos as config center |

## Quick Start

1. add this into pom.xml of your project

```xml
        <dependency>
            <groupId>cn.sp</groupId>
            <artifactId>ship-sharding-spring-boot-starter</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
```
2. Add sharding configurations to your local configuration file or nacos.

**for example:**
```yaml
ship:
  sharding:
    jdbc:
      datasource:
        names: ds0
        ds0:
          driver-class-name: com.mysql.cj.jdbc.Driver
          type: com.alibaba.druid.pool.DruidDataSource
          url: jdbc:mysql://127.0.0.1:3306/my_springboot?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false
          username: root
          password: 1234
          initial-size: 5
          minIdle: 5
          maxActive: 20
          maxWait: 60000
          timeBetweenEvictionRunsMillis: 60000
          minEvictableIdleTimeMillis: 300000
          validationQuery: SELECT 1 FROM DUAL
          testWhileIdle: true
          testOnBorrow: false
          testOnReturn: false
          poolPreparedStatements: true
          maxPoolPreparedStatementPerConnectionSize: 20
          useGlobalDataSourceStat: true
          connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=2000;druid.mysql.usePingMethod=false
      config:
        binding-tables: t_order
        tables:
          t_order:
            actual-data-nodes: ds0.t_order_${2022..2023}_${1..4}
            key-generator:
              type: DISTRIBUTED
              column: id
            table-strategy:
              standard:
                sharding-column: create_time
                precise-algorithm-class-name: cn.sp.sharding.algorithm.MyTableShardingAlgorithm
                range-algorithm-class-name: cn.sp.sharding.algorithm.MyTableShardingAlgorithm
```


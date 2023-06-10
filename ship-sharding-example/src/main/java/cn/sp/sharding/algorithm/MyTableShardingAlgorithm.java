package cn.sp.sharding.algorithm;

import cn.sp.sharding.util.DateUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * @Author: Ship
 * @Description:
 * @Date: Created in 2023/6/8
 */
@Slf4j
public class MyTableShardingAlgorithm implements PreciseShardingAlgorithm<Long>, RangeShardingAlgorithm<Long> {

    private static final String TABLE_NAME_PREFIX = "t_order_";

    @Override
    public String doSharding(Collection<String> availableTableNames, PreciseShardingValue<Long> preciseShardingValue) {
        Long createTime = preciseShardingValue.getValue();
        if (createTime == null) {
            throw new ShipShardingException("创建时间不能为空！");
        }
        LocalDate localDate = DateUtils.longToLocalDate(createTime);
        final String year = localDate.getYear() + "";
        Integer quarter = DateUtils.getQuarter(localDate);
        for (String tableName : availableTableNames) {
            String dateStr = tableName.replace(TABLE_NAME_PREFIX, "");
            String[] dateArr = dateStr.split("_");
            if (dateArr[0].equals(year) && dateArr[1].equals(quarter.toString())) {
                return tableName;
            }
        }
        log.error("分表算法对应的表不存在！");
        throw new ShipShardingException("分表算法对应的表不存在！");
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTableNames, RangeShardingValue<Long> rangeShardingValue) {
        //获取查询条件中范围值
        Range<Long> valueRange = rangeShardingValue.getValueRange();
        // 上限值
        Long upperEndpoint = valueRange.upperEndpoint();
        // 下限值
        Long lowerEndpoint = valueRange.lowerEndpoint();

        List<String> tableNames = Lists.newArrayList();
        for (String tableName : availableTableNames) {
            String dateStr = tableName.replace(MyTableShardingAlgorithm.TABLE_NAME_PREFIX, "");
            String[] dateArr = dateStr.split("_");
            String year = dateArr[0];
            String quarter = dateArr[1];
            Long[] minAndMaxTime = DateUtils.getMinAndMaxTime(year, quarter);
            Long minTime = minAndMaxTime[0];
            Long maxTime = minAndMaxTime[1];
            if (valueRange.hasLowerBound() && valueRange.hasUpperBound()) {
                // between and
                if (minTime.compareTo(lowerEndpoint) <= 0 && upperEndpoint.compareTo(maxTime) <= 0) {
                    tableNames.add(tableName);
                }
            } else if (valueRange.hasLowerBound() && !valueRange.hasUpperBound()) {
                if (maxTime.compareTo(lowerEndpoint) > 0) {
                    tableNames.add(tableName);
                }
            } else {
                if (upperEndpoint.compareTo(minTime) > 0) {
                    tableNames.add(tableName);
                }
            }
        }
        if (tableNames.size() == 0) {
            log.error("分表算法对应的表不存在！");
            throw new ShipShardingException("分表算法对应的表不存在！");
        }
        return tableNames;
    }
}

package com.zz.shardingjdbc.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * 精确分片算法实现
 */
@Slf4j
public class TimeShardingTableAlgorithm implements PreciseShardingAlgorithm<Long> {

    /**
     * 自定义分表算法
     * @param collection
     * @param preciseShardingValue
     * @return
     */
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        // table name
        return "";
    }





}

package com.zz.shardingjdbc.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;

/**
 * 范围分片算法
 */
@Slf4j
public class TimeRangeShardingAlgorithm implements RangeShardingAlgorithm<Long> {



    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {
        Collection<String> result = null;
        // table name
        return result;
    }



}

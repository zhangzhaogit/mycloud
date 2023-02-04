package com.zhao.fk;
 /**
 * @Author zhangzhao
 * @Date 2022/9/24 22:11
 */

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.FlatMapOperator;
import org.apache.flink.api.java.operators.UnsortedGrouping;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * @Description:
 * @Author: zhangzhao
 * @Date: 2022-09-24 22:11 
 **/
public class BatchWorkCount {
    public static void main(String[] args) throws Exception {
        // 1. 创建执行环境
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
// 2. 从 文 件 读 取 数 据 按 行 读 取 ( 存 储 的 元 素 就 是 每 行 的 文 本 )
        DataSource<String> lineDataSource = env.readTextFile("flink/input/words.txt");
// 3. 转换数据格式
        FlatMapOperator<String, Tuple2<String, Long>> wordAndOneTuple = lineDataSource.flatMap((String line, Collector<Tuple2<String, Long>> out) -> {
            String[] words = line.split("  ");
            for (String word : words) {
                out.collect(Tuple2.of(word, 1L));
            }
        }).returns(Types.TUPLE(Types.STRING, Types.LONG));

        // 4.按照words进行分组
        UnsortedGrouping<Tuple2<String, Long>> wordAndOneGroup = wordAndOneTuple.groupBy(0);
        // 5.分组内进行聚合
        AggregateOperator<Tuple2<String, Long>> sum = wordAndOneGroup.sum(1);
        // 6.打印结果
        sum.print();
    }
}
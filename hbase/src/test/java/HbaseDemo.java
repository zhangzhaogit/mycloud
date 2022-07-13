/**
 * @Author zhangzhao
 * @Date 2022/6/19 14:52
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * @Description:
 *  DDL:
 *      1.判断表是否存在
 *      2.创建表
 *      3.创建命名空间
 *      4.删除表
 *  DML：
 *      5.插入数据
 *      6.查数据 get
 *      7.查数据 scan
 *      8.删除数据
 *
 * @Author: zhangzhao
 * @Date: 2022-06-19 14:52 
 **/
public class HbaseDemo {

    private static Connection connection = null;
    private static Admin admin = null;
    static {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum","192.168.1.101");
        try {
            connection = ConnectionFactory.createConnection(configuration);
            admin = connection.getAdmin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void close(){
        try {
            admin.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * 判断表是否存在
     */
    public static boolean isTableExist(String tableName) {
        try {
            return admin.tableExists(TableName.valueOf(tableName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 创建表
     * @param tableName
     * @param cfs
     * @return
     */
    public static boolean createTable(String tableName, String... cfs) {
        // 判断表是否存在
        if (isTableExist(tableName)) {
            System.out.println("表已存在");
            return false;
        }
        // 表描述器
        TableDescriptorBuilder builder = TableDescriptorBuilder.newBuilder(TableName.valueOf(tableName));
        for (String cf : cfs) {
            // 列簇
            ColumnFamilyDescriptorBuilder familyDescriptorBuilder = ColumnFamilyDescriptorBuilder.newBuilder(cf.getBytes());
            builder.setColumnFamily(familyDescriptorBuilder.build());
        }
        try {
            admin.createTable(builder.build());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void putData(String tableName, String rowKey, String cf, String cn, String value) {
        try {
            Table table = connection.getTable(TableName.valueOf(tableName));
            Put put = new Put(Bytes.toBytes(rowKey));
            put.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn), Bytes.toBytes(value));
            table.put(put);
            table.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getDate(String tableName, String rowKey, String cf, String cn) {
        try {
            Table table = connection.getTable(TableName.valueOf(tableName));
            Get get = new Get(Bytes.toBytes(rowKey));
//            get.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn));
            Result result = table.get(get);
            for (Cell cell : result.rawCells()) {
                System.out.println("CF：" + Bytes.toString(CellUtil.cloneFamily(cell)));
                System.out.println("CN：" + Bytes.toString(CellUtil.cloneQualifier(cell)));
                System.out.println("VALUE：" + Bytes.toString(CellUtil.cloneValue(cell)));
            }
            table.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void scan(String tableName) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();
        ResultScanner scanner = table.getScanner(scan);
        for (Result result : scanner) {
            for (Cell cell : result.rawCells()) {
                System.out.println("RK：" + Bytes.toString(CellUtil.cloneRow(cell)));
                System.out.println("CF：" + Bytes.toString(CellUtil.cloneFamily(cell)));
                System.out.println("CN：" + Bytes.toString(CellUtil.cloneQualifier(cell)));
                System.out.println("VALUE：" + Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }
        table.close();

    }

    public static void deleteDate(String tableName, String rowKey, String cf, String cn) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        table.delete(delete);
        table.close();

    }

    public static void main(String[] args) throws Exception {
//        boolean stu5 = createTable("stu5","info");
//        System.out.println(isTableExist("stu5"));
//        putData("stu5", "1001", "info", "name", "zhangsan");
        getDate("stu5", "1001", null, null);
        close();


    }
}
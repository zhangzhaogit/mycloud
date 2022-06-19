/**
 * @Author zhangzhao
 * @Date 2022/5/22 12:40
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.net.URI;

/**
 * @Description:
 * @Author: zhangzhao
 * @Date: 2022-05-22 12:40 
 **/
public class HdfsClientTest {

    public static void main(String[] args) throws Exception {
        URI uri = new URI("hdfs://hadoop102:8020");
        Configuration config = new Configuration();
        FileSystem fs = FileSystem.get(uri, config);
        fs.mkdirs(new Path("/xiyou/huaguoshan"));
        fs.close();


    }
}
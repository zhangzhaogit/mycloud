package com.zz;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URI;

/**
 * @Description:
 * @Author: zhangzhao
 * @Date: 2022-05-22 12:40
 **/
@SpringBootTest
public class HdfsClient {
    private static FileSystem fs;

    @Before
    public void init() throws Exception {
        URI uri = new URI("hdfs://hadoop102:8020");
        Configuration config = new Configuration();
        fs = FileSystem.get(uri, config, "root");
    }

    @After
    public void close() throws Exception {
        fs.close();
    }

    @Test
    public void testMkdir() throws Exception {
        fs.mkdirs(new Path("/xiyou/huaguoshan"));
    }

    @Test
    public void testPut() throws IOException {
        fs.copyFromLocalFile(false, true, new Path("C:\\Users\\zhangzhao\\Desktop\\自开发\\hadoop\\datanode.png"), new Path("/xiyou/huaguoshan"));
    }
    @Test
    public void testDownload() throws IOException {
        fs.copyToLocalFile(false,new Path("/xiyou/huaguoshan"),new Path("C:\\Users\\zhangzhao\\Desktop\\自开发\\hadoop\\"),false);
    }

    @Test
    public void testDelete() throws IOException {
        // 删除文件
        fs.delete(new Path("/xiyou/huaguoshan/datanode.png"), false);
    }
}
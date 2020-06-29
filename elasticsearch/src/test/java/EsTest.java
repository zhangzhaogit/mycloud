import cn.hutool.json.JSONUtil;
import com.zhao.elasticsearch.ElasticsearchApplication;
import com.zhao.elasticsearch.module.User;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @Description:
 * @Author: zhangzhao
 * @Date: 2020-06-29 11:23
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticsearchApplication.class)
public class EsTest {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 创建索引
     * @throws Exception
     */
    @Test
    public void createIndex() throws Exception {
        // 1.创建索引
        CreateIndexRequest request = new CreateIndexRequest("zz_index");
        // 2.执行请求
        CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(response);

    }

    /**
     * 判断索引是否存在
     * @throws Exception
     */
    @Test
    public void getIndex() throws Exception {
        GetIndexRequest request = new GetIndexRequest("zz_index");
        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    /**
     * 删除索引
     */
    @Test
    public void deleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("zz_index");
        AcknowledgedResponse delete = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }

    /**
     * 添加文档
     */
    @Test
    public void addDocument() throws IOException {
        User user = new User("zz",22);
        // 创建请求
        IndexRequest request = new IndexRequest("zz_index");
        request.id("4");
//        request.timeout(TimeValue.timeValueSeconds(1));
        request.timeout("10s");
        // 数据放入请求
        request.source(JSONUtil.toJsonStr(user), XContentType.JSON);

        // 发送请求
        IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        System.out.println(indexResponse.toString());

    }

    /**
     * 判断文档是否存在
     */
    @Test
    public void existsDocument() throws IOException {
        GetRequest request = new GetRequest("zz_index", "2");
        request.fetchSourceContext(new FetchSourceContext(false));
        boolean exists = restHighLevelClient.exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    /**
     * 获取文档信息
     */
    @Test
    public void getDocument() throws IOException {
        GetRequest request = new GetRequest("zz_index", "1");
        GetResponse documentFields = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        // 文档内容 {"name":"张钊","age":22}
        System.out.println(documentFields.getSourceAsString());
        // {"_index":"zz_index","_type":"_doc","_id":"1","_version":1,"_seq_no":0,"_primary_term":1,"found":true,"_source":{"name":"张钊","age":22}}
        System.out.println(documentFields);
    }

    /**
     * 更新文档
     */
    @Test
    public void updateDocument() throws IOException {
        UpdateRequest request = new UpdateRequest("zz_index", "1");
        request.timeout("10s");
        User user = new User("张钊", 18);
        request.doc(JSONUtil.toJsonStr(user),XContentType.JSON);
        UpdateResponse update = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        System.out.println(update);
        System.out.println(update.status());
    }

    /**
     * 删除文档
     */
    @Test
    public void deleteDocument() throws IOException {
        DeleteRequest request = new DeleteRequest("zz_index", "1");
        request.timeout("10s");
        DeleteResponse response = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        System.out.println(response);
    }

    /**
     * 批量插入
     */
    @Test
    public void insertList() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");
        ArrayList<User> list = new ArrayList<>();
        list.add(new User("张三",3));
        list.add(new User("张4",4));
        list.add(new User("张5",5));
        for (int i = 0; i < list.size(); i++) {
            bulkRequest.add(new IndexRequest("zz_index")
                    .id(String.valueOf(i+1))
                    .source(JSONUtil.toJsonStr(list.get(i)),XContentType.JSON)
            );
        }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk.hasFailures());
    }

    @Test
    public void search() throws IOException {
        SearchRequest request = new SearchRequest("zz_index");
        // 构建搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 查询条件
        // 精确匹配 QueryBuilders.termQuery，匹配所有 QueryBuilders.matchAllQuery()
//        TermQueryBuilder query = QueryBuilders.termQuery("name", "z");
        // 模糊匹配
        MatchQueryBuilder query = QueryBuilders.matchQuery("name", "张");
        sourceBuilder.query(query);
        request.source(sourceBuilder);
        SearchResponse search = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        System.out.println(search);
    }






}
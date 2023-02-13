import java.util.concurrent.CompletableFuture;

/**
 * @Description: 异步线程异常
 * @Author: zhangzhao
 * @Date: 2023-02-04 16:52 
 **/
public class AsyncThreadException {
    public static void main(String[] args) throws Exception {

        CompletableFuture.runAsync(() -> {
            zz(1);
        });
        Thread.sleep(10000);


    }
    static void zz(int i){
        try {
            int zi = 1 / 0;
        }catch (Exception e){
            System.out.println(e.toString());

        }
    }

}

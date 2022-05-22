/**
 * @Author zhangzhao
 * @Date 2021/8/20 20:17
 */

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: zhangzhao
 * @Date: 2021-08-20 20:17 
 **/
public class BlockThreadPool {

    private ThreadPoolExecutor pool = null;

    public BlockThreadPool(int poolSize) {
        pool = new ThreadPoolExecutor(poolSize, poolSize, 0, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(poolSize),
                new CustomThreadFactory(), new CustomRejectedExecutionHandler());
    }

    /**
     *
     * @author chujiejie
     *
     */
    private class CustomThreadFactory implements ThreadFactory {
        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String threadName = BlockThreadPool.class.getSimpleName() + count.addAndGet(1);
            t.setName(threadName);
            return t;
        }
    }

    /**
     * 改造任务入队方法
     * @author chujiejie
     *
     */
    private class CustomRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                // 核心改造，由blockingqueue的offer改成put阻塞方法
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 包装execte方法
     * @param runnable
     */
    public void execute(Runnable runnable) {
        this.pool.execute(runnable);
    }

    /**
     * 关闭线程池
     */
    public void shutdown() {
        this.pool.shutdown();
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        BlockThreadPool pool = new BlockThreadPool(3);
        for (int i = 1; i < 100; i++) {
            System.out.println("提交第" + i + "个任务");
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getId() + "=====开始");
                        TimeUnit.SECONDS.sleep(10);
                        System.out.println(Thread.currentThread().getId() + "=====结束");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            System.out.println("提交第" + i + "个任务成功");
        }
    }

}
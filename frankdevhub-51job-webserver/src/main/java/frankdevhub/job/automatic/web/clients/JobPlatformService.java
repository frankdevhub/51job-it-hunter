package frankdevhub.job.automatic.web.clients;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.util.Assert;

import java.util.concurrent.*;

/**
 * <p>Title:@ClassName JobPlatformService.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/25 15:00
 * @Version: 1.0
 */

@Slf4j
@SuppressWarnings("all")
public class JobPlatformService {

    private final Integer CPU_CAPBILITY; //CPU性能
    private final ThreadPoolExecutor threadPool; //扫描解析的线程池对象

    public JobPlatformService() {
        this.CPU_CAPBILITY = Runtime.getRuntime().availableProcessors();
        this.threadPool = new ThreadPoolExecutor(2 * CPU_CAPBILITY + 1, Integer.MAX_VALUE, 100, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(), new CustomizableThreadFactory("jd-extract-client"),
                new ThreadPoolExecutor.CallerRunsPolicy()); //解析每一个列表页面的线程对象所在的线程池
    }

    @Data
    private class DefaultDataPatrolThread extends Thread {

        private final ExecutorService cachedThreadPool = Executors.newCachedThreadPool(); //扫描网页的线程池
        private final JobPlatformClient client = new JobPlatformClient(); //客户端业务逻辑对象
        private String url; //页面链接

        public DefaultDataPatrolThread(String url) {
            Assert.notNull(url, "cannot find url");
            this.url = url;
        }

        @Override
        public void run() {
            log.info("[defaultDataPatrolService --> task::thread]thread name = "
                    + Thread.currentThread().getName());
            log.info("default data patrol thread start");
            while (true) {
                try {
                    log.info("current url = " + url);
                    client.restorePageJobSearchResult(url, cachedThreadPool);
                    //依据链接规则获取下一页链接
                    url = PlatformWebClient.getNextResultPage(url);
                    log.info("next url = " + url);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 默认的扫描业务逻辑
     *
     * @param url 页面链接
     * @throws InterruptedException
     */
    public void defaultDataPatrolService(String url) throws InterruptedException {
        log.info("invoke default data patrol service");
        Runnable task = () -> {
            try {
                Thread t = new DefaultDataPatrolThread(url);
                t.start();
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(task);
        thread.setDaemon(true);//设置为守护进程
        threadPool.submit(thread);   //提交任务至线程池
        Thread.sleep(1000L);

    }
}

package frankdevhub.job.automatic.web.clients;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
public class JobPlatformService {

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
                    log.info("@current url = " + url);
                    client.restorePageJobSearchResult(url, cachedThreadPool);
                    url = client.getNextResultPage(url);
                    log.info("@next url = " + url);
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
            log.info("[defaultDataPatrolService --> task]thread name = "
                    + Thread.currentThread().getName());
            Thread t = new DefaultDataPatrolThread(url);
            t.setDaemon(true);
            t.start();
        };
        Thread t = new Thread(task);
        log.info("thread t->name =" + t.getName());
        t.setDaemon(true);
        t.start();
        Thread.sleep(200L);

    }
}

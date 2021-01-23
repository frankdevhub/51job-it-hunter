package frankdevhub.job.automatic.web.clients;

import lombok.extern.slf4j.Slf4j;

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
@SuppressWarnings("all")
public class JobPlatformService {

    private class DefaultDataPatrolThread extends Thread {
        private final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        private final JobPlatformClient client = new JobPlatformClient();

        private String url;

        public DefaultDataPatrolThread(String url) {
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
                    //TODO if next page ?
                    url = client.getNextResultPage(url);
                    log.info("@next url = " + url);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //TODO
    public void defaultDataPatrolService(String url) throws InterruptedException {
        log.info("invoke default data patrol service");
        Runnable task = () -> {
            log.info("[defaultDataPatrolService --> task]thread name = "
                    + Thread.currentThread().getName());
            Thread t = new DefaultDataPatrolThread(url);
            // t.setDaemon(true);
            t.start();
        };

        Thread t = new Thread(task);
        log.info("thread t->name =" + t.getName());
        // t.setDaemon(true);
        t.start();
        Thread.sleep(200L);

    }
}

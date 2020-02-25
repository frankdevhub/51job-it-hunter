package frankdevhub.job.automatic.web.clients;

import frankdevhub.job.automatic.core.data.logging.Logger;
import frankdevhub.job.automatic.core.data.logging.LoggerFactory;

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

public class JobPlatformService {

    private final Logger LOGGER = LoggerFactory.getLogger(JobPlatformService.class);

    private class DefaultDataPatrolThread extends Thread {

        private final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        private final JobPlatformClient client = new JobPlatformClient();

        private String url;

        public DefaultDataPatrolThread(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            System.out.println("thread name = " + Thread.currentThread().getName());
            System.out.println("default data patrol thread start");
            while (true) {
                try {
                    System.out.println("@current url = " + url);
                    client.restorePageJobSearchResult(url, cachedThreadPool);
                    //TODO if next page ?
                    url = client.getNextResultPage(url);
                    System.out.println("@next url = " + url);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //TODO
    public void defaultDataPatrolService(String url) {
        System.out.println("thread name = " + Thread.currentThread().getName());

        LOGGER.begin().info("invoke default data patrol service");
        Runnable task = () -> {
            Thread t = new DefaultDataPatrolThread(url);
            t.setDaemon(true);
            t.start();
        };
        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }
}

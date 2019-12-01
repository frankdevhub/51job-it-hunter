package frankdevhub.job.webauto.utils;

/**
 * <p>Title:ThreadUtils.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @author frankdevhub
 * @date:2019-05-22 19:17
 */

public class ThreadUtils {
    public synchronized static Thread check(String thread) {
        Thread alive = null;
        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
        int noThread = currentGroup.activeCount();

        Thread[] lstThreads = new Thread[noThread];
        currentGroup.enumerate(lstThreads);

        for (int i = 0; i < noThread; i++) {
            String currentThreadName = lstThreads[i].getName();
            System.out.println(currentThreadName);
            if (currentThreadName.equals(thread)) {
                alive = lstThreads[i];
                break;
            }
        }
        return alive;
    }
}

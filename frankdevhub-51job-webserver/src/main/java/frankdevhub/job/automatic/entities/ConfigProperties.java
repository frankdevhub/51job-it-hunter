package frankdevhub.job.automatic.entities;

import java.util.Properties;

/**
 * <p>Title:@ClassName ConfigProperties.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/1 22:00
 * @Version: 1.0
 */
public class ConfigProperties extends Properties {

    public synchronized ConfigProperties setProperty(String key, String value) {
        super.setProperty(key, value);
        return this;
    }

    public synchronized ConfigProperties put(Object key, Object value) {
        super.put(key, value);
        return this;
    }


}
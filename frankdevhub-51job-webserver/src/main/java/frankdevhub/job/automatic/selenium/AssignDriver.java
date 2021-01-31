package frankdevhub.job.automatic.selenium;

import org.openqa.selenium.remote.RemoteWebDriver;

import java.lang.reflect.Field;

/**
 * <p>Title:AssignDriver.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @author frankdevhub
 * @date:2019-04-21 16:04
 */

@SuppressWarnings("all")
public class AssignDriver {

    /**
     * 初始化定位元素的查询对象
     *
     * @param object 加载驱动运行的实体,例网页对象的基类
     * @param driver 浏览器驱动对象
     */
    public static void initQueryObjects(Object object, RemoteWebDriver driver) {
        Class<?> clazz = object.getClass();
        try {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.getType() == Query.class) {
                    field.setAccessible(true);
                    Query queryObject = (Query) field.get(object);
                    if (null != queryObject) {
                        queryObject.usingDriver(driver);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

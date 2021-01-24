package frankdevhub.job.automatic.core.message;

/**
 * <p>Title:MessageMethod.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @author frankdevhub
 * @date:2019-04-20 22:53
 */

@SuppressWarnings("all")
public enum MessageMethod {
    DELETE, EVENT, GET, PATCH, POST, ERROR, PUT;

    private MessageMethod() {
    }
}

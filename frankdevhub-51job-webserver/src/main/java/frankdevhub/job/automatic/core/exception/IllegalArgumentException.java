package frankdevhub.job.automatic.core.exception;

/**
 * <p>Title:@ClassName IllegalArgumentException.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/1/28 23:05
 * @Version: 1.0
 */
@SuppressWarnings("all")
public class IllegalArgumentException extends BusinessException {

    /**
     * 参数非法异常
     *
     * @param message 异常消息
     */
    public IllegalArgumentException(String message) {
        super(message);
    }
}

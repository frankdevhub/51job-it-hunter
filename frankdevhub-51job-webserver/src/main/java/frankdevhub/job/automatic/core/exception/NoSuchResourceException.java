package frankdevhub.job.automatic.core.exception;

@SuppressWarnings("all")
public class NoSuchResourceException extends BusinessException {

    /**
     * 资源不存在异常
     *
     * @param message 异常消息
     */
    public NoSuchResourceException(String message) {
        super(message);
    }
}

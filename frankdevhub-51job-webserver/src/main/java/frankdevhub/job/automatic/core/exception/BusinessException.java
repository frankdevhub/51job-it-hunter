package frankdevhub.job.automatic.core.exception;

@SuppressWarnings("all")
public class BusinessException extends Exception {

    /**
     * 业务逻辑异常
     *
     * @param message 异常消息
     */
    public BusinessException(String message) {
        super(message);
    }
}

package frankdevhub.job.automatic.core.exception;

@SuppressWarnings("all")
public class NoSuchPermissionException extends BusinessException {

    /**
     * 非法权限异常
     *
     * @param message 异常消息
     */
    public NoSuchPermissionException(String message) {
        super(message);
    }
}

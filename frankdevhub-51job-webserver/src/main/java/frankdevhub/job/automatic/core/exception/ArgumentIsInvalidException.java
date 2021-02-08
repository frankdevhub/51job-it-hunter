package frankdevhub.job.automatic.core.exception;

@SuppressWarnings("all")
public class ArgumentIsInvalidException extends BusinessException {
    private static final long serialVersionUID = 1L;

    /**
     * 参数非法异常
     *
     * @param message 异常消息
     */
    public ArgumentIsInvalidException(String message) {
        super(message);
    }

}

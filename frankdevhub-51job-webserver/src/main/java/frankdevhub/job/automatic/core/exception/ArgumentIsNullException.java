package frankdevhub.job.automatic.core.exception;

@SuppressWarnings("all")
public class ArgumentIsNullException extends BusinessException {

    /**
     * 参数为空异常
     *
     * @param message 异常消息
     */
    public ArgumentIsNullException(String message) {
        super(message);
    }
}

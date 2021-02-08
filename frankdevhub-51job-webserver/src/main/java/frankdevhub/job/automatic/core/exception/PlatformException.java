package frankdevhub.job.automatic.core.exception;

@SuppressWarnings("all")
public class PlatformException extends Exception {

    /**
     * 业务平台异常
     *
     * @param message 异常消息
     */
    public PlatformException(String message) {
        super(message);
    }
}

package frankdevhub.job.automatic.core.exception;

@SuppressWarnings("all")
public class InvokeRemoteException extends PlatformException {

    private String status;

    /**
     * 远程服务调用异常
     *
     * @param status  消息状态码
     * @param message 异常消息
     */
    public InvokeRemoteException(String status, String message) {
        super(message);
        setStatus(status);
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

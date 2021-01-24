package frankdevhub.job.automatic.core.exception;

@SuppressWarnings("all")
public class ArgumentIsInvalidException extends BusinessException {
    private static final long serialVersionUID = 1L;

    public ArgumentIsInvalidException(String message) {
        super(message);
    }

}

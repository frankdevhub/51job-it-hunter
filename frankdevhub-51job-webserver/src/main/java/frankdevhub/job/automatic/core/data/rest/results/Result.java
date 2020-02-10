package frankdevhub.job.automatic.core.data.rest.results;

public class Result {
    private String message;
    private String status;

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Result setStatus(String status) {
        this.status = status;
        return this;
    }
}

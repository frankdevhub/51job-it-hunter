package frankdevhub.job.automatic.core.data.rest.results;

public class SingleResult<T> extends Result {
    private T data;

    public T getData() {
        return this.data;
    }

    public SingleResult<T> setData(T data) {
        this.data = data;
        return this;
    }
}

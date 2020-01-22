package frankdevhub.job.webauto.generators;

public interface KeyGenerator<T> {
    public abstract T generateKey() throws InterruptedException;
}

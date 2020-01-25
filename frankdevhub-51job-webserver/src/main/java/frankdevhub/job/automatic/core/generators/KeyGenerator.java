package frankdevhub.job.automatic.core.generators;

public interface KeyGenerator<T> {
    public abstract T generateKey() throws InterruptedException;
}

package frankdevhub.job.automatic.core.generators;

@SuppressWarnings("all")
public interface KeyGenerator<T> {
    public abstract T generateKey() throws InterruptedException;
}

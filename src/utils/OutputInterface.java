package utils;

/**
 * Created by user on 1/14/2017.
 */
public interface OutputInterface<E> {
    void add(E element);
    int size();
    Iterable<E> getAll();
}

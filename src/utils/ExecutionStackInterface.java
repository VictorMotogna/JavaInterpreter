package utils;

import java.util.Iterator;

/**
 * Created by user on 1/14/2017.
 */
public interface ExecutionStackInterface<E> {
    void push(E element);
    E pop() throws InterpretorException;
    boolean isEmpty();
    E top() throws InterpretorException;
    Iterable<E> getAll();
}

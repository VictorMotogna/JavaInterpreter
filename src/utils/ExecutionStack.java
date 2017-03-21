package utils;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by user on 1/14/2017.
 */
public class ExecutionStack<E> implements ExecutionStackInterface<E>, Serializable {
    //region Fields
    private Deque<E> executionStack;
    //endregion

    //region Constructor
    public ExecutionStack() {
        this.executionStack = new ArrayDeque<E>();
    }
    //endregion

    //region Properties
    @Override
    public E top() throws InterpretorException {
        if(executionStack.isEmpty())
            throw new InterpretorException("The execution stack is empty.");
        return executionStack.peek();
    }

    @Override
    public Iterable<E> getAll() {
        return executionStack;
    }
    //endregion

    //region Methods
    @Override
    public void push(E statement) {
        executionStack.push(statement);
    }

    @Override
    public E pop() throws InterpretorException {
        if(executionStack.isEmpty())
            throw new InterpretorException("The execution stack is empty.");
        return executionStack.pop();
    }

    @Override
    public boolean isEmpty() {
        return executionStack.isEmpty();
    }

    @Override
    public String toString() {
        return executionStack.toString();
    }
    //endregion

}

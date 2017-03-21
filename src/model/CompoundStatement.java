package model;

import utils.ExecutionStackInterface;
import utils.InterpretorException;

import java.io.Serializable;

/**
 * Created by user on 1/15/2017.
 */
public class CompoundStatement implements Statement, Serializable {
    //region Fields
    private Statement first;
    private Statement second;
    //endregion

    //region Constructor
    public CompoundStatement(Statement first, Statement second) {
        this.first = first;
        this.second = second;
    }
    //endregion

    //region Methods
    @Override
    public ProgramState execute(ProgramState p) throws InterpretorException {
        ExecutionStackInterface<Statement> exeStack = p.getExecutionStack();
        exeStack.push(second);
        exeStack.push(first);
        return null;
    }

    public String toString() {
        return "{" + first.toString() + "; " + second.toString() + "}";
    }
    //endregion
}

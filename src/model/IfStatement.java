package model;

import utils.ExecutionStackInterface;
import utils.InterpretorException;
import utils.SymbolsTableInterface;

import java.io.Serializable;

/**
 * Created by user on 1/15/2017.
 */
public class IfStatement implements Statement, Serializable {
    //region Fields
    private Expression expression;
    private Statement first;
    private Statement second;
    //endregion

    //region Constructor
    public  IfStatement(Expression expression, Statement first, Statement second) {
        this.expression = expression;
        this.first = first;
        this.second = second;
    }
    //endregion

    //region Methods
    @Override
    public ProgramState execute(ProgramState p) throws InterpretorException {
        ExecutionStackInterface<Statement> exeStack = p.getExecutionStack();
        SymbolsTableInterface<String, Integer> symbolsTable = p.getSymbolsTable();
        if(expression.evaluate(symbolsTable, p.getHeap()) != 0)
            exeStack.push(first);
        else
            exeStack.push(second);
        return null;
    }

    public String toString() {
        return "if( Evaluate(" + expression + ") then: " + first.toString() + "; else: " + second.toString() + ")";
    }
    //endregion
}

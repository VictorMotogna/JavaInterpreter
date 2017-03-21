package model;

import utils.HeapInterface;
import utils.InterpretorException;
import utils.SymbolsTableInterface;

import java.io.Serializable;

/**
 * Created by user on 1/15/2017.
 */
public class AssignmentStatement implements Statement, Serializable {
    //region Fields
    private String variable;
    private Expression expression;
    //endregion

    //region Constructor
    public AssignmentStatement(String variable, Expression expression) {
        this.variable = variable;
        this.expression = expression;
    }
    //endregion

    //region Methods
    @Override
    public ProgramState execute(ProgramState p) throws InterpretorException {
        HeapInterface<Integer> heap = p.getHeap();
        SymbolsTableInterface<String, Integer> symbolsTable = p.getSymbolsTable();
        int result = expression.evaluate(symbolsTable, heap);
        symbolsTable.addSymbol(variable, result);
        return null;
    }

    public String toString() {
        return variable + "=" + expression.toString();
    }
    //endregion
}

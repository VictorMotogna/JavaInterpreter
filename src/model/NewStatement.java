package model;

import utils.HeapInterface;
import utils.InterpretorException;
import utils.SymbolsTableInterface;

import java.io.Serializable;

/**
 * Created by user on 1/15/2017.
 */
public class NewStatement implements Statement, Serializable {
    //region Fields
    private String variableName;
    private Expression expression;
    //endregion

    //region Constructor
    public NewStatement(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }
    //endregion

    //region Methods
    @Override
    public ProgramState execute(ProgramState p) throws InterpretorException {
        HeapInterface<Integer> heap = p.getHeap();
        SymbolsTableInterface<String, Integer> symbolsTable = p.getSymbolsTable();

        int evaluation = expression.evaluate(symbolsTable, p.getHeap());
        int address = heap.addContent(evaluation);
        symbolsTable.addSymbol(variableName, address);

        return null;
    }

    public String toString(){
        return "new("+variableName+", " + expression.toString() + ")";
    }
    //endregion
}

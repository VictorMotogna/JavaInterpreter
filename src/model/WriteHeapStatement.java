package model;

import utils.HeapInterface;
import utils.InterpretorException;
import utils.SymbolsTableInterface;

import java.io.Serializable;

/**
 * Created by user on 1/15/2017.
 */
public class WriteHeapStatement implements Statement, Serializable {
    //region Fields
    private String varName;
    private Expression expression;
    //endregion

    //region Constructor
    public WriteHeapStatement(String varName, Expression expression) {
        this.varName = varName;
        this.expression = expression;
    }
    //endregion

    //region Methods
    @Override
    public ProgramState execute(ProgramState p) throws InterpretorException{
        SymbolsTableInterface<String, Integer> symbolsTable = p.getSymbolsTable();
        HeapInterface<Integer> heap = p.getHeap();

        int address = symbolsTable.getValue(varName);
        int content = expression.evaluate(symbolsTable, heap);

        heap.addContent(address, content);

        return null;
    }

    public String toString(){
        return "WriteHeap("+varName + ", " + expression.toString()+")";
    }
    //endregion
}

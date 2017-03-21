package model;

import utils.HeapInterface;
import utils.InterpretorException;
import utils.SymbolsTableInterface;

import java.io.Serializable;

/**
 * Created by user on 1/15/2017.
 */
public class ReadHeapExpression implements Expression, Serializable {
    //region Fields
    private String variableName;
    //endregion

    //region Constructor
    public ReadHeapExpression(String variableName) {
        this.variableName = variableName;
    }
    //endregion

    //region Methods
    @Override
    public int evaluate(SymbolsTableInterface<String, Integer> symbolsTable, HeapInterface<Integer> heap) throws InterpretorException, InterpretorException {
        int address = symbolsTable.getValue(variableName);
        return heap.getContent(address);
    }

    public String toString(){
        return "ReadHeap("+variableName+")";
    }
    //endregion
}

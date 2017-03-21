package model;

import utils.HeapInterface;
import utils.InterpretorException;
import utils.SymbolsTableInterface;

import java.io.Serializable;

/**
 * Created by user on 1/15/2017.
 */
public class VariableExpression implements Expression, Serializable {
    //region Fields
    private String name;
    //endregion

    //region Constructor
    public VariableExpression(String name) {
        this.name = name;
    }
    //endregion

    //region Methods
    @Override
    public int evaluate(SymbolsTableInterface<String, Integer> symbolsTable, HeapInterface<Integer> heap) throws InterpretorException {
        return symbolsTable.getValue(name);
    }

    public String toString() {
        return name;
    }
    //endregion
}

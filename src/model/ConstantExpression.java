package model;

import utils.HeapInterface;
import utils.InterpretorException;
import utils.SymbolsTableInterface;

import java.io.Serializable;

/**
 * Created by user on 1/15/2017.
 */
public class ConstantExpression implements Expression, Serializable {
    //region Fields
    private int value;
    //endregion

    //region Constructor
    public ConstantExpression(int value) {
        this.value = value;
    }
    //endregion

    //region Methods
    @Override
    public int evaluate(SymbolsTableInterface<String, Integer> symbolsTable, HeapInterface<Integer> heap) throws InterpretorException {
        return value;
    }

    public String toString() {
        return "" + value;
    }
    //endregion
}

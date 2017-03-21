package model;

import utils.HeapInterface;
import utils.InterpretorException;
import utils.SymbolsTableInterface;

import java.io.Serializable;

/**
 * Created by user on 1/25/2017.
 */
public class LessOrEqualExpression implements Expression, Serializable {
    private Expression expression1;
    private Expression expression2;

    public LessOrEqualExpression(Expression e1,Expression e2){
        this.expression1 = e1;
        this.expression2 = e2;
    }

    @Override
    public int evaluate(SymbolsTableInterface<String, Integer> symbolsTable, HeapInterface<Integer> heap) throws InterpretorException {
        if (! (expression1.evaluate(symbolsTable, heap) <= expression2.evaluate(symbolsTable, heap)))
            return 0;
        return 1;
    }

    public String toString() {
        return "" + expression1.toString() + "<" + expression2.toString();
    }
}

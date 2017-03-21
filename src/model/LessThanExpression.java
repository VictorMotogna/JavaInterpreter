package model;

import utils.HeapInterface;
import utils.InterpretorException;
import utils.SymbolsTableInterface;

import java.io.Serializable;

/**
 * Created by user on 1/25/2017.
 */
public class LessThanExpression implements Expression, Serializable {
    private Expression expression1;
    private Expression expression2;

    public LessThanExpression(Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public int evaluate(SymbolsTableInterface<String, Integer> symbolsTable, HeapInterface<Integer> heap) throws InterpretorException {
        if (! (expression1.evaluate(symbolsTable, heap) < expression2.evaluate(symbolsTable, heap)))
            return 0;
        return 1;
    }

    public String toString() {
        return "" + expression1.toString() + "<" + expression2.toString();
    }
}

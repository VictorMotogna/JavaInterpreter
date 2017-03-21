package model;

import utils.HeapInterface;
import utils.InterpretorException;
import utils.SymbolsTableInterface;

import java.io.Serializable;

/**
 * Created by user on 1/15/2017.
 */
public class ComparisonExpression implements Expression, Serializable {
    //region Fields
    private Expression firstExpression;
    private Expression secondExpression;
    private String operator;
    //endregion

    //region Constructor
    public ComparisonExpression(Expression firstExpression, Expression secondExpression, String operator) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.operator = operator;
    }
    //endregion

    //region Methods
    @Override
    public int evaluate(SymbolsTableInterface<String, Integer> symbolsTable, HeapInterface<Integer> heap) throws InterpretorException {
        int resultFirst = firstExpression.evaluate(symbolsTable, heap);
        int resultSecond = secondExpression.evaluate(symbolsTable, heap);
        switch (operator) {
            case "<":
                if(resultFirst < resultSecond)
                    return 1;
                else
                    return 0;
            case "<=":
                if(resultFirst <= resultSecond)
                    return 1;
                else
                    return 0;
            case ">":
                if(resultFirst > resultSecond)
                    return 1;
                else
                    return 0;
            case ">=":
                if(resultFirst >= resultSecond)
                    return 1;
                else
                    return 0;
            case "==":
                if(resultFirst == resultSecond)
                    return 1;
                else
                    return 0;
            case "!=":
                if(resultFirst != resultSecond)
                    return 1;
                else
                    return 0;
            default:
                throw new InterpretorException("Invalid Operator");
        }
    }

    public String toString(){
        return firstExpression.toString() + operator + secondExpression.toString();
    }
    //endregion
}

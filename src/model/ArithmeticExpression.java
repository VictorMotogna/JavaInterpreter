package model;

import utils.HeapInterface;
import utils.InterpretorException;
import utils.SymbolsTableInterface;

import java.io.Serializable;

/**
 * Created by user on 1/15/2017.
 */
public class ArithmeticExpression implements Expression, Serializable {
    //region Fields
    private char operator;
    private Expression first;
    private Expression second;
    //endregion

    //region Constructor
    public ArithmeticExpression(Expression first, Expression second, char operator) {
        this.first = first;
        this.second = second;
        this.operator = operator;
    }
    //endregion

    //region Menthods
    @Override
    public int evaluate(SymbolsTableInterface<String, Integer> symbolsTable, HeapInterface<Integer> heap) throws InterpretorException {
        int resultFirst = first.evaluate(symbolsTable, heap);
        int resultSecond = second.evaluate(symbolsTable, heap);
        switch (operator) {
            case '+':
                return resultFirst + resultSecond;
            case '-':
                return resultFirst - resultSecond;
            case '*':
                return resultFirst * resultSecond;
            case '/':
                if (resultSecond == 0)
                    throw new InterpretorException("Can't divide by 0");
                return resultFirst / resultSecond;
            default:
                throw new InterpretorException("Invalid Operator");
        }
    }

    public String toString() {
        return first.toString() + operator + second.toString();
    }
    //endregion
}

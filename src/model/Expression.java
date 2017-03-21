package model;

import utils.HeapInterface;
import utils.InterpretorException;
import utils.SymbolsTableInterface;

/**
 * Created by user on 1/14/2017.
 */
public interface Expression {
    int evaluate(SymbolsTableInterface<String, Integer> symbolsTable, HeapInterface<Integer> heap) throws InterpretorException;
}

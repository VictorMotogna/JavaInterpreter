package model;

import utils.InterpretorException;
import utils.OutputInterface;
import utils.SymbolsTableInterface;

import java.io.Serializable;

/**
 * Created by user on 1/15/2017.
 */
public class PrintStatement implements Statement, Serializable {
    //region Fields
    private Expression expression;
    //endregion

    //region Constructor
    public PrintStatement(Expression expression){
        this.expression = expression;
    }
    //endregion

    //region Methods
    public ProgramState execute(ProgramState p) throws InterpretorException {
        OutputInterface<Integer> o = p.getOutput();
        SymbolsTableInterface<String, Integer> s = p.getSymbolsTable();
        o.add(expression.evaluate(s, p.getHeap()));
        return null;
    }
    public String toString(){
        return "print(" + expression.toString()+")";
    }
    //endregion
}

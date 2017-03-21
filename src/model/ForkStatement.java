package model;

import utils.ExecutionStack;
import utils.InterpretorException;
import utils.SymbolsTableInterface;

/**
 * Created by user on 1/15/2017.
 */
public class ForkStatement implements Statement {
    //region Fields
    private Statement statement;
    //endregion

    //region Constructor
    public ForkStatement(Statement statement) {
        this.statement = statement;
    }
    //endregion

    //region Methods
    @Override
    public ProgramState execute(ProgramState p) throws InterpretorException {
        SymbolsTableInterface<String, Integer> symbolsTable = p.getSymbolsTable();
        SymbolsTableInterface clonnedSymbolsTable = symbolsTable.clone();
        ProgramState newProgamState = new ProgramState(statement, new ExecutionStack<>(), clonnedSymbolsTable, p.getOutput(), p.getFileTable(), p.getHeap());
        return newProgamState;
    }

    public String toString() {
        return "Fork(" + statement.toString() + ")";
    }
    //endregion
}

package model;

import utils.ExecutionStack;
import utils.ExecutionStackInterface;
import utils.InterpretorException;
import utils.SymbolsTableInterface;

import java.io.Serializable;

/**
 * Created by user on 1/15/2017.
 */
public class WhileStatement implements Statement, Serializable {
    //region Fields
    private Expression expression;
    private Statement statement;
    //endregion

    //region Constructor
    public WhileStatement(Expression expression, Statement statement) {
        this.expression = expression;
        this.statement = statement;
    }
    //endregion

    //region Methods
    @Override
    public ProgramState execute(ProgramState p) throws InterpretorException {
        ExecutionStackInterface<Statement> exe = p.getExecutionStack();
        SymbolsTableInterface<String, Integer> s = p.getSymbolsTable();
        if(expression.evaluate(s, p.getHeap()) != 0) {
            exe.push(this);
            exe.push(statement);
        }
        return null;
    }

    public String toString(){
        return "while("+expression.toString()+")" + statement.toString();
    }
    //endregion
}

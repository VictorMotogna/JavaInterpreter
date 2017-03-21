package model;

import utils.ExecutionStackInterface;
import utils.InterpretorException;

/**
 * Created by user on 1/25/2017.
 */
public class ForStatement implements Statement {
    //region Fields
    private Statement initialStatement;
    private Expression condition;
    private Statement finalStatement;
    private Statement statement;
    //endregion

    //region Constructor
    public ForStatement(Statement initialStatement, Expression condition, Statement finalStatement, Statement statement) {
        this.initialStatement = initialStatement;
        this.condition = condition;
        this.finalStatement = finalStatement;
        this.statement = statement;
    }
    //endregion

    //region Methods
    @Override
    public ProgramState execute(ProgramState p) throws InterpretorException {
        ExecutionStackInterface<Statement> executionStack = p.getExecutionStack();
        executionStack.push(new WhileStatement(condition, new CompoundStatement(statement, finalStatement)));
        executionStack.push(initialStatement);

//        System.out.println(initialStatement.toString());
//        System.out.println(finalStatement.toString());
//        System.out.println(condition.toString());
//        System.out.println(statement.toString());

        return null;
    }

    public String toString() {
        return "for(" + initialStatement.toString() + "; " + condition.toString() + "; " + finalStatement.toString() + ")";
    }
    //endregion
}

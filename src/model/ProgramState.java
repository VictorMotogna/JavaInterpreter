package model;

import utils.*;

import javax.swing.plaf.nimbus.State;
import java.io.Serializable;

/**
 * Created by user on 1/14/2017.
 */
public class ProgramState implements Serializable {
    //region Fields
    private int id;
    private Statement initialProgram;
    private ExecutionStackInterface<Statement> executionStack;
    private SymbolsTableInterface<String, Integer> symbolsTable;
    private OutputInterface<Integer> output;
    private FileTableInterface<Integer, FileData> fileTable;
    private HeapInterface<Integer> heap;
    //endregion

    //region Constructor
    public ProgramState(Statement initialProgram, ExecutionStackInterface<Statement> executionStack, SymbolsTableInterface<String, Integer> symbolsTable, OutputInterface<Integer> output, FileTableInterface<Integer, FileData> fileTable, HeapInterface<Integer> heap) {
        this.id = ProgramIdGenerator.generateProgramStateId();
        this.initialProgram = initialProgram;
        this.executionStack = executionStack;
        this.symbolsTable = symbolsTable;
        this.output = output;
        this.fileTable = fileTable;
        this.heap = heap;
        executionStack.push(initialProgram);
    }
    //endregion

    //region Properties
    public int getId() {
        return id;
    }

    public Statement getInitialProgram() {
        return initialProgram;
    }

    public FileTableInterface<Integer, FileData> getFileTable(){return fileTable;}

    public ExecutionStackInterface<Statement> getExecutionStack() {
        return executionStack;
    }

    public SymbolsTableInterface<String, Integer> getSymbolsTable() {
        return symbolsTable;
    }

    public HeapInterface<Integer> getHeap() { return heap; }

    public OutputInterface<Integer> getOutput() {
        return output;
    }
    //endregion

    //region Methods
    public boolean isNotCompleted(){
        return !executionStack.isEmpty();
    }

    public ProgramState oneStep() throws InterpretorException {
        if(executionStack.isEmpty()){
            throw new InterpretorException("The program has finished execution");
        }
        Statement currentStatement = executionStack.pop();
        return currentStatement.execute(this);
    }

    public String toString(){
        return "Program " + id + ":\n"+ "Execution Stack: " + executionStack.toString() + ";\n" + "Symbols Table: " + symbolsTable.toString() + "\n" + "Output: " + output.toString() + ";\nFileTable\n" + fileTable.toString() + ";\nHeap\n" + heap.toString() +  "\n---------------\n";
    }
    //endregion
}

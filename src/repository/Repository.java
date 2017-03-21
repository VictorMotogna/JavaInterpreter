package repository;

import model.ProgramState;
import model.Statement;
import utils.FileData;
import utils.InterpretorException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 1/15/2017.
 */
public class Repository implements RepositoryInterface {
    //region Fields
    private List<ProgramState> programStates;
    private String fileName;
    //endregion

    //region Constructor
    public Repository(String fileName) {
        programStates = new ArrayList<ProgramState>();
        this.fileName = fileName;
    }
    //endregion

    //region Properties
    @Override
    public ProgramState getInitialProgram() {
        return programStates.get(0);
    }

    @Override
    public void setProgramState(ProgramState programState){ programStates.set(0, programState); }

    @Override
    public List<ProgramState> getProgramStates() {
        return programStates;
    }

    @Override
    public void setProgramStates(List<ProgramState> newProgramStates) {
        programStates = newProgramStates;
    }
    //endregion

    //region Methods
    @Override
    public void addProgramState(ProgramState programState) {
        programStates.add(programState);
    }

    @Override
    public void logProgramStateExecute(ProgramState programState) throws InterpretorException {
        try {
            PrintWriter logFile = new PrintWriter(new FileWriter(fileName, true), true);
            logFile.println("Program "+ programState.getId() + ":");
            logFile.println("Execution Stack: ");
            for (Statement statement : programState.getExecutionStack().getAll()) {
                logFile.println(statement);
            }
            logFile.println();
            logFile.println("Symbols Table: ");
            for (Map.Entry<String, Integer> entry : programState.getSymbolsTable().getAll()) {
                logFile.println(entry.getKey() + " = " + entry.getValue());
            }
            logFile.println();
            logFile.println("Output: ");
            for (Integer output : programState.getOutput().getAll()) {
                logFile.println(output);
            }
            logFile.println();
            logFile.println("FileTable: ");
            for (Map.Entry<Integer, FileData> file : programState.getFileTable().getAllFiles()) {
                logFile.println(file.getKey() + " : " + file.getValue().getFileName());
            }
            logFile.println();
            logFile.println("Heap: ");
            for(Map.Entry<Integer, Integer> heapEntry : programState.getHeap().getAll()){
                logFile.println(heapEntry.getKey() + " -> " + heapEntry.getValue());
            }
            logFile.println("-------");
            logFile.close();
        } catch (IOException e) {
            throw new InterpretorException("Couldn't write into the file");
        }
    }

    @Override
    public void serialize(ProgramState programState, String fileName) throws InterpretorException {
        try{
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(fileName));
            stream.writeObject(programState);
            stream.close();
        }catch (IOException e){
            throw new InterpretorException(e.getMessage());
        }
    }

    @Override
    public ProgramState deserialize(String fileName) throws InterpretorException {
        try{
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream(fileName));
            Object object = stream.readObject();
            if(object instanceof ProgramState){
                return (ProgramState) object;
            }
            throw new InterpretorException("Error in repository");
        } catch (Exception e){
            throw new InterpretorException(e.getMessage());
        }
    }
    //endregion
}

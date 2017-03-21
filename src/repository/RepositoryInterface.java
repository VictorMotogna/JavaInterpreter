package repository;

import model.ProgramState;
import utils.InterpretorException;

import java.util.List;

/**
 * Created by user on 1/15/2017.
 */
public interface RepositoryInterface {
    void addProgramState(ProgramState programState);
    void setProgramState(ProgramState programState);
    ProgramState getInitialProgram();
    List<ProgramState> getProgramStates();
    void setProgramStates(List<ProgramState> newProgramStates);
    void logProgramStateExecute(ProgramState programState) throws InterpretorException;
    void serialize(ProgramState programState, String fileName) throws InterpretorException;
    ProgramState deserialize(String fileName) throws InterpretorException;
}

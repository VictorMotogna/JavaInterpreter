package model;

import utils.InterpretorException;

/**
 * Created by user on 1/14/2017.
 */
public interface Statement {
    ProgramState execute(ProgramState p) throws InterpretorException;
}

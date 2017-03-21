package model;

import utils.FileData;
import utils.FileTableInterface;
import utils.InterpretorException;
import utils.SymbolsTableInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by user on 1/15/2017.
 */
public class CloseFileStatement implements Statement, Serializable {
    //region Fields
    private Expression idDescriptorName;
    //endregion

    //region Constructor
    public CloseFileStatement(Expression idDescriptorName) {
        this.idDescriptorName = idDescriptorName;
    }
    //endregion

    //region Methods
    @Override
    public ProgramState execute(ProgramState p) throws InterpretorException {
        SymbolsTableInterface<String, Integer> symbolsTable = p.getSymbolsTable();
        int result = idDescriptorName.evaluate(symbolsTable, p.getHeap());
        FileTableInterface<Integer, FileData> fileTable = p.getFileTable();
        FileData fileData = fileTable.getFileData(result);
        BufferedReader bufferedReader = fileData.getReader();
        try {
            bufferedReader.close();
            fileTable.removeFile(result);
        } catch (IOException e) {
            throw new InterpretorException("The file could not be closed.");
        }
        return null;
    }

    public String toString() {
        return "Closed file with id " + idDescriptorName;
    }
    //endregion
}

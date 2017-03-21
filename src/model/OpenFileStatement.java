package model;

import utils.FileData;
import utils.FileIdGenerator;
import utils.FileTableInterface;
import utils.InterpretorException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by user on 1/15/2017.
 */
public class OpenFileStatement implements Statement, Serializable {
    //region Fields
    private String fileName;
    private String varName;
    //endregion Fields

    //region Constructor
    public OpenFileStatement(String fileName, String varName){
        this.fileName = fileName;
        this.varName = varName;
    }
    //endregion Constructor

    //region Methods
    private boolean isOpen(String fileName, FileTableInterface<Integer, FileData> fileTable){
        for(Map.Entry<Integer, FileData> file: fileTable.getAllFiles()){
            if(fileName.equals(file.getValue().getFileName()))
                return true;
        }
        return false;
    }

    @Override
    public ProgramState execute(ProgramState p) throws InterpretorException {
        if(isOpen(fileName, p.getFileTable()))
            throw new InterpretorException("File Already Opened");
        try{
            BufferedReader buffer = new BufferedReader(new FileReader(fileName));
            FileData fileData = new FileData(fileName, buffer);
            int id = FileIdGenerator.generateId();
            p.getFileTable().addFile(id, fileData);
            p.getSymbolsTable().addSymbol(varName + Integer.toString(id), id);
        }catch (IOException e){
            throw new InterpretorException("Error opening file");
        }
        return null;
    }

    public String toString(){
        return "Open file: " + fileName;
    }

    //endregion Methods
}

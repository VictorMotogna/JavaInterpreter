//package main;
//
//import controller.Controller;
//import model.*;
//import repository.Repository;
//import utils.*;
//import view.*;
//
///**
// * Created by user on 1/16/2017.
// */
//public class Main {
//    public static void main(String args[]){
//        Statement workingInMemory = new IfStatement(new ConstantExpression(0), new CompoundStatement(new AssignmentStatement("x", new ConstantExpression(2)), new PrintStatement(new ArithmeticExpression(new ConstantExpression(2), new ConstantExpression(4), '+'))), new CompoundStatement(new AssignmentStatement("a", new ConstantExpression(10)), new PrintStatement(new VariableExpression("a"))));
//        repository.Repository repositoryMemory = new repository.Repository("Working In Memory.txt");
//        ProgramState programMemory = new ProgramState(workingInMemory, new ExecutionStack<>(), new SymbolsTable<>(), new Output<>(), new FileTable<>(), new Heap<>());
//        repositoryMemory.addProgramState(programMemory);
//        Controller controllerMemory = new Controller(repositoryMemory);
//
//        Statement variableException = new PrintStatement(new VariableExpression("a"));
//        repository.Repository repositoryVariableException = new repository.Repository("Variable Exception.txt");
//        ProgramState programVariableException = new ProgramState(variableException, new ExecutionStack<>(), new SymbolsTable<>(), new Output<>(), new FileTable<>(), new Heap<>());
//        repositoryVariableException.addProgramState(programVariableException);
//        Controller controllerVariableException = new Controller(repositoryVariableException);
//
//        Statement divideBy0Exception = new PrintStatement(new ArithmeticExpression(new ConstantExpression(5), new ConstantExpression(0), '/'));
//        Repository repositoryDivideBy0Exception = new Repository("Divide By 0 Exception.txt");
//        ProgramState programDivideBy0Exception = new ProgramState(divideBy0Exception, new ExecutionStack<>(), new SymbolsTable<>(), new Output<>(), new FileTable<>(), new Heap<>());
//        repositoryDivideBy0Exception.addProgramState(programDivideBy0Exception);
//        Controller controllerDivideBy0Exception = new Controller(repositoryDivideBy0Exception);
//
//        Statement file = new CompoundStatement(new CompoundStatement(new OpenFileStatement("test.txt", "fileVar"), new ReadFileStatement(new ConstantExpression(1), "a")), new CloseFileStatement(new ConstantExpression(1)));
//        Repository repositoryFile = new Repository("File.txt");
//        ProgramState programFile = new ProgramState(file, new ExecutionStack<>(), new SymbolsTable<>(), new Output<>(), new FileTable<>(), new Heap<>());
//        repositoryFile.addProgramState(programFile);
//        Controller controllerFile = new Controller(repositoryFile);
//
//        Statement fileException = new ReadFileStatement(new ConstantExpression(1), "a");
//        Repository repositoryFileException = new Repository("FileException.txt");
//        ProgramState programFileException = new ProgramState(fileException, new ExecutionStack<>(), new SymbolsTable<>(), new Output<>(), new FileTable<>(), new Heap<>());
//        repositoryFileException.addProgramState(programFileException);
//        Controller controllerFileException = new Controller(repositoryFileException);
//
//        Statement heapStatement = new CompoundStatement(new CompoundStatement(new CompoundStatement(new CompoundStatement(new AssignmentStatement("v", new ConstantExpression(10)), new NewStatement("v", new ConstantExpression(20))), new CompoundStatement(new NewStatement("a", new ConstantExpression(22)), new WriteHeapStatement("a", new ConstantExpression(30)))), new CompoundStatement(new PrintStatement(new VariableExpression("a")), new PrintStatement(new ReadHeapExpression("a")))), new AssignmentStatement("a", new ConstantExpression(0)));
//        Repository repositoryHeap = new Repository("FileHeap.txt");
//        ProgramState programHeap = new ProgramState(heapStatement, new ExecutionStack<>(), new SymbolsTable<>(), new Output<>(), new FileTable<>(), new Heap<>());
//        repositoryHeap.addProgramState(programHeap);
//        Controller controllerHeap = new Controller(repositoryHeap);
//
//        Statement whileStatement = new CompoundStatement(new CompoundStatement(new AssignmentStatement("v", new ConstantExpression(6)), new WhileStatement(new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(2), '-'), new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignmentStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(1), '-'))))), new PrintStatement(new VariableExpression("v")));
//        Repository repositoryWhile = new Repository("FileWhile.txt");
//        ProgramState programWhile = new ProgramState(whileStatement, new ExecutionStack<>(), new SymbolsTable<>(), new Output<>(), new FileTable<>(), new Heap<>());
//        repositoryWhile.addProgramState(programWhile);
//        Controller controllerWhile = new Controller(repositoryWhile);
//
//        Statement serializableStatement = new AssignmentStatement("v", new ConstantExpression(2));
//        Repository repositorySerializable = new Repository("FileSerializable.txt");
//        ProgramState programSerializable = new ProgramState(serializableStatement, new ExecutionStack<>(), new SymbolsTable<>(), new Output<>(), new FileTable<>(), new Heap<>());
//        repositorySerializable.addProgramState(programSerializable);
//        Controller controllerSerializable = new Controller(repositorySerializable);
//
//        Statement threadStatement = new CompoundStatement(new AssignmentStatement("v", new ConstantExpression(10)), new CompoundStatement(new NewStatement("a", new ConstantExpression(22)), new CompoundStatement(new ForkStatement(new CompoundStatement(new WriteHeapStatement("a", new ConstantExpression(30)), new CompoundStatement(new AssignmentStatement("v", new ConstantExpression(32)), new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeapExpression("a")))))), new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeapExpression("a"))))));
//        Repository repositoryThread = new Repository("FileThread.txt");
//        ProgramState programThread = new ProgramState(threadStatement, new ExecutionStack<>(), new SymbolsTable<>(), new Output<>(), new FileTable<>(), new Heap<>());
//        repositoryThread.addProgramState(programThread);
//        Controller controllerThread = new Controller(repositoryThread);
//
//        TextMenu ui = new TextMenu();
//        ui.addCommand(new AllStepsCommand("1", workingInMemory.toString(), controllerMemory));
//        ui.addCommand(new AllStepsCommand("2", variableException.toString(), controllerVariableException));
//        ui.addCommand(new AllStepsCommand("3", divideBy0Exception.toString(), controllerDivideBy0Exception));
//        ui.addCommand(new AllStepsCommand("4", file.toString(), controllerFile));
//        ui.addCommand(new AllStepsCommand("5", fileException.toString(), controllerFileException));
//        ui.addCommand(new AllStepsCommand("6", heapStatement.toString(), controllerHeap));
//        ui.addCommand(new AllStepsCommand("7", whileStatement.toString(), controllerWhile));
//        ui.addCommand(new DeserializeCommand("8", "Deserialize into a new program", controllerSerializable));
//        ui.addCommand(new SerializeCommand("9", "Serialize: " + serializableStatement, controllerSerializable));
//        ui.addCommand(new AllStepsCommand("10", "Execute Deserialized Program", controllerSerializable));
//        ui.addCommand(new AllStepsForAllProgramsCommand("t", threadStatement.toString(), controllerThread));
//        ui.addCommand(new ExitCommand("0", "Exit"));
//
//        ui.show();
//    }
//}

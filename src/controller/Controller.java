package controller;

import repository.Repository;
import model.ProgramState;
import model.Statement;
import utils.ExecutionStackInterface;
import utils.InterpretorException;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by user on 1/16/2017.
 */
public class Controller {
    //region Fields
    private Repository repository;
    private ExecutorService executor;
    //endregion

    //region Constructor
    public Controller(Repository repository){
        this.repository = repository;
    }
    //endregion

    //region Garbage Collector
    private Map<Integer, Integer> conservativeGarbageCollector(Collection<Integer> symbolTableValues, Map<Integer, Integer> heap){
        Map<Integer, Integer> newHeap = heap.entrySet().stream()
                .filter(e->symbolTableValues.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return newHeap;
    }
    //endregion

    //region Properties
    public ProgramState getInitialProgram() { return repository.getInitialProgram(); }

    public void setProgramState(ProgramState programState) { repository.setProgramState(programState);}

    public List<ProgramState> getPrograms() {return repository.getProgramStates(); }

    public ProgramState getProgramsById(int id){
        for(ProgramState p: repository.getProgramStates()){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }
    //endregion

    //region Methods
    public List<ProgramState> removeCompleteProgram(List<ProgramState> programStateList){
        return  programStateList.stream().
                filter(p->p.isNotCompleted()).
                collect(Collectors.toList());
    }

    public void oneStepForAllPrograms(List<ProgramState> programStateList) throws InterruptedException {
        List<Callable<ProgramState>> callableProgramStateList = programStateList.stream().
                map((ProgramState p) ->(Callable<ProgramState>)(()-> p.oneStep()))
                .collect(Collectors.toList());


        List<ProgramState> newProgramList = null;
        try {
            newProgramList = executor.invokeAll(callableProgramStateList).stream().map(future -> {
                try {
                    return future.get();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return null;
                }})
                    .filter(p -> p != null)
                    .collect(Collectors.toList());
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }

        if (!newProgramList.isEmpty()) {
            programStateList.addAll(newProgramList);

            repository.setProgramStates(programStateList);
        }
        programStateList.forEach(programState -> {
            try {
                this.repository.logProgramStateExecute(programState);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

    }

    public void allStepsForAllPrograms() throws InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        while(true){
            List<ProgramState> programStateList = removeCompleteProgram(repository.getProgramStates());
            if(programStateList.size() == 0){
                break;
            }
            oneStepForAllPrograms(programStateList);
        }
        executor.shutdownNow();
    }

    public ProgramState executeOneStep(ProgramState state) throws InterpretorException {
        ExecutionStackInterface<Statement> executionStack = state.getExecutionStack();
        if(executionStack.isEmpty())
            throw new InterpretorException("Stack is empty");
        System.out.print(state.toString());
        try {
            repository.logProgramStateExecute(state);
        } catch (Exception e){
            throw new InterpretorException("The file couldn't be written");
        }
        Statement currentStatement = executionStack.pop();
        return currentStatement.execute(state);
    }

    public void executeAllSteps() throws InterpretorException {
        ProgramState currentProgramState = repository.getInitialProgram();
        while(!currentProgramState.getExecutionStack().isEmpty()){
            executeOneStep(currentProgramState);
            currentProgramState.getHeap().setContent(conservativeGarbageCollector(currentProgramState.getSymbolsTable().getValues(), currentProgramState.getHeap().getContent()));
        }
        System.out.print(currentProgramState.toString());
        try {
            repository.logProgramStateExecute(repository.getInitialProgram());
        } catch (Exception e){
            throw new InterpretorException("The file couldn't be written");
        }
    }

    public void serialize(ProgramState programState, String fileName) throws InterpretorException {
        repository.serialize(programState, fileName);
    }

    public void deserialize(String fileName) throws InterpretorException {
        try {
            setProgramState(repository.deserialize(fileName));
            System.out.print(repository.getInitialProgram());
        } catch(Exception e){
            throw new InterpretorException(e.getMessage());
        }
    }

    public void allStepGUI() throws InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> programs = removeCompleteProgram(repository.getProgramStates());
        if (!programs.isEmpty()) {
            oneStepForAllPrograms(programs);
        }
        executor.shutdownNow();
    }
    //endregion
}

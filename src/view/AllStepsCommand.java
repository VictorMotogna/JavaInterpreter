package view;

import controller.Controller;
import utils.InterpretorException;

/**
 * Created by user on 1/16/2017.
 */
public class AllStepsCommand extends Command {
    //region Fields
    private Controller controller;
    //endregion

    //region Constructor
    public AllStepsCommand(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }
    //endregion

    //region Methods
    @Override
    public void execute() {
        try {
            controller.executeAllSteps();
        } catch(InterpretorException e) {
            System.out.println(e.getMessage());
        }
    }
    //endregion
}

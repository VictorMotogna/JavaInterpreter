package view;

import controller.Controller;

/**
 * Created by user on 1/16/2017.
 */
public class AllStepsForAllProgramsCommand extends Command {
    //region Fields
    private Controller controller;
    //endregion

    //region Constructor
    public AllStepsForAllProgramsCommand(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }
    //endregion

    //region Methods
    @Override
    public void execute() {
        try {
            controller.allStepsForAllPrograms();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //endregion
}

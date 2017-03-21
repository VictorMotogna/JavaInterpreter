package view;

import controller.Controller;
import utils.InterpretorException;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Created by user on 1/16/2017.
 */
public class SerializeCommand extends Command {
    //region Fields
    private Controller controller;
    //endregion

    //region Constructor
    public SerializeCommand(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }
    //endregion

    //region Methods
    @Override
    public void execute() {
        System.out.println("Give a file where you'd like to save the serialized content: ");
        Scanner scanner = new Scanner(System.in);
        String filename = scanner.nextLine();
        try {
            controller.serialize(controller.getInitialProgram(), filename);
        } catch (InterpretorException e) {
            System.out.println(e.getMessage());
        }
    }
    //endregion
}

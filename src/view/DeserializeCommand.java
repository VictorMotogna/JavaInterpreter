package view;

import controller.Controller;
import utils.InterpretorException;

import java.util.Scanner;

/**
 * Created by user on 1/16/2017.
 */
public class DeserializeCommand extends Command {
    //region Fields
    private Controller controller;
    //endregion

    //region Constructor
    public DeserializeCommand(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }
    //endregion

    //region Methods
    @Override
    public void execute() {
        System.out.println("Give a file from where you'd like to deserialize: ");
        Scanner scanner = new Scanner(System.in);
        String filename = scanner.nextLine();
        try {
            controller.deserialize(filename);
        } catch (InterpretorException e) {
            System.out.println(e.getMessage());
        }
    }
    //endregion
}

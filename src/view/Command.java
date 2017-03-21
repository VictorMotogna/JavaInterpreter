package view;

/**
 * Created by user on 1/16/2017.
 */
public abstract class Command {
    //region Fields
    private String key;
    private String description;
    //endregion

    //region Constructor
    public Command(String key, String description) {
        this.key = key;
        this.description = description;
    }
    //endregion

    //region Methods
    public abstract void execute();

    public String getKey() { return key; }

    public String getDescription() { return description; }
    //endregion
}

package utils;

/**
 * Created by user on 1/15/2017.
 */
public class HeapAddressGenerator {
    //region Fields
    private static int counter = 0;
    //endregion

    //region Methods
    public static int generateId() {
        return ++counter;
    }
    //endregion
}

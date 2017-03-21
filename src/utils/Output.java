package utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 1/15/2017.
 */
public class Output<E> implements OutputInterface<E>, Serializable {
    //region Fields
    private List<E> outputList;
    //endregion

    //region Constructor
    public Output() {
        this.outputList = new ArrayList<E>();
    }
    //endregion

    //region Methods
    @Override
    public void add(E element) {
        outputList.add(element);

    }

    public String toString() {
        return outputList.toString();
    }
    //endregion

    //region Properties
    @Override
    public int size() {
        return outputList.size();
    }

    @Override
    public Iterable<E> getAll() {
        return outputList;
    }
    //endregion
}

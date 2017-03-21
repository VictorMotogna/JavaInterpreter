package utils;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by user on 1/14/2017.
 */
public interface HeapInterface<Value> {
    int addContent(Value content);
    void addContent(Integer address, Value content);
    void setContent(Map<Integer, Value> map);
    Value getContent(Integer address) throws InterpretorException;
    boolean containsKey(Integer address);
    boolean containsContent(Value content);
    boolean isEmpty();
    Iterable<Map.Entry<Integer, Value>> getAll();
    Map<Integer, Value> getContent();
    ArrayList<Value> getValues();
}

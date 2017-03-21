package utils;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by user on 1/14/2017.
 */
public interface SymbolsTableInterface<K, V> {
    void addSymbol(K symbolName, V value);
    boolean contains(K symbolName);
    V getValue(K symbolName) throws InterpretorException;
    void setValue(K symbolName, V value) throws InterpretorException;
    int size();
    Iterable<Map.Entry<K,V>> getAll();
    ArrayList<V> getValues();
    SymbolsTableInterface<K, V> clone();
}

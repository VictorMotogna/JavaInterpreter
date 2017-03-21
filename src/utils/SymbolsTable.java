package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 1/16/2017.
 */
public class SymbolsTable<K, V> implements SymbolsTableInterface<K, V> {
    //region Fields
    private Map<K, V> symbolsTable;
    //endregion

    //region Constructor
    public SymbolsTable(){
        this.symbolsTable = new HashMap<>();
    }
    //endregion

    //region Properties
    @Override
    public V getValue(K symbolName) throws InterpretorException {
        if(!symbolsTable.containsKey(symbolName))
            throw new InterpretorException("The Value of " + symbolName + " doesn't exist");
        return symbolsTable.get(symbolName);
    }

    @Override
    public void setValue(K symbolName, V value) throws InterpretorException {
        if(!symbolsTable.containsKey(symbolName))
            throw new InterpretorException("The Value of " + symbolName + "doesn't exist");
        symbolsTable.replace(symbolName, value);
    }

    @Override
    public int size() {
        return symbolsTable.size();
    }

    @Override
    public ArrayList<V> getValues() {
        ArrayList<V> values = new ArrayList<>();
        for(Map.Entry<K, V> entry : symbolsTable.entrySet()){
            values.add(entry.getValue());
        }
        return values;
    }

    @Override
    public SymbolsTableInterface<K, V> clone() {
        SymbolsTableInterface newSymbolsTable = new SymbolsTable<K, V>();
        for(Map.Entry<K, V> entry: getAll()){
            newSymbolsTable.addSymbol(entry.getKey(), entry.getValue());
        }
        return newSymbolsTable;
    }

    @Override
    public Iterable<Map.Entry<K, V>> getAll() {
        return symbolsTable.entrySet();
    }
    //endregion

    //region Methods
    @Override
    public void addSymbol(K symbolName, V value) {
        symbolsTable.put(symbolName, value);
    }

    @Override
    public boolean contains(K symbolName) {
        return symbolsTable.containsKey(symbolName);
    }

    @Override
    public String toString(){
        StringBuffer result = new StringBuffer();
        for (Map.Entry<K, V> entry : symbolsTable.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            result.append(key);
            result.append(" -> ");
            result.append(value);
            result.append("; ");
        }
        return result.toString();
    }
    //endregion
}

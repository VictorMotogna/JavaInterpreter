package utils;

import java.util.Map;

/**
 * Created by user on 1/14/2017.
 */
public interface FileTableInterface<K, V> {
    void addFile(K key, V value);
    V getFileData(K key) throws InterpretorException;
    void removeFile(K key) throws InterpretorException;
    Iterable<Map.Entry<K, V>> getAllFiles();
}

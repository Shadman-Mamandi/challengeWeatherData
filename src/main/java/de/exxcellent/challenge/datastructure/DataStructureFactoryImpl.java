package de.exxcellent.challenge.datastructure;

import de.exxcellent.challenge.abstractions.DataStructure;
import de.exxcellent.challenge.abstractions.DataStructureFactory;
import java.util.List;

public class DataStructureFactoryImpl<K, V> implements DataStructureFactory<K, V> {
    @Override
    public DataStructure<K, V> produce(K[] keys, List<V[]> tabularData) {
        return new TabularDataStructure<>(keys, tabularData);
    }
}

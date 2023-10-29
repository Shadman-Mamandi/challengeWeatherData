package de.exxcellent.challenge.datastructure;

import de.exxcellent.challenge.abstractions.DataStructure;
import de.exxcellent.challenge.abstractions.DataStructureFactory;

import java.util.List;

public class DataStructureFactoryImpl implements DataStructureFactory {
    @Override
    public DataStructure produce(List<String[]> tabularData) {
        var structure = new TabularDataStructure();
        structure.addData(tabularData);
        return structure;
    }
}

package de.exxcellent.challenge.datastructure;

import de.exxcellent.challenge.abstractions.DataIdentifier;
import de.exxcellent.challenge.abstractions.DataStructure;
import de.exxcellent.challenge.abstractions.DataElement;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TabularDataStructure implements DataStructure {
    private List<RowDataElement> dataRows;
    private List<String> headers;

    public void addData(final List<String[]> data) throws IllegalArgumentException {
        if (data.size() < 1) {
            throw new IllegalArgumentException("There must be at least one row in the data!");
        }
        this.headers = Arrays.stream(data.stream().findFirst().get()).toList();
        this.dataRows = data.stream()
                .skip(1)
                .map(s -> {
                    var row = new RowDataElement();
                    for (int i=0; i<headers.size(); i++) {
                        row.addValue(headers.get(i), s[i]);
                    }
                    return row;
                })
                .collect(Collectors.toList());
    }

    public DataElement find(final Function<Pair<DataElement, DataElement>, DataElement> compareFunction) {
        DataElement result = null;
        for (final var potentialResult : dataRows) {
            Pair<DataElement, DataElement> pair = Pair.of(result, potentialResult);
            result = compareFunction.apply(pair);
        }
        return result;
    }

    @Override
    public DataIdentifier createDataIdentifier(final String dataFieldName) {
        Optional<String> header = this.headers.stream()
                .filter(h -> Objects.equals(h, dataFieldName))
                .findFirst();
        if (header.isEmpty()) return null;
        return new RowDataIdentifier(header.get());
    }

}

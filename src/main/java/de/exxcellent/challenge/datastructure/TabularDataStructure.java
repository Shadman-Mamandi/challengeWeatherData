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

public class TabularDataStructure<K, V> implements DataStructure<K, V> {
    private final List<RowDataElement<K, V>> dataRows;
    private final List<K> headers;

    public TabularDataStructure(final K[] headers, final List<V[]> data) {
        this.headers = Arrays.stream(headers).toList();
        this.dataRows = data.stream()
                .skip(1)
                .filter(Objects::nonNull)
                .map(s -> {
                    var row = new RowDataElement<K, V>();
                    for (int i=0; i<this.headers.size(); i++) {
                        row.addValue(this.headers.get(i), s[i]);
                    }
                    return row;
                })
                .collect(Collectors.toList());
    }

    public DataElement<K, V> find(final Function<
            Pair<DataElement<K, V>, DataElement<K, V>>,
            DataElement<K, V>> compareFunction) {
        DataElement<K, V> result = null;
        for (final var potentialResult : dataRows) {
            Pair<DataElement<K, V>, DataElement<K, V>> pair =
                    Pair.of(result, potentialResult);
            result = compareFunction.apply(pair);
        }
        return result;
    }


    @Override
    public DataIdentifier<K> createDataIdentifier(final K dataFieldName) {
        Optional<K> header = this.headers.stream()
                .filter(h -> Objects.equals(h, dataFieldName))
                .findFirst();
        if (header.isEmpty()) return null;
        return new RowDataIdentifier<>(header.get());
    }

}

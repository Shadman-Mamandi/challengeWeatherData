package de.exxcellent.challenge.csv;

import com.opencsv.CSVReader;
import de.exxcellent.challenge.abstractions.DataStructure;
import de.exxcellent.challenge.abstractions.DataStructureFactory;
import de.exxcellent.challenge.abstractions.FileReader;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CsvFileReader implements FileReader {

    private final DataStructureFactory dataStructureFactory;

    public CsvFileReader(final DataStructureFactory dataStructureFactory) {
        this.dataStructureFactory = dataStructureFactory;
    }

    public DataStructure readFileToDataStructure(final Path filePath) throws Exception {
        List<String[]> content = readAllLines(filePath);
        return dataStructureFactory.produce(content);
    }

    private List<String[]> readAllLines(final Path filePath) throws Exception {
        try (Reader reader = Files.newBufferedReader(filePath)) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                return csvReader.readAll();
            }
        }
    }
}

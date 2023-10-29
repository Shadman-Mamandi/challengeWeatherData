package de.exxcellent.challenge.csv;

import com.opencsv.CSVReader;
import de.exxcellent.challenge.abstractions.DataStructure;
import de.exxcellent.challenge.abstractions.DataStructureFactory;
import de.exxcellent.challenge.abstractions.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;



public record CsvFileReader(DataStructureFactory<String, String> dataStructureFactory)
        implements FileReader<String, String> {

    public DataStructure<String, String> readFileToDataStructure(final Path filePath) throws IOException {
        List<String[]> content = readAllLines(filePath);
        if (content.size() < 1) {
         new RuntimeException("The input file did not contain any data!");
        }
        return dataStructureFactory.produce(
                content.stream().findFirst().get(),
                content.stream().skip(1).toList());
    }

    private List<String[]> readAllLines(final Path filePath) throws IOException {
        try (Reader reader = Files.newBufferedReader(filePath)) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                return csvReader.readAll();
            }
        }
    }
}

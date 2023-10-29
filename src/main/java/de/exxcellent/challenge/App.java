package de.exxcellent.challenge;



import de.exxcellent.challenge.abstractions.DataStructureFactory;
import de.exxcellent.challenge.csv.CsvFileReader;
import de.exxcellent.challenge.datastructure.DataStructureFactoryImpl;
import de.exxcellent.challenge.operators.SpreadFinder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) throws Exception {

        String dayWithSmallestTempSpread = getMinSpread(
                "weather.csv", "MxT", "MnT", "Day"
        );     // Your day analysis function call …
        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);

        String teamWithSmallestGoalSpread = getMinSpread(
                "football.csv", "Goals", "Goals Allowed", "Team"
        ); // Your goal analysis function call …
        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }

    /**
     * Calculates the minimum spread between two columns in a csv file.
     * @param csfFileName The name of the csv files that is loaded from the resources.
     * @param spreadColumn1 The name of one of the columns that is used for calculating the spread.
     * @param spreadColumn2 The name of one of the columns that is used for calculating the spread.
     * @param resultColumn The name of the column that contains the resulting value.
     * @return The content of the resultColumn-cell in the row with the lowest spread between the given spread-columns.
     * @throws IOException When the input file fails to load or the given columns are not found in the file.
     */
    private static String getMinSpread(final String csfFileName,
                                       final String spreadColumn1,
                                       final String spreadColumn2,
                                       final String resultColumn) throws URISyntaxException, IOException {
        // the data structure factory can produce data structures,
        // thus decoupling the file reader from the concrete data structure implementation
        DataStructureFactory<String, String> dataStructureFactory = new DataStructureFactoryImpl<>();

        // the file reader loads csv data.
        // the constructor takes the factory to enable dependency injection / inversion of control.
        var fileReader = new CsvFileReader(dataStructureFactory);

        // get the absolute path of the csv file
        Path csvFilePath = Paths.get(Objects.requireNonNull(App.class.getResource(csfFileName)).toURI());

        // read the csv file and load it into a data structure
        var dataStructure = fileReader.readFileToDataStructure(csvFilePath);

        // create data identifiers that allow finding the cells in a data row of the data structure
        var spreadColumn1Id = dataStructure.createDataIdentifier(spreadColumn1);
        var spreadColumn2Id = dataStructure.createDataIdentifier(spreadColumn2);
        var resultColumnId = dataStructure.createDataIdentifier(resultColumn);

        // the spread finder works on arbitrary data structures
        var spreadFinder = new SpreadFinder<>(dataStructure);

        // find the data row with the minimum spread between the two given columns
        var elementWithMinSpread = spreadFinder.getDataElementByMinSpread(spreadColumn1Id, spreadColumn2Id);

        // return the value of the data cell defined by the given result column
        return elementWithMinSpread.geValue(resultColumnId);
    }
}

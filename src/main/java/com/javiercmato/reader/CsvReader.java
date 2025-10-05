package com.javiercmato.reader;

import com.javiercmato.model.Cell;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;

import static com.javiercmato.model.Sudoku.INVALID_VALUE;

@RequiredArgsConstructor
public class CsvReader {
    private static final String COMMA_DELIMITER = ",";
    private static final String EMPTY_VALUE = "";
    private static final String[] HEADERS = {
            "x",
            "y",
            "value"
    };
    private static int BOARD_COLUMNS, BOARD_ROWS;

    /**
     * Read values from a CSV file and returns an array of {@link Cell}
     *
     * @param fileInput CSV file
     * @return Array of values
     * @throws IOException Could not parse CSV file
     */
    public static Cell[][] readValues(File fileInput, int numRows, int numCols) throws IOException {
        BOARD_COLUMNS = numCols;
        BOARD_ROWS = numRows;

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setDelimiter(COMMA_DELIMITER)
                .setSkipHeaderRecord(true)
                .setNullString(EMPTY_VALUE)
                .build();

        Reader reader = new FileReader(fileInput);
        Iterable<CSVRecord> rows = csvFormat.parse(reader);

        return toCellArray(rows);
    }

    private static Cell[][] toCellArray(Iterable<CSVRecord> records) {
        Cell[][] values = new Cell[BOARD_COLUMNS][BOARD_ROWS];

        for (CSVRecord row : records) {
            int x = Integer.parseInt(row.get(HEADERS[0]));
            int y = Integer.parseInt(row.get(HEADERS[1]));
            String value = row.get(HEADERS[2]);

            try {
                values[x - 1][y - 1] = toCell(value);
            } catch (NumberFormatException e) {
                String message = "Value at position (%d,%d) is not a number: '%s'".formatted(x, y, value);
                System.err.println(message);
                throw e;
            }
        }

        return values;
    }

    private static Cell toCell(String value) {
        int cellValue = (value == null) ? INVALID_VALUE : Integer.parseInt(value);
        return new Cell(cellValue);
    }
}

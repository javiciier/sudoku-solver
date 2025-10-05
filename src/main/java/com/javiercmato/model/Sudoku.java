package com.javiercmato.model;

import com.javiercmato.reader.CsvReader;
import com.javiercmato.utils.BoardUtils;
import lombok.Data;

import java.io.File;
import java.io.IOException;

import static java.lang.Math.max;

@Data
public class Sudoku {
    public static final int INVALID_VALUE = 0;

    /**
     * Range of numbers from 1 to N allowed to put in the cells
     */
    private int valuesRange;
    private Board board;
    private Givens given;

    public Sudoku(File file, int numRows, int numCols) {
        this.valuesRange = max(numRows, numCols);
        Cell[][] cells = readFile(file, valuesRange);
        System.out.println(
                BoardUtils.prettifyCells(cells, "Sudoku")
        );
    }

    private static Cell[][] readFile(File file, int valuesRange) {
        try {
            ClassLoader classLoader = Sudoku.class.getClassLoader();
            file = new File(classLoader.getResource(file.getName()).getFile());
            return CsvReader.readValues(file, valuesRange, valuesRange);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

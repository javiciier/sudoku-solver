package com.javiercmato.model;

import com.javiercmato.utils.BoardUtils;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * Collection of numbers that are already set by default in the Sudoku
 */
@RequiredArgsConstructor
public class Givens {
    private Cell[][] cells;

    public Givens(Cell[][] cells) {
        Arrays.stream(cells)
                .flatMap(Arrays::stream)
                .filter(cell -> !cell.isEmpty())
                .forEach(cell -> cell.setEditable(false));

        this.cells = cells;
    }

    @Override
    public String toString() {
        return BoardUtils.prettifyCells(cells, "Givens");
    }


}

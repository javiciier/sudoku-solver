package com.javiercmato.utils;

import com.javiercmato.model.Cell;

import static com.javiercmato.model.Sudoku.INVALID_VALUE;

public class BoardUtils {
    private static final String EMPTY_CELL_VALUE = " ";

    /**
     * Format the given array of {@link Cell} to be human readable.
     *
     * @param board     Array of cells to print
     * @param boardName Name of the board
     */
    public static String prettifyCells(Cell[][] board, String boardName) {
        StringBuilder sb = new StringBuilder();
        String ROWS_SIZE_PLACEHOLDER = "RS";
        int maxRowSize = 0, maxColSize = 0;
        String COLUMNS_SIZE_PLACEHOLDER = "CS";

        sb
                .append(boardName)
                .append("(").append(ROWS_SIZE_PLACEHOLDER).append("X").append(COLUMNS_SIZE_PLACEHOLDER).append(")")
                .append(": {\n");
        for (Cell[] row : board) {
            maxRowSize = Math.max(maxRowSize, row.length);
            sb.append("\t[ ");
            for (int j = 0; j < row.length; j++) {
                maxColSize = Math.max(maxColSize, j + 1);
                int cellValue = row[j].getValue();
                if (cellValue == INVALID_VALUE) {
                    sb.append(EMPTY_CELL_VALUE);
                } else {
                    sb.append(cellValue);
                }

                if (j < row.length - 1) {
                    sb.append(" | ");
                }
            }
            sb.append(" ]");
            if (row != board[board.length - 1]) {
                sb.append("\n");
            }
        }
        sb.append("\n}");

        sb.replace(sb.indexOf(ROWS_SIZE_PLACEHOLDER), sb.indexOf(ROWS_SIZE_PLACEHOLDER) + ROWS_SIZE_PLACEHOLDER.length(), String.valueOf(maxRowSize));
        sb.replace(sb.indexOf(COLUMNS_SIZE_PLACEHOLDER), sb.indexOf(COLUMNS_SIZE_PLACEHOLDER) + COLUMNS_SIZE_PLACEHOLDER.length(), String.valueOf(maxColSize));
        return sb.toString();
    }
}

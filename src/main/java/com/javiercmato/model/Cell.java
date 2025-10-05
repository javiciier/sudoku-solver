package com.javiercmato.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Cell {
    private final int value;
    @Setter
    private boolean isEditable;

    public Cell(int value) {
        this.value = value;
    }

    public boolean isEmpty() {
        return value == Sudoku.INVALID_VALUE;
    }

    public Cell copyWithValue(int value) {
        Cell clone = new Cell(value);
        clone.setEditable(isEditable);

        return clone;
    }
}

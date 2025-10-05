package com.javiercmato.model;

import com.javiercmato.utils.BoardUtils;
import lombok.Getter;

@Getter
public class Board {
    private Cell[][] cells;
    private Givens givens;

    public Board(int valuesRange, Givens givens) {
        this.givens = givens;
    }


    @Override
    public String toString() {
        return BoardUtils.prettifyCells(cells, "Board");
    }
}

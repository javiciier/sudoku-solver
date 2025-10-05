package com.javiercmato;

import com.javiercmato.model.Sudoku;

import java.io.File;

public class Main {
    static void main() {
        File file = new File("sudoku_3x3.csv");
        Sudoku sudoku = new Sudoku(file, 3, 3);
    }
}

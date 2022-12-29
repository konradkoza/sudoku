package pl.comp.view;

import lodz.p.pk.sudoku.SudokuBoard;

public class SudokuBoardAdapter {
    private SudokuBoard board;

    private int row;

    private int column;

    public SudokuBoardAdapter(SudokuBoard board, int row, int column) {
        this.board = board;
        this.row = row;
        this.column = column;
    }

    public void setField(int value){
        board.setField(row, column, value);

    }

    public int getField(){
        return board.getField(row, column);

    }
}

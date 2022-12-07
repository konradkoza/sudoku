package pl.comp.view;


import java.util.*;

import lodz.p.pk.sudoku.SudokuBoard;

public enum DifficultyLevel {
    
    EASY(30),
    MEDIUM(45),
    HARD(60);

    private int numberFields;
    private List<Integer> numbersList = new ArrayList<Integer>();

    DifficultyLevel(int numberFields) {
        this.numberFields = numberFields;
    }

    private void numbersToRemove() {
        Random rng = new Random();
        for (int i = 0; i < numberFields; i++) {
            boolean isAdded = false;
            while (!isAdded) {
                int random = rng.nextInt(81);
                isAdded = numbersList.contains(random);
                if(!isAdded) {
                    numbersList.set(i, random);
                }

            }
            
        }
    }

    public void deleteFields(SudokuBoard board){
        for (int i = 0; i < numberFields; i++) {
            board.setField(numbersList.get(i) % 9, (int)(numbersList.get(i) / 9), 0);
        }
    }


}

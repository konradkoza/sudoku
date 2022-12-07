package pl.comp.view;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import lodz.p.pk.sudoku.SudokuBoard;

public enum DifficultyLevel {
    
    EASY(30),
    MEDIUM(45),
    HARD(60);

    private int numberFields;

    private Set<Integer> numbers = new HashSet<>();

    DifficultyLevel(int numberFields) {
        this.numberFields = numberFields;
    }

    private void numbersToRemove() {
        Random rng = new Random();
        for (int i = 0; i < numberFields; i++) {
            boolean isAdded = false;
            while (!isAdded) {
                int random = rng.nextInt(81);
                isAdded = numbers.add(random);
            }
            
        }
    }

    public void deleteFields(SudokuBoard board) {
        for (int i = 0; i < numberFields; i++) {
            board.setField(i % 9, (int)(i / 9), 0);
        }
    }


}

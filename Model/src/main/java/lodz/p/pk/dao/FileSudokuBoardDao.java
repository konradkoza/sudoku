package lodz.p.pk.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import lodz.p.pk.sudoku.SudokuBoard;




public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private String fileName;

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public SudokuBoard read() {
        SudokuBoard result = null;
        try (FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            result = (SudokuBoard) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            //            System.out.println("Trouble reading from the file: " + e.getMessage());
            throw new RuntimeException(e);

        }

        return result;
    }

    @Override
    public void write(SudokuBoard obj) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
        } catch (IOException e) {
            //            System.out.println("Trouble writing to the file: " + e.getMessage());
            throw new RuntimeException(e);

        }
    }

    @Override
    public void close(){
    }
}
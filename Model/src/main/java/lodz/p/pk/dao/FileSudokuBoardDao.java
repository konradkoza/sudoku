package lodz.p.pk.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ResourceBundle;
import lodz.p.pk.exceptions.ReadDaoException;
import lodz.p.pk.exceptions.WriteDaoException;
import lodz.p.pk.sudoku.SudokuBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    private static Logger logger = LoggerFactory.getLogger(FileSudokuBoardDao.class);

    ResourceBundle bundle = ResourceBundle.getBundle("lodz.p.pl.LangBundle");

    private String fileName;

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public SudokuBoard read() throws ReadDaoException {
        SudokuBoard result = null;
        try (FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            result = (SudokuBoard) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            //            System.out.println("Trouble reading from the file: " + e.getMessage());
            logger.info(bundle.getString("ReadException"));
            throw new ReadDaoException(e);

        }

        return result;
    }

    @Override
    public void write(SudokuBoard obj) throws WriteDaoException {
        try (FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
        } catch (IOException e) {
            //            System.out.println("Trouble writing to the file: " + e.getMessage());
            logger.info(bundle.getString("WriteException"));
            throw new WriteDaoException(e);

        }
    }

    @Override
    public void close(){
        
    }
}
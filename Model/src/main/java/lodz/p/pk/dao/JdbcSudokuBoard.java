package lodz.p.pk.dao;

import lodz.p.pk.exceptions.ReadDaoException;
import lodz.p.pk.exceptions.WriteDaoException;
import lodz.p.pk.sudoku.SudokuBoard;

public class JdbcSudokuBoard implements Dao<SudokuBoard>, AutoCloseable {
    @Override
    public SudokuBoard read() throws ReadDaoException {
        return null;
    }

    @Override
    public void write(SudokuBoard obj) throws WriteDaoException {

    }
    @Override
    public void close() throws Exception {

    }

    @Override
    protected void finalize() {

    }
}

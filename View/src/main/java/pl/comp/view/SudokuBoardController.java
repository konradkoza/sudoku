package pl.comp.view;

import java.util.ResourceBundle;
import javafx.beans.property.adapter.JavaBeanIntegerProperty;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.StringConverter;
import lodz.p.pk.dao.Dao;
import lodz.p.pk.dao.SudokuBoardDaoFactory;
import lodz.p.pk.sudoku.BacktrackingSudokuSolver;
import lodz.p.pk.sudoku.SudokuBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.comp.view.exceptions.InsertNumberException;
import pl.comp.view.exceptions.SaveFileException;



public class SudokuBoardController {

    ResourceBundle bundle = ResourceBundle.getBundle("pl.comp.view.LangBundle");

    private static Logger logger = LoggerFactory.getLogger(SudokuBoardController.class);

    @FXML
    private TextField fileText;
    @FXML
    private Button showButton;

    @FXML
    private GridPane sudokuGrid;

    @FXML
    private Button saveButton;

    private TextField[][] sudokuNumbers = new TextField[9][9];

    private BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();

    private SudokuBoard board;

    private final JavaBeanIntegerProperty[][] fieldValueProperty =
            new JavaBeanIntegerProperty[9][9];

    private final StringConverter converter = new Converter();

    //    public void setBoard(SudokuBoard board) {
    //        this.board = board;
    //    }

    public void printBoard() {
        solver.fillBoard(board);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                logger.info(board.getField(i,j) + "  ");
                fieldValueProperty[i][j].set(board.getField(i,j));
            }
            logger.info("\n");
        }

    }


    public void initTextFields(SudokuBoard board) throws InsertNumberException {
        this.board = board;
        JavaBeanIntegerPropertyBuilder builder = JavaBeanIntegerPropertyBuilder.create();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField newNumber = new TextField();
                newNumber.setPrefWidth(60);
                newNumber.setPrefHeight(60);
                newNumber.alignmentProperty().set(Pos.CENTER);
                newNumber.setFont(Font.font("System Regular", FontWeight.BOLD, 12));

                try {
                    fieldValueProperty[i][j] = builder
                            .bean(new SudokuBoardAdapter(this.board, i, j))
                            .name("Field")
                            .build();
                } catch (NoSuchMethodException e) {
                    logger.info(bundle.getString("NumberNotInserted"));
                    throw new InsertNumberException(e);
                }

                newNumber.textProperty().bindBidirectional(fieldValueProperty[i][j], converter);
                newNumber.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable,
                                        String oldValue, String newValue) {
                        if (!(newValue.matches("[1-9]") || newValue.equals(""))) {
                            newNumber.setText(oldValue);

                        }
                    }
                });

                //                if(newNumber.getText().matches("[1-9]")) {
                //                    newNumber.setFont(Font.font("System Regular",
                //                    FontWeight.LIGHT, 12));
                //                    Paint bgColor = Paint.valueOf("EEEEEE");
                //                    newNumber.setStyle("-fx-control-inner-background: #"+bgColor
                //                    .toString().substring(2));
                //                    newNumber.setEditable(false);
                //                }
                if (board.getField(i, j) == 0) {
                    newNumber.clear();
                }
                sudokuGrid.add(newNumber, j, i);

            }
        }
    }

    @FXML
    public void saveToFile(ActionEvent event) throws SaveFileException {
        String fileName = fileText.getText();
        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getFileDao(fileName)) {
            dao.write(board);
        } catch (Exception e) {
            logger.info(bundle.getString("FileNotSaved"));
            throw new SaveFileException(e);
        }
    }

    public void initialize() {


    //        board.solveGame();
    //        diffLevel.deleteFields(board);
    //        for (int i = 0; i < 9; i++) {
    //            for (int j = 0; j < 9; j++) {
    //                System.out.print(board.getField(i,j));
    //            }
    //            System.out.print("\n");
    //        }

    //        initTextFields(board);
    }
}

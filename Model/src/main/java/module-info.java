module pl.comp {
    requires java.desktop;
    requires com.google.common;

    opens lodz.p.pk.dao;
    opens lodz.p.pk.sudoku;

    exports lodz.p.pk.sudoku;
}
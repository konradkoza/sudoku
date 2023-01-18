module pl.comp {
    requires java.desktop;
    requires com.google.common;
    requires slf4j.api;
    requires java.sql;

    opens lodz.p.pk.dao;
    opens lodz.p.pk.sudoku;

    exports lodz.p.pk.sudoku;
    exports lodz.p.pk.dao;
}
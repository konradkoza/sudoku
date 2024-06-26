module pl.comp.viewproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires pl.comp;
    requires slf4j.api;

    opens pl.comp.view to javafx.fxml;
    exports pl.comp.view;
    exports pl.comp.view.exceptions;
    opens pl.comp.view.exceptions to javafx.fxml;
}
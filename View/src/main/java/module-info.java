module pl.comp.viewproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires pl.comp;

    opens pl.comp.view to javafx.fxml;
    exports pl.comp.view;
}
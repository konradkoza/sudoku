package pl.comp.view;

import javafx.util.StringConverter;

public class Converter extends StringConverter<Integer> {
    @Override
    public String toString(Integer object) {
        return object.toString();
    }

    @Override
    public Integer fromString(String string) {
        if (string.matches("[1-9]")) {
            return Integer.valueOf(string);
        } else {
            return 0;
        }
    }
}

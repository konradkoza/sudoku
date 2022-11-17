package lodz.p.pk.sudoku;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Observer implements PropertyChangeListener {

    private boolean isCorrect = false;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.setCorrect((boolean) evt.getNewValue());
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}

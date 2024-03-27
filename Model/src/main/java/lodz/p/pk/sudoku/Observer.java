package lodz.p.pk.sudoku;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

public class Observer implements PropertyChangeListener, Serializable {

    private boolean isCorrect = false;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("property changedz");
        this.setCorrect((boolean) evt.getNewValue());
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}

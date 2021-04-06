package oop.focus.finance.view;

import oop.focus.common.View;
import oop.focus.homepage.model.Person;

public interface PersonView extends View {

    /**
     * @return the person referenced by the view
     */
    Person getPerson();
}

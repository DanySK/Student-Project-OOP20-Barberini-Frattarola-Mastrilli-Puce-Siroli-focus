package oop.focus.application.controller;

import javafx.util.Pair;
import oop.focus.common.Controller;

import java.util.List;

/**
 * The interface manages different sections of the app.
 */
public interface Sections {
    /**
     * Returns the {@link Controller} of the first section to show when app starts.
     * @return  the Controller of the first section to show
     */
    Controller getStarterController();

    /**
     * Returns a List of {@link Pair}. For each of which, the key is the Controller, while the value is the
     * name of a specific section.
     * @return  a list of Controllers and names of all sections.
     */
    List<Pair<Controller, String>> getList();
}

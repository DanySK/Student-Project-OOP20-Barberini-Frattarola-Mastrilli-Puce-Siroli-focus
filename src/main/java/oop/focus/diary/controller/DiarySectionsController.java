package oop.focus.diary.controller;

import javafx.scene.Node;
import oop.focus.common.Controller;

/**
 * The interface returns three Nodes, which represent the different sections of diary's sections(diary, stopwatch and
 * timer).
 */
public interface DiarySectionsController extends Controller {
    /**
     * Returns the root of diary's section.
     * @return  the root of diary's section
     */
    Node getDiary();

    /**
     * Return the root of stopwatch's section.
     * @return  the root of stopwatch's section
     */
    Node getStopwatch();

    /**
     * Return the root of timer's section.
     * @return  the root of timer's section
     */
    Node getTimer();
    Node getMoodCalendar();
}

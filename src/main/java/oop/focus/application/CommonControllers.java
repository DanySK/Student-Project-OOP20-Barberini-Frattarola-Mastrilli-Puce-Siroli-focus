package oop.focus.application;

import javafx.scene.Node;

/**
 * This interface has methods that return different Nodes, each of one stage a different sections of application:
 * Diary, Finance, HomePage and Calendar.
 */
public interface CommonControllers {
    /**
     * @return  the root of diary's section
     */
    Node getDiary();

    /**
     * @return  the root of finance's section
     */
    Node getFinance();

    /**
     * @return  the root of calendar's section
     */
    Node getCalendar();
}

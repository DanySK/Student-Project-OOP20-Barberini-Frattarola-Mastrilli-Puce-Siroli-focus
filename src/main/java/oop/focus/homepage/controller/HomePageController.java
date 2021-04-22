package oop.focus.homepage.controller;

import javafx.collections.ObservableList;
import oop.focus.common.Controller;
import oop.focus.common.Repetition;
import oop.focus.db.DataSourceImpl;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.HotKey;
import oop.focus.homepage.model.HotKeyImpl;

public interface HomePageController extends Controller {

    /**
     * This method is used to delete a selected hot key from the database.
     * @param hotKeyImpl is the hot key to delete.
     */
    void deleteHotKey(HotKeyImpl hotKeyImpl);

    /**
     * This method is used to know if an activity hot key was selected.
     * @param hotKeyName is the name of the activity hot key.
     * @return true if the activity was never selected false otherwise.
     */
    boolean getActivitySelected(String hotKeyName);

    /**
     * This method is used to get the times that a counter hot key was clicked.
     * @param hotKey is the hot key of which to count the times it has been clicked.
     * @return a string represents the number of clicks.
     */
    String getClickTime(HotKey hotKey);

    /**
     * This method is used to get the dsi.
     * @return the data source.
     */
    DataSourceImpl getDsi();

    /**
     * This method is used to get an ObservableList of event.
     * @return the list of the event generated from hot key click.
     */
    ObservableList<Event> getEvents();

    /**
     * This method is used to get an observable list of hot keys.
     * @return the list of the hot keys.
     */
    ObservableList<HotKey> getHotKey();

    /**
     * This method is used to set the label of the new event view window.
     * @return a string.
     */
    String getText();

    /**
     * This method is used to generate events that recur to date.
     */
    void refreshDailyEvents();

    /**
     * This method is used to save a specific event.
     * @param eventImpl is the event to save.
     */
    void saveEvent(Event eventImpl);

    /**
     * This method is used to save a specific hot key.
     * @param hotKey is the hot key to save.
     */
    void saveHotKey(HotKey hotKey);

    /**
     * This method is use to save the name of the event.
     * @param text is the name of the event.
     */
    void setText(String text);

    /**
     * This method is used to get all the repetition to fill the combo box for add a new event.
     * @return ObservableList that contains all the repetitions name.
     */
    ObservableList<Repetition> getRep();
}

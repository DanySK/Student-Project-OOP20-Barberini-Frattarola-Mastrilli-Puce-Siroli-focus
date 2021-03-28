package oop.focus.diary.view;

import javafx.scene.control.Accordion;

/**
 * The interface has method to set the view of all diary's page inside the apposite container, and update the
 * view if a page is saved/deleted or modified.
 */
public interface PagesView {
    /**
     * Return an accordion formed by all saved pages of diary.
     * @return  an Accordion of pages
     */
    Accordion getAccordion();
}

package oop.focus.calendarhomepage.view;

public interface NewEventView extends GenericAddView {

    /**
     * This method is use to set the text of the label that represent the name of the event.
     * @param eventName is the name of the event.
     */
    void setText(String eventName);

}

package oop.focus.event.controller;

import oop.focus.common.View;
import oop.focus.homepage.model.Event;

public interface EventInformationController {

    View getView();

    Event getEvent();

    void stopRepetition();
}

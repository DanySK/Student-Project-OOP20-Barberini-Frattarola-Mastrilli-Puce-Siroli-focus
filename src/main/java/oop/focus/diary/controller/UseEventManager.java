package oop.focus.diary.controller;

import oop.focus.db.DataSourceImpl;
import oop.focus.homepage.model.EventManagerImpl;

public final class UseEventManager {
    private static final EventManagerImpl EVENT_MANAGER = new EventManagerImpl(new DataSourceImpl());
    private UseEventManager() {
    }
    public static EventManagerImpl getEventManager() {
        return EVENT_MANAGER;
    }
}

package oop.focus.application;

import oop.focus.db.DataSourceImpl;
import oop.focus.homepage.model.EventManagerImpl;

public class UseCommonController {
    private static final DataSourceImpl DATA_SOURCE = new DataSourceImpl();
    private static final EventManagerImpl EVENT_MANAGER = new EventManagerImpl(DATA_SOURCE);
    private UseCommonController() { }

    public static DataSourceImpl getDataSource() {
        return DATA_SOURCE;
    }

    public static EventManagerImpl getEventManager() {
        return EVENT_MANAGER;
    }
}

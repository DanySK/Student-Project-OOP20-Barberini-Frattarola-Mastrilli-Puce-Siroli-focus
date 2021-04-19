package oop.focus.event.controller;


public enum FXMLPaths {

    /**
     * 
     */
    EVENTMENU(Constants.EVENT + "eventView.fxml"),
    /**
     * 
     */
    EVENTINFORMATION(Constants.EVENT + "viewEventStopped.fxml");

    private final String path;

    FXMLPaths(final String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    private static class Constants {
        public static final String EVENT = "/layouts/week/";
    }
}

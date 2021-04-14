package oop.focus.calendar.week.controller;

public enum FXMLPaths {

   /**
     * 
     */
    WEEK(Constants.WEEK + "pane.fxml"),
    /**
     * 
     */
    ADDNEWEVENT(Constants.WEEK + "addNewEventWeek.fxml");

    private final String path;

    FXMLPaths(final String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    private static class Constants {
        public static final String WEEK = "/layouts/week/";
    }
}

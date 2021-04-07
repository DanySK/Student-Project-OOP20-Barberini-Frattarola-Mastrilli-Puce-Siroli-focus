package oop.focus.diary.controller;

public enum FXMLPaths {

    /**
     *
     */
    STOPWATCH(Costants.DIARY + "stopwatchScheme.fxml"),
    /**
     *
     */
    TIMER(Costants.DIARY + "timerScheme.fxml"),
    /**
     *
     */
    ADD_EVENT_NAME_COUNTER(Costants.DIARY + "newCounterNameWindow.fxml");
    private final String path;

    FXMLPaths(final String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    private static class Costants {

        public static final String DIARY = "/layouts/diary/";
       // public static final String BASES = FINANCE + "bases/";
       // public static final String TILES = FINANCE + "tiles/";
       // public static final String WINDOWS = FINANCE + "windows/";

    }

}

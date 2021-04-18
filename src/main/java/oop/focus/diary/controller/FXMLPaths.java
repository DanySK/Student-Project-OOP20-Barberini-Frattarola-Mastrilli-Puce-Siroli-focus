package oop.focus.diary.controller;

/**
 * The enum lists all FXML file used by under-sections of diary.
 */
public enum FXMLPaths {

    /**
     *
     */
    ADD_EVENT_NAME_COUNTER(Costants.DIARY + "newCounterNameWindow.fxml"),
    /**
     *
     */
    INSERT_TIMER_TIME(Costants.DIARY + "insertTimeWindow.fxml"),
    /**
     *
     */
    INSERT_DIARY_PAGE(Costants.DIARY + "windowAddPage.fxml"),
    /**
     *
     */
    INSERT_TDL_ANNOTATION(Costants.DIARY + "windowAddAnnotation.fxml"),
    /**
     *
     */
    REMOVE_TDL_ANNOTATION(Costants.DIARY + "windowRemoveAnnotation.fxml"),
    /**
     *
     */
    DIARY_SCHEME(Costants.DIARY + "diaryScheme.fxml"),
    /**
     *
     */
    TDL_SCHEME(Costants.DIARY + "TDLScheme.fxml");
    private final String path;

    FXMLPaths(final String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    private static class Costants {

        public static final String DIARY = "/layouts/diary/";
       // public static final String TILES = FINANCE + "tiles/";
       // public static final String WINDOWS = FINANCE + "windows/";

    }

}

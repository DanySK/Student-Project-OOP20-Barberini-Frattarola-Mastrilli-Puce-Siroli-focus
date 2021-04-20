package oop.focus.diary.controller;

/**
 * Lists all FXML file used by under-sections of diary.
 */
public enum FXMLPaths {

    /**
     *
     */
    ADD_EVENT_NAME_COUNTER(Constants.DIARY + "newCounterNameWindow.fxml"),
    /**
     *
     */
    INSERT_TIMER_TIME(Constants.DIARY + "insertTimeWindow.fxml"),
    /**
     *
     */
    INSERT_DIARY_PAGE(Constants.DIARY + "windowAddPage.fxml"),
    /**
     *
     */
    INSERT_TDL_ANNOTATION(Constants.DIARY + "windowAddAnnotation.fxml"),
    /**
     *
     */
    REMOVE_TDL_ANNOTATION(Constants.DIARY + "windowRemoveAnnotation.fxml"),
    /**
     *
     */
    DIARY_SCHEME(Constants.DIARY + "diaryScheme.fxml"),
    /**
     *
     */
    TDL_SCHEME(Constants.DIARY + "TDLScheme.fxml");
    private final String path;

    FXMLPaths(final String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    private static class Constants {
        public static final String DIARY = "/layouts/diary/";
    }

}

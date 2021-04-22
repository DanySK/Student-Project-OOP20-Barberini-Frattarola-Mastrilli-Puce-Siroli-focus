package oop.focus.application.controller;

/**
 * Has paths of all application's styles.
 */
public enum Style {
    /**
     *
     */
    STATISTIC_STYLE(Constants.STYLES + "statistics.css"),
    /**
     *
     */
    GENERAL_STYLE(Constants.STYLES + "generalStyle.css"),
    /**
     *
     */
    CALENDAR_STYLE(Constants.STYLES + "Calendar.css"),
    /**
     *
     */
    DIARY_STYLE(Constants.STYLES + "diaryStyle.css"),
    /**
     *
     */
    FINANCE_STYLE(Constants.STYLES + "financeStyle.css");
    private final String path;

    Style(final String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    private static class Constants {

        public static final String STYLES = "/styles/";
    }
}

package oop.focus.application.controller;

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
    CALENDAR_STYLE(Constants.STYLES + "Calendar.css");
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

package oop.focus.application;

public enum Style {
    /**
     *
     */
    STATISTIC_STYLE(Style.Costants.STYLES + "statistics.css");

    private final String path;

    Style(final String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    private static class Costants {

        public static final String STYLES = "/styles/";
    }
}

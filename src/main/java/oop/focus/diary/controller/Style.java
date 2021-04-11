package oop.focus.diary.controller;

public enum Style {
    /**
     *
     */
    DIARY_STYLE(Costants.DIARY + "diaryStyle.css"),
    /**
     *
     */
    STOPWATCH_STYLE(Costants.DIARY + "stopwatchStyle.css"),
    /**
     *
     */
    TIMER_STYLE(Costants.DIARY + "timerStyle.css");
    private final String path;

    Style(final String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    private static class Costants {

        public static final String DIARY = "/layouts/diary/";
    }
}

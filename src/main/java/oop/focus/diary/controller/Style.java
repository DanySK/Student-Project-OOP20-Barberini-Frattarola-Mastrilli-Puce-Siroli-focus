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
    TIMER_STYLE(Costants.DIARY + "timerStyle.css"),
    /**
     *
     */
    MOOD_CALENDAR_STYLE(Costants.DIARY + "moodCalendarStyle.css");
    private final String path;

    Style(final String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    private static class Costants {

        public static final String DIARY = "/styles/";
    }
}

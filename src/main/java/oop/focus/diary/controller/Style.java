package oop.focus.diary.controller;

public enum Style {
    /**
     *
     */
    DIARY_STYLE(Costants.DIARY + "diaryStyle.css"),
    /**
     *
     */
    COUNTER_STYLE(Costants.DIARY + "counterStyle.css");
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

package oop.focus.diary.controller;


public enum DiarySections {
    /**
     *
     */
    DIARY("Diario", Style.DIARY_STYLE.getPath()),
    /**
     *
     */
    STOPWATCH("Cronometro", Style.STOPWATCH_STYLE.getPath()),
    /**
     *
     */
    TIMER("Timer", Style.TIMER_STYLE.getPath());
    private final String name;
    private final String style;

    DiarySections(final String name, final String style) {
        this.name = name;
        this.style = style;
    }
    public String getName() {
        return this.name;
    }

    public String getStyle() {
        return this.style;
    }


}



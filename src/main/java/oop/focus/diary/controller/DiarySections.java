package oop.focus.diary.controller;


public enum DiarySections {
    /**
     *
     */
    DIARY("Diario", Style.DIARY_STYLE.getPath()),
    /**
     *
     */
    STOPWATCH("Cronometro", Style.COUNTER_STYLE.getPath()),
    /**
     *
     */
    TIMER("Timer", "");
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



package oop.focus.calendar.model;

public enum Format {

    /**
     * Normal format day.
     */
    NORMAL(24, "orario"),

    /**
     * Extended format day.
     */
    EXTENDED(48, "ogni mezz'ora");

    private int number;
    private String name;

    Format(final int i, final String string) {
        number = i;
        name = string;
    }


    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }


}

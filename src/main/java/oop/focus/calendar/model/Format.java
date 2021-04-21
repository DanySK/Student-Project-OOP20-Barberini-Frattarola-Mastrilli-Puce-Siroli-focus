package oop.focus.calendar.model;

/**
 * Enum that models a the Format of the day.
 * an Format is composed by the number of the hours and the name.
 * number of the hours : Normal, Extended
 * Normal: 24 hours (hour to hour) and the name
 * Extended : 48 hours ( hour to hour with half hour in the middle) and is name
 */
public enum Format {

    /**
     * Normal format day.
     */
    NORMAL(24, "orario"),

    /**
     * Extended format day.
     */
    EXTENDED(48, "ogni mezz'ora");

    private final int number;
    private final String name;

    Format(final int i, final String string) {
        this.number = i;
        this.name = string;
    }


    public int getNumber() {
        return this.number;
    }

    public String getName() {
        return this.name;
    }


}

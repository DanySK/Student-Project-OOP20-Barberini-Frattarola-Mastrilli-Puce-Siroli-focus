package oop.focus.calendar.model;

public enum Format {

    /**
     * Normal format day.
     */
    NORMAL(24),

    /**
     * Extended format day.
     */
    EXTENDED(48);

    private int number;

    Format(final int i) {
        number = i;
    }


    public int getNumber() {
        return number;
    }


}

package oop.focus.common.generalGraphic;

public enum Sections {
    HOMEPAGE("HomePage", "bg-1"),
    FINANCE("Finanze", ""),
    DIARY("Diario", ""),
    CALENDAR("Calendario", "");
    private final String name;
    private final String style;
    Sections(String name, String style) {
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

package oop.focus.common.generalGraphic;

public enum Sections {
    /**
     * HomePage's section.
     */
    HOMEPAGE("HomePage", "bg-1"),
    /**
     * Finance's section.
     */
    FINANCE("Finanze", ""),
    /**
     * Diary's section.
     */
    DIARY("Diario", ""),
    /**
     * Calendar's section.
     */
    CALENDAR("Calendario", "");
    private final String name;
    private final String style;
    Sections(final String name, final String style) {
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

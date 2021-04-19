package oop.focus.calendar.model;



/**
 * Enum that models a the Calendar Type of the Month Calendar.
 * an Calendar Type is composed by 3 type of calendar month.
 * NORMAL : It is made up of buttons that, when clicked, open a window with the information of the day
 * HOMEPAGE : Is composed only with label with the number of the day.
 * DIARY : Is composed only with label with the number of the day and an icon that represent you daily humor.
 */
public enum CalendarType {

    /**
     * Normal Calendar.
     */
    NORMAL,

    /**
     * Homepage Calendar.
     */
    HOMEPAGE,

    /**
     * Diary Calendar.
     */
    DIARY

}

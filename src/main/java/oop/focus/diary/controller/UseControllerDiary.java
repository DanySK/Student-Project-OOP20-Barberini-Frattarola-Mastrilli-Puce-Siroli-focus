package oop.focus.diary.controller;

public final class UseControllerDiary {
    private static final PagesDiaryImpl CONTROLLER_DIARY = new PagesDiaryImpl();

    private UseControllerDiary() { }

    public static PagesDiaryImpl getCF() {
        return CONTROLLER_DIARY;
    }

}


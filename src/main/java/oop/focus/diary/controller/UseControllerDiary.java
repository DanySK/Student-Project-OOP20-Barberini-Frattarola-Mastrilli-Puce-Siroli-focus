package oop.focus.diary.controller;

public final class UseControllerDiary {
    private static final DiaryPagesImpl CONTROLLER_DIARY = new DiaryPagesImpl();

    private UseControllerDiary() { }

    public static DiaryPagesImpl getCF() {
        return CONTROLLER_DIARY;
    }

}


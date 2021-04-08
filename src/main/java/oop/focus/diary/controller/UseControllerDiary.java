package oop.focus.diary.controller;

/**
 * This class uses Singleton's pattern and creates the controller of diary.
 */
public final class UseControllerDiary {
    private static final DiaryPagesImpl CONTROLLER_DIARY = new DiaryPagesImpl();

    private UseControllerDiary() { }

    public static DiaryPagesImpl getCF() {
        return CONTROLLER_DIARY;
    }

}


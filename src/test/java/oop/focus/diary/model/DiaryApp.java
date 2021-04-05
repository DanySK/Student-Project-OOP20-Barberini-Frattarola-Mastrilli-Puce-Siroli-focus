package oop.focus.diary.model;

import oop.focus.diary.controller.LauncherDiary;
import oop.focus.diary.controller.LauncherStopwatch;
import oop.focus.diary.controller.LauncherTimer;

public final class DiaryApp {
    private DiaryApp() {}
    public static void main(final String... args) {
        //LauncherDiary.main(args);
        LauncherStopwatch.main(args);
       //LauncherTimer.main(args);
    }
}

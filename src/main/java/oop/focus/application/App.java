package oop.focus.application;


import oop.focus.homepage.controller.HomePageLauncher;
import oop.focus.homepage.controller.PersonLauncher;
import oop.focus.homepage.controller.WeekLauncher;

public final  class App {
    private App() {
}
    public static void main(final String... args) {

        //LauncherAll.main(args);
        //PersonLauncher.main(args);
        //HomePageLauncher.main(args);
        WeekLauncher.main(args);
    }
}

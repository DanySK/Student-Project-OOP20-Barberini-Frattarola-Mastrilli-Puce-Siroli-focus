package oop.focus.finance;

import oop.focus.calendarhomepage.controller.HomePageLauncher;
import oop.focus.event.controller.EventLauncher;
import oop.focus.finance.view.launchers.FinanceHomePageLauncher;
import oop.focus.finance.view.launchers.FinanceLauncher;

public final  class App {

    // private constructor to avoid unnecessary instantiation of the class
    private App() {
    }
    
    public static void main(final String... args) {
        //FinanceLauncher.main(args);
        FinanceHomePageLauncher.main(args);
    }
}
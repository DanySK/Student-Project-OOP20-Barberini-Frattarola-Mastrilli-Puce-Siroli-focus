package oop.focus.finance;

import oop.focus.finance.view.FinanceLauncher;

public final  class App {

    // private constructor to avoid unnecessary instantiation of the class
    private App() {
    }
    
    public static void main(final String... args) {
        FinanceLauncher.main(args);
    }
}
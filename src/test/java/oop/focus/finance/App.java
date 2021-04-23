package oop.focus.finance;

// import oop.focus.finance.view.launchers.FinanceHomePageLauncher;
import oop.focus.finance.view.launchers.FinanceLauncher;

/**
 * Class that opens the interface of the finance section or the relative home page to be able
 * to test what concerns the graphic part.
 */
public final  class App {

    // private constructor to avoid unnecessary instantiation of the class
    private App() {
    }
    
    public static void main(final String... args) {
        FinanceLauncher.main(args);
        // FinanceHomePageLauncher.main(args);
    }
}
package oop.focus.finance.controller;

public enum FXMLPaths {

    /**
     *
     */
    MAIN(Costants.BASES + "financeBase.fxml"),
    /**
     *
     */
    ALL(Costants.BASES + "allTransactionsBase.fxml"),
    /**
     *
     */
    GROUP(Costants.BASES + "groupTransactionBase.fxml"),
    /**
     *
     */
    SUBS(Costants.BASES + "subscriptionsBase.fxml"),
    /**
     *
     */
    GENERICTILE(Costants.TILES + "genericTile.fxml"),
    /**
     *
     */
    GROUPTILE(Costants.TILES + "groupTransactionTile.fxml"),
    /**
     *
     */
    MOVTILE(Costants.TILES + "movementTile.fxml"),
    /**
     *
     */
    NEWACCOUNT(Costants.WINDOWS + "newAccount.fxml"),
    /**
     *
     */
    NEWGROUPMOV(Costants.WINDOWS + "newGroupTransaction.fxml"),
    /**
     *
     */
    NEWPERSON(Costants.WINDOWS + "newPerson.fxml"),
    /**
     *
     */
    RESOLVE(Costants.WINDOWS + "resolve.fxml");

    private final String path;

    FXMLPaths(final String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    private static class Costants {

        public static final String FINANCE = "/layouts/finances/";
        public static final String BASES = FINANCE + "bases/";
        public static final String TILES = FINANCE + "tiles/";
        public static final String WINDOWS = FINANCE + "windows/";

    }

}

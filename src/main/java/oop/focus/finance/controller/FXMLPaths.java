package oop.focus.finance.controller;

public enum FXMLPaths {

    /**
     * Path to reach the base fxml file.
     */
    MAIN(Costants.BASES + "financeBase.fxml"),
    /**
     * Path to reach the transactions base fxml file.
     */
    ALL(Costants.BASES + "allTransactionsBase.fxml"),
    /**
     * Path to reach the group base fxml file.
     */
    GROUP(Costants.BASES + "groupTransactionBase.fxml"),
    /**
     * Path to reach the subscriptions base fxml file.
     */
    SUBS(Costants.BASES + "subscriptionsBase.fxml"),
    /**
     * Path to reach the finance home page base fxml file.
     */
    HOMEPAGE(Costants.BASES + "homePageBase.fxml"),
    /**
     * Path to reach the generic tile fxml file.
     */
    GENERICTILE(Costants.TILES + "genericTile.fxml"),
    /**
     * Path to reach the transaction tile fxml file.
     */
    MOVTILE(Costants.TILES + "movementTile.fxml"),
    /**
     * Path to reach the new account window fxml file.
     */
    NEWACCOUNT(Costants.WINDOWS + "newAccount.fxml"),
    /**
     * Path to reach the new group transaction window fxml file.
     */
    NEWGROUPMOV(Costants.WINDOWS + "newGroupTransaction.fxml"),
    /**
     * Path to reach the add person window fxml file.
     */
    ADDPERSON(Costants.WINDOWS + "addPerson.fxml"),
    /**
     * Path to reach the resolve credits window fxml file.
     */
    RESOLVE(Costants.WINDOWS + "resolve.fxml"),
    /**
     * Path to reach the transaction details window fxml file.
     */
    TRANSACTIONDETAILS(Costants.WINDOWS + "transactionDetails.fxml"),
    /**
     * Path to reach the new transaction window fxml file.
     */
    NEWMOVEMENT(Costants.WINDOWS + "newMovement.fxml");

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

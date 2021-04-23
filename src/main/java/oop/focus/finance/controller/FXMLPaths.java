package oop.focus.finance.controller;

public enum FXMLPaths {

    /**
     * Path to reach the base fxml file.
     */
    MAIN(Constants.BASES + "financeBase.fxml"),
    /**
     * Path to reach the transactions base fxml file.
     */
    ALL(Constants.BASES + "allTransactionsBase.fxml"),
    /**
     * Path to reach the group base fxml file.
     */
    GROUP(Constants.BASES + "groupTransactionBase.fxml"),
    /**
     * Path to reach the subscriptions base fxml file.
     */
    SUBS(Constants.BASES + "subscriptionsBase.fxml"),
    /**
     * Path to reach the finance home page base fxml file.
     */
    HOMEPAGE(Constants.BASES + "homePageBase.fxml"),
    /**
     * Path to reach the generic tile fxml file.
     */
    GENERICTILE(Constants.TILES + "genericTile.fxml"),
    /**
     * Path to reach the transaction tile fxml file.
     */
    MOVTILE(Constants.TILES + "movementTile.fxml"),
    /**
     * Path to reach the new account window fxml file.
     */
    NEWACCOUNT(Constants.WINDOWS + "newAccount.fxml"),
    /**
     * Path to reach the new group transaction window fxml file.
     */
    NEWGROUPMOV(Constants.WINDOWS + "newGroupTransaction.fxml"),
    /**
     * Path to reach the add person window fxml file.
     */
    ADDPERSON(Constants.WINDOWS + "addPerson.fxml"),
    /**
     * Path to reach the resolve credits window fxml file.
     */
    RESOLVE(Constants.WINDOWS + "resolve.fxml"),
    /**
     * Path to reach the transaction details window fxml file.
     */
    TRANSACTIONDETAILS(Constants.WINDOWS + "transactionDetails.fxml"),
    /**
     * Path to reach the new transaction window fxml file.
     */
    NEWMOVEMENT(Constants.WINDOWS + "newMovement.fxml");

    private final String path;

    FXMLPaths(final String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    private static class Constants {
        public static final String FINANCE = "/layouts/finances/";
        public static final String BASES = FINANCE + "bases/";
        public static final String TILES = FINANCE + "tiles/";
        public static final String WINDOWS = FINANCE + "windows/";
    }

}

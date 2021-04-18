package oop.focus.finance.controller;

import oop.focus.common.Controller;

/**
 * Implementation of a controller interface that takes care of creating a new account.
 */
public interface NewAccountController extends Controller {

    /**
     * Save the account in the database.
     *
     * @param name of the account to save
     * @param amount of the account to save
     * @param color of the account to save
     */
    void newAccount(String name, String color, double amount);
}

package oop.focus.finance.controller;

import oop.focus.common.Controller;
import oop.focus.finance.model.GroupTransaction;

import java.util.List;

/**
 * Implementation of a controller interface that deals with the resolution of all credits.
 */
public interface ResolveController extends Controller {

    /**
     * Performs group transactions that settle all debts.
     */
    void resolve();

    /**
     * @return a list of group transactions which, if carried out, resolveList all debts
     */
    List<GroupTransaction> getResolvingTransactions();
}

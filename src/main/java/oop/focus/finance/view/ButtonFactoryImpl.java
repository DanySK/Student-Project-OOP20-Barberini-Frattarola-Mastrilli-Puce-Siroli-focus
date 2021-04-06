package oop.focus.finance.view;

import oop.focus.finance.controller.BaseController;
import oop.focus.finance.controller.GroupController;
import oop.focus.finance.controller.GroupControllerImpl;
import oop.focus.finance.controller.SubscriptionsController;
import oop.focus.finance.controller.SubscriptionsControllerImpl;
import oop.focus.finance.controller.TransactionsController;
import oop.focus.finance.controller.TransactionsControllerImpl;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.Transaction;

import java.util.function.Predicate;

public class ButtonFactoryImpl implements ButtonFactory {

    @Override
    public final FinanceMenuButton getTransactions(final BaseController controller, final String name,
                                                   final Predicate<Transaction> predicate, final FinanceManager manager) {
        final TransactionsController transactionsController = new TransactionsControllerImpl(manager, predicate);
        return new FinanceMenuButtonImpl(name, c -> c.changeView(transactionsController.getView()));
    }

    @Override
    public final FinanceMenuButton getSubscriptions(final BaseController controller, final String name,
                                                    final FinanceManager manager) {
        final SubscriptionsController subscriptionsController = new SubscriptionsControllerImpl(manager);
        return new FinanceMenuButtonImpl(name, c -> c.changeView(subscriptionsController.getView()));
    }

    @Override
    public final FinanceMenuButton getGroupTransactions(final BaseController controller, final String name,
                                                        final FinanceManager manager) {
        final GroupController groupController = new GroupControllerImpl(manager);
        return new FinanceMenuButtonImpl(name, c -> c.changeView(groupController.getView()));
    }

    @Override
    public final AccountsMenuButton getAccountTransactions(final TransactionsController controller, final Account account) {
        return new AccountsMenuButtonImpl(account.getName(), c -> c.showTransactions(a -> a.equals(account)));
    }

    @Override
    public final AccountsMenuButton getAccountTransactions(final TransactionsController controller) {
        return new AccountsMenuButtonImpl("Tutti i conti", c -> c.showTransactions(a -> true));
    }
}

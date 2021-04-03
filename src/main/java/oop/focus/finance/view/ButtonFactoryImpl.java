package oop.focus.finance.view;

import oop.focus.db.DataSource;

import oop.focus.finance.controller.BaseController;
import oop.focus.finance.controller.TransactionsController;
import oop.focus.finance.controller.GroupControllerImpl;
import oop.focus.finance.controller.SubscriptionsControllerImpl;
import oop.focus.finance.controller.TransactionsControllerImpl;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.Transaction;

import java.util.function.Predicate;

public class ButtonFactoryImpl implements ButtonFactory {

    @Override
    public final FinanceMenuButton getTransactions(final BaseController controller, final String name,
                                                   final Predicate<Transaction> predicate, final DataSource db) {
        View transactionsView = new TransactionsView(new TransactionsControllerImpl(db, predicate));
        return new FinanceMenuButtonImpl(name, (c -> c.changeView(transactionsView)));
    }

    @Override
    public final FinanceMenuButton getSubscriptions(final BaseController controller, final String name,
                                                    final DataSource db) {
        View subsView = new SubscriptionsView(new SubscriptionsControllerImpl(db));
        return new FinanceMenuButtonImpl(name, (c -> c.changeView(subsView)));
    }

    @Override
    public final FinanceMenuButton getGroupTransactions(final BaseController controller, final String name,
                                                        final DataSource db) {
        View groupView = new GroupView(new GroupControllerImpl(db));
        return new FinanceMenuButtonImpl(name, (c -> c.changeView(groupView)));
    }

    @Override
    public final AccountsMenuButton getAccountTransactions(final TransactionsController controller, final Account account) {
        return new AccountsMenuButtonImpl(account.getName(), (c -> c.showTransactions(a -> a.equals(account))));
    }

    @Override
    public final AccountsMenuButton getAccountTransactions(final TransactionsController controller) {
        return new AccountsMenuButtonImpl("Tutti i conti", (c -> c.showTransactions(a -> true)));
    }
}

package oop.focus.finance.view.bases;

import oop.focus.common.Controller;
import oop.focus.finance.controller.BaseController;
import oop.focus.finance.controller.GroupControllerImpl;
import oop.focus.finance.controller.SubscriptionsControllerImpl;
import oop.focus.finance.controller.TransactionsController;
import oop.focus.finance.controller.TransactionsControllerImpl;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.Transaction;
import oop.focus.statistics.controller.FinanceStatistics;

import java.util.function.Predicate;

public class ButtonFactoryImpl implements ButtonFactory {

    @Override
    public final FinanceMenuButton<BaseController> getTransactions(final BaseController controller, final String name,
                                                   final Predicate<Transaction> predicate, final FinanceManager manager) {
        final Controller transactionsController = new TransactionsControllerImpl(manager, predicate);
        return new FinanceMenuButtonImpl<>(name, c -> c.changeView(transactionsController.getView()));
    }

    @Override
    public final FinanceMenuButton<BaseController> getStatistics(final BaseController controller, final String name,
                                                           final FinanceManager manager) {
        final Controller statisticsController = new FinanceStatistics(manager);
        return new FinanceMenuButtonImpl<>(name, c -> c.changeView(statisticsController.getView()));
    }


    @Override
    public final FinanceMenuButton<BaseController> getSubscriptions(final BaseController controller, final String name,
                                                    final FinanceManager manager) {
        final Controller subscriptionsController = new SubscriptionsControllerImpl(manager);
        return new FinanceMenuButtonImpl<>(name, c -> c.changeView(subscriptionsController.getView()));
    }

    @Override
    public final FinanceMenuButton<BaseController> getGroupTransactions(final BaseController controller, final String name,
                                                        final FinanceManager manager) {
        final Controller groupController = new GroupControllerImpl(manager);
        return new FinanceMenuButtonImpl<>(name, c -> c.changeView(groupController.getView()));
    }

    @Override
    public final FinanceMenuButtonImpl<TransactionsController> getAccountTransactions(final TransactionsController controller, final Account account) {
        return new FinanceMenuButtonImpl<>(account.getName(), c -> c.showTransactions(a -> a.equals(account)));
    }

    @Override
    public final FinanceMenuButtonImpl<TransactionsController> getAccountTransactions(final TransactionsController controller) {
        return new FinanceMenuButtonImpl<>("Tutti i conti", c -> c.showTransactions(a -> true));
    }
}

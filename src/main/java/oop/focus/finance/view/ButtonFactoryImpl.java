package oop.focus.finance.view;

import oop.focus.finance.controller.BaseController;

public class ButtonFactoryImpl implements ButtonFactory {

    @Override
    public final FinanceMenuButton getTransactions(final BaseController controller) {
        View transactionsView = new TransactionsView(controller);
        return new FinanceMenuButtonImpl("Tutte", (c -> c.changeView(transactionsView)));
    }

    @Override
    public final FinanceMenuButton getOutings(final BaseController controller) {
        View outingsView = new OutingsView(controller);
        return new FinanceMenuButtonImpl("Uscite", (c -> c.changeView(outingsView)));
    }

    @Override
    public final FinanceMenuButton getIncomes(final BaseController controller) {
        View incomesView = new IncomesView(controller);
        return new FinanceMenuButtonImpl("Entrate", (c -> c.changeView(incomesView)));
    }

    @Override
    public final FinanceMenuButton getSubscriptions(final BaseController controller) {
        View subsView = new SubscriptionsView(controller);
        return new FinanceMenuButtonImpl("Abbonamenti", (c -> c.changeView(subsView)));
    }

    @Override
    public final FinanceMenuButton getGroupTransactions(final BaseController controller) {
        View groupView = new GroupView(controller);
        return new FinanceMenuButtonImpl("Gruppo", (c -> c.changeView(groupView)));
    }
}

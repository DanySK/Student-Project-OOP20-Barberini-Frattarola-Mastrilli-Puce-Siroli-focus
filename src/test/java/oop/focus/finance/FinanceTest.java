package oop.focus.finance;

import static org.junit.Assert.assertEquals;

import java.util.Date;

public class FinanceTest {

    private final FinanceManager manager = new FinanceManagerImpl();

    @org.junit.Before
    public void initLists() {
        // creo qualche account e la aggiungo a accounts
        manager.addAccount(new AccountImpl("Conto Corrente", "00AAFF", 150_000));
        manager.addAccount(new AccountImpl("Portafoglio", "FFFFFF", 10_000));
        // creo qualche categoria e la aggiungo a categories
        manager.addCategory(new CategoryImpl("Bar", "FFC0FF"));
        manager.addCategory(new CategoryImpl("Ristoranti", "A3B4C5"));
        manager.addCategory(new CategoryImpl("Stipendio", "AAAAAA"));
        manager.addCategory(new CategoryImpl("Spesa", "AB00FF"));
        manager.addCategory(new CategoryImpl("Affitto", "FF00FF"));
        // creo qualche transazione e la aggiungo a transactions
        manager.addTransaction(new TransactionImpl("Gelato",
                manager.getCategories().get(0), new Date(1_614_970_953_008L),
                manager.getAccounts().get(0), -250, Repetition.ONCE, true));
        manager.addTransaction(new TransactionImpl("Pizzeria la Marinella",
                manager.getCategories().get(1),
                new Date(1_414_970_953_008L), manager.getAccounts().get(1),
                -1_200, Repetition.ONCE, true));
    }

    @org.junit.Test
    public void testAccounts() {
        // controlli accounts
        assertEquals(2, manager.getAccounts().size());
        // referenze ai due account
        final Account firstAccount = manager.getAccounts().get(0);
        final Account secondAccount = manager.getAccounts().get(1);
        // controlli importi iniziali
        assertEquals(149_750, firstAccount.getAmount());
        assertEquals(8_800, secondAccount.getAmount());
        // eseguo altre transazioni
        manager.addTransaction(new TransactionImpl("Iper", manager.getCategories().get(3),
                new Date(), firstAccount, -2_500, Repetition.ONCE, true));
        manager.addTransaction(new TransactionImpl("Conad", manager.getCategories().get(3),
                new Date(), firstAccount, -5_000, Repetition.ONCE, true));
        // controllo importi
        assertEquals(142_250, firstAccount.getAmount());
        assertEquals(8_800, secondAccount.getAmount());
        // eseguo altre transazioni
        final Transaction a = new TransactionImpl("Coop", manager.getCategories().get(3),
                new Date(), firstAccount, -10_000, Repetition.ONCE, true);
        final Transaction b = new TransactionImpl("Stipendio", manager.getCategories().get(2),
                new Date(), secondAccount, 100_000, Repetition.ONCE, true);
        manager.addTransaction(a);
        manager.addTransaction(b);
        // controllo importi
        assertEquals(132_250, firstAccount.getAmount());
        assertEquals(108_800, secondAccount.getAmount());
        // elimino le ultime due transazioni
        manager.removeTransaction(a);
        manager.removeTransaction(b);
        // controllo importi
        assertEquals(142_250, firstAccount.getAmount());
        assertEquals(8_800, secondAccount.getAmount());
    }


    @org.junit.Test
    public void testCategories() {
        // controlli categories
        assertEquals(5, manager.getCategories().size());
    }

    @org.junit.Test
    public void testTransactions() {
        // controlli transactions
        assertEquals(2, manager.getTransactions().size());
    }

}


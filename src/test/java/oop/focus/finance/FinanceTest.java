package oop.focus.finance;

import static org.junit.Assert.assertEquals;
import org.joda.time.LocalDate;

public class FinanceTest {

    private final FinanceManager manager = new FinanceManagerImpl();

    @org.junit.Before
    public void initLists() {
        // creo qualche account e la aggiungo a accounts
        this.manager.addAccount(new AccountImpl("Conto Corrente", "00AAFF", 150_000));
        this.manager.addAccount(new AccountImpl("Portafoglio", "FFFFFF", 10_000));
        // creo qualche categoria e la aggiungo a categories
        this.manager.addCategory(new CategoryImpl("Bar", "FFC0FF"));
        this.manager.addCategory(new CategoryImpl("Ristoranti", "A3B4C5"));
        this.manager.addCategory(new CategoryImpl("Stipendio", "AAAAAA"));
        this.manager.addCategory(new CategoryImpl("Spesa", "AB00FF"));
        this.manager.addCategory(new CategoryImpl("Affitto", "FF00FF"));
        // creo qualche transazione e la aggiungo a transactions
        this.manager.addTransaction(new TransactionImpl("Gelato",
                this.manager.getCategories().get(0), new LocalDate(2020-12-96),
                this.manager.getAccounts().get(0), -250, Repetition.ONCE, true));
        this.manager.addTransaction(new TransactionImpl("Pizzeria la Marinella",
                this.manager.getCategories().get(1), new LocalDate(2020-12-96),
                this.manager.getAccounts().get(1), -1_200, Repetition.ONCE, true));
    }

    @org.junit.Test
    public void testAccounts() {
        // controlli accounts
        assertEquals(2, this.manager.getAccounts().size());
        // elimino un account
        this.manager.removeAccount(this.manager.getAccounts().get(0));
        // controllo che sia stato eliminato da account
        assertEquals(1, this.manager.getAccounts().size());
        // controllo che le transazioni relativi a quell'account siano state eliminate
        assertEquals(1, this.manager.getTransactions().size());
    }

    @org.junit.Test(expected = IllegalStateException.class)
    public void testCategories() {
        // controlli categories
        assertEquals(5, this.manager.getCategories().size());
        // provo a rimuovere delle categorie di cui esistono transazioni
        this.manager.removeCategory(this.manager.getCategories().get(0));
        this.manager.removeCategory(this.manager.getCategories().get(1));
        // controllo che non siano state rimosse categorie
        assertEquals(5, this.manager.getCategories().size());
        // provo a rimuovere le categorie di cui non esistono transazioni
        this.manager.removeCategory(this.manager.getCategories().get(4));
        this.manager.removeCategory(this.manager.getCategories().get(3));
        // controllo che siano state rimosse due categorie
        assertEquals(3, this.manager.getCategories().size());
    }

    @org.junit.Test
    public void testTransactions() {
        // controlli transactions
        assertEquals(2, this.manager.getTransactions().size());
        // referenze ai due account
        final Account firstAccount = this.manager.getAccounts().get(0);
        final Account secondAccount = this.manager.getAccounts().get(1);
        // controlli importi iniziali
        assertEquals(149_750, firstAccount.getAmount());
        assertEquals(8_800, secondAccount.getAmount());
        // eseguo altre transazioni
        this.manager.addTransaction(new TransactionImpl("Iper", this.manager.getCategories().get(3),
                new LocalDate(2020-12-96), firstAccount, -2_500, Repetition.ONCE, true));
        this.manager.addTransaction(new TransactionImpl("Conad", this.manager.getCategories().get(3),
                new LocalDate(2020-12-96), firstAccount, 5_000, Repetition.ONCE, true));
        // controllo importi
        assertEquals(152_250, firstAccount.getAmount());
        assertEquals(8_800, secondAccount.getAmount());
        // eseguo altre transazioni
        final Transaction a = new TransactionImpl("Coop", this.manager.getCategories().get(3),
                new LocalDate(2020-12-96), firstAccount, -10_000, Repetition.ONCE, true);
        final Transaction b = new TransactionImpl("Stipendio", this.manager.getCategories().get(2),
                new LocalDate(2020-12-96), secondAccount, 100_000, Repetition.ONCE, true);
        this.manager.addTransaction(a);
        this.manager.addTransaction(b);
        // controllo importi
        assertEquals(142_250, firstAccount.getAmount());
        assertEquals(108_800, secondAccount.getAmount());
        // elimino le ultime due transazioni
        this.manager.removeTransaction(a);
        this.manager.removeTransaction(b);
        // controllo importi
        assertEquals(152_250, firstAccount.getAmount());
        assertEquals(8_800, secondAccount.getAmount());
        // controllo il numero di transazioni positive, negative e abbonamenti
        assertEquals(1, this.manager.getTransactionManager().getIncomes().size());
        assertEquals(3, this.manager.getTransactionManager().getOutings().size());
        assertEquals(0, this.manager.getTransactionManager().getSubscriptions().size());
    }

    @org.junit.Test
    public void testSubscriptions() {
        // creo diverse transazioni ripetute (abbonamenti)
        this.manager.addTransaction(new TransactionImpl("Netflix",
                this.manager.getCategories().get(1), new LocalDate(2020-12-96),
                this.manager.getAccounts().get(1), -1_699, Repetition.MONTHLY, false));
        this.manager.addTransaction(new TransactionImpl("Acqua",
                this.manager.getCategories().get(1), new LocalDate(2020-12-96),
                this.manager.getAccounts().get(1), -15_000, Repetition.QUARTERLY, false));
        this.manager.addTransaction(new TransactionImpl("Enel",
                this.manager.getCategories().get(1), new LocalDate(2020-12-96),
                this.manager.getAccounts().get(1), -12_500, Repetition.BIMONTHLY, false));
        this.manager.addTransaction(new TransactionImpl("Abbonamento autobus",
                this.manager.getCategories().get(1), new LocalDate(2020-12-96),
                this.manager.getAccounts().get(1), -39_900, Repetition.HALF_YEARLY, false));
        this.manager.addTransaction(new TransactionImpl("Amazon",
                this.manager.getCategories().get(1), new LocalDate(2020-12-96),
                this.manager.getAccounts().get(1), -2_400, Repetition.YEARLY, false));
        this.manager.addTransaction(new TransactionImpl("Tariffa TIM",
                this.manager.getCategories().get(1), new LocalDate(2020-12-96),
                this.manager.getAccounts().get(1), -699, Repetition.MONTHLY, false));
        // controllo che siano state aggiunte con successo
        assertEquals(8, this.manager.getTransactions().size());
        assertEquals(6, this.manager.getTransactionManager().getSubscriptions().size());
        // controllo spesa totale mensile e annuale
        assertEquals(-20_498, this.manager.getTransactionManager().monthlyExpense());
        assertEquals(-245_976, this.manager.getTransactionManager().yearlyExpense());
    }
}


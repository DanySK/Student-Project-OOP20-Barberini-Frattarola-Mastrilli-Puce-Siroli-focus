package oop.focus.finance;

import static org.junit.Assert.assertEquals;

import oop.focus.homepage.model.Person;
import oop.focus.homepage.model.PersonImpl;
import org.joda.time.LocalDate;

import java.util.List;

public class FinanceTest {

    private final FinanceManager manager = new FinanceManagerImpl();
    private final GroupManager groupManager = new GroupManagerImpl();

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
                this.manager.getCategories().get(0), new LocalDate(2020, 1, 1),
                this.manager.getAccounts().get(0), -250, Repetition.ONCE, true));
        this.manager.addTransaction(new TransactionImpl("Pizzeria la Marinella",
                this.manager.getCategories().get(1), new LocalDate(2020, 1, 1),
                this.manager.getAccounts().get(1), -1_200, Repetition.ONCE, true));
        // creo tre persone (nei due modi possibili)
        final Person alex = new PersonImpl("Alex", "me");
        final Person luca = new PersonImpl("Marco", "fratello");
        final Person gaia = new PersonImpl("Chiara", "amica");
        // aggiungo tre persone al manager delle transazioni di gruppo
        this.groupManager.addPerson(alex);
        this.groupManager.addPerson(luca);
        this.groupManager.addPerson(gaia);
        // aggiungo alcune transazioni di gruppo
        this.groupManager.addTransaction(new GroupTransactionImpl("", alex, List.of(alex, gaia), 500, new LocalDate()));
        this.groupManager.addTransaction(new GroupTransactionImpl("", luca, List.of(alex, luca), 1000, new LocalDate()));
        this.groupManager.addTransaction(new GroupTransactionImpl("", luca, List.of(alex, gaia), 300, new LocalDate()));
        this.groupManager.addTransaction(new GroupTransactionImpl("", alex, List.of(alex), 300, new LocalDate()));
        this.groupManager.addTransaction(new GroupTransactionImpl("", gaia, List.of(luca), 100, new LocalDate()));
        this.groupManager.addTransaction(new GroupTransactionImpl("", alex, List.of(alex, luca, gaia), 600, new LocalDate()));
        this.groupManager.addTransaction(new GroupTransactionImpl("", gaia, List.of(alex, luca), 200, new LocalDate()));
        this.groupManager.addTransaction(new GroupTransactionImpl("", luca, List.of(alex, luca), 400, new LocalDate()));
        this.groupManager.addTransaction(new GroupTransactionImpl("", gaia, List.of(alex, gaia), 500, new LocalDate()));
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
        this.manager.addAccount(new AccountImpl("Conto iscrizioni", "00FFFF", 200_000));
        // creo diverse transazioni ripetute (abbonamenti)
        this.manager.addTransaction(new TransactionImpl("Netflix",
                this.manager.getCategories().get(1), new LocalDate(2021, 1, 4),
                this.manager.getAccounts().get(2), -1_699, Repetition.WEEKLY, false)); // 10
        this.manager.addTransaction(new TransactionImpl("Acqua",
                this.manager.getCategories().get(1), new LocalDate(2020, 11, 25),
                this.manager.getAccounts().get(2), -15_000, Repetition.QUARTERLY, false)); // 2
        this.manager.addTransaction(new TransactionImpl("Enel",
                this.manager.getCategories().get(1), new LocalDate(2020, 11, 7),
                this.manager.getAccounts().get(2), -12_500, Repetition.BIMONTHLY, false)); // 3
        this.manager.addTransaction(new TransactionImpl("Abbonamento autobus",
                this.manager.getCategories().get(1), new LocalDate(2020, 6, 5),
                this.manager.getAccounts().get(2), -39_900, Repetition.HALF_YEARLY, false)); // 2
        this.manager.addTransaction(new TransactionImpl("Amazon",
                this.manager.getCategories().get(1), new LocalDate(2020, 1, 1),
                this.manager.getAccounts().get(2), -2_400, Repetition.YEARLY, false)); // 2
        this.manager.addTransaction(new TransactionImpl("Tariffa TIM",
                this.manager.getCategories().get(1), new LocalDate(2021, 3, 3),
                this.manager.getAccounts().get(2), -699, Repetition.MONTHLY, false)); // 1
        // controllo che siano state aggiunte con successo
        assertEquals(8, this.manager.getTransactions().size());
        assertEquals(6, this.manager.getTransactionManager().getSubscriptions().size());
        // controllo spesa totale mensile e annuale
        assertEquals(-26_889, this.manager.getTransactionManager().monthlyExpense());
        assertEquals(-314_173, this.manager.getTransactionManager().yearlyExpense());
        // faccio creare tutte le transazioni da ripetere
        this.manager.getTransactionManager().getGeneratedTransactions().forEach(this.manager::addTransaction);
        // controllo che siano state aggiunte tutte
        assertEquals(22, this.manager.getTransactions().size());
        // controllo che l'importo del conto sia corretto
        assertEquals(30_211, this.manager.getAccounts().get(2).getAmount());
    }

    @org.junit.Test(expected = IllegalStateException.class)
    public void testGroupTransactions() {
        // controllo che ci siano tutte le persone nel gruppo
        assertEquals(3, this.groupManager.getGroup().size());
        // controllo che ci siano tutte le transazioni di gruppo
        assertEquals(9, this.groupManager.getTransactions().size());
        // controllo che i crediti e i debiti siano tutti corretti
        assertEquals(-550, this.groupManager.getCredit(this.groupManager.getGroup().get(0)));
        assertEquals(600, this.groupManager.getCredit(this.groupManager.getGroup().get(1)));
        assertEquals(-50, this.groupManager.getCredit(this.groupManager.getGroup().get(2)));
        // provo a eliminare qualche transazione
        this.groupManager.removeTransaction(this.groupManager.getTransactions().get(7));
        this.groupManager.removeTransaction(this.groupManager.getTransactions().get(4));
        this.groupManager.removeTransaction(this.groupManager.getTransactions().get(0));
        // eseguo una transazione
        this.groupManager.addTransaction(new GroupTransactionImpl("", this.groupManager.getGroup().get(0),
                List.of(this.groupManager.getGroup().get(0), this.groupManager.getGroup().get(2)),
                200, new LocalDate()));
        // controllo che siano state aggiornate
        assertEquals(7, this.groupManager.getTransactions().size());
        // controllo che i crediti siano cambiati e corretti
        assertEquals(-500, this.groupManager.getCredit(this.groupManager.getGroup().get(0)));
        assertEquals(500, this.groupManager.getCredit(this.groupManager.getGroup().get(1)));
        assertEquals(0, this.groupManager.getCredit(this.groupManager.getGroup().get(2)));
        // elimino una persona eliminabile
        this.groupManager.removePerson(this.groupManager.getGroup().get(2));
        // controllo quante persone ci sono nel gruppo
        assertEquals(2, this.groupManager.getGroup().size());
        // elimino una persona non eliminabile
        this.groupManager.removePerson(this.groupManager.getGroup().get(0));
        // controllo qquante persone ci sono nel gruppo
        assertEquals(2, this.groupManager.getGroup().size());
        //
    }
}


package oop.focus.finance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import oop.focus.homepage.model.Person;
import oop.focus.homepage.model.PersonImpl;
import org.joda.time.LocalDate;

import java.util.List;

public class FinanceTest {

    private final FinanceManager financeManager = new FinanceManagerImpl();
    private final GroupManager groupManager = new GroupManagerImpl();

    @org.junit.Before
    public void initLists() {
        // creo qualche account e la aggiungo a accounts
        this.financeManager.addAccount(new AccountImpl("Conto Corrente", "00AAFF", 150_000));
        this.financeManager.addAccount(new AccountImpl("Portafoglio", "FFFFFF", 10_000));

        // creo qualche categoria e la aggiungo a categories
        this.financeManager.addCategory(new CategoryImpl("Bar", "FFC0FF"));
        this.financeManager.addCategory(new CategoryImpl("Ristoranti", "A3B4C5"));
        this.financeManager.addCategory(new CategoryImpl("Stipendio", "AAAAAA"));
        this.financeManager.addCategory(new CategoryImpl("Spesa", "AB00FF"));
        this.financeManager.addCategory(new CategoryImpl("Affitto", "FF00FF"));

        // creo qualche transazione e la aggiungo a transactions
        this.financeManager.addTransaction(new TransactionImpl("Gelato",
                this.financeManager.getCategoryManager().getCategories().get(0), new LocalDate(2020, 1, 1),
                this.financeManager.getAccountManager().getAccounts().get(0), -250, Repetition.ONCE));
        this.financeManager.addTransaction(new TransactionImpl("Pizzeria la Marinella",
                this.financeManager.getCategoryManager().getCategories().get(1), new LocalDate(2020, 1, 1),
                this.financeManager.getAccountManager().getAccounts().get(1), -1_200, Repetition.ONCE));

        // creo alcune transazioni rapide e le aggiungo a quickTransactions
        this.financeManager.getQuickManager().add(new QuickTransactionImpl("Parcheggio",
                this.financeManager.getCategoryManager().getCategories().get(3),
                this.financeManager.getAccountManager().getAccounts().get(0), -150));
        this.financeManager.getQuickManager().add(new QuickTransactionImpl("Frappe",
                this.financeManager.getCategoryManager().getCategories().get(0),
                this.financeManager.getAccountManager().getAccounts().get(0), -300));
        this.financeManager.getQuickManager().add(new QuickTransactionImpl("Paghetta",
                this.financeManager.getCategoryManager().getCategories().get(2),
                this.financeManager.getAccountManager().getAccounts().get(0), 1000));

        // creo tre persone (nei due modi possibili)
        final Person alex = new PersonImpl("Alex", "me");
        final Person luca = new PersonImpl("Luca", "fratello");
        final Person gaia = new PersonImpl("Gaia", "amica");
        final Person maia = new PersonImpl("Maia", "fidanzata");
        final Person elia = new PersonImpl("Elia", "cugino");

        // aggiungo tre persone al financeManager delle transazioni di gruppo
        this.groupManager.addPerson(alex);
        this.groupManager.addPerson(luca);
        this.groupManager.addPerson(gaia);
        this.groupManager.addPerson(maia);
        this.groupManager.addPerson(elia);

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
        this.groupManager.addTransaction(new GroupTransactionImpl("", maia, List.of(alex), 100, new LocalDate()));
        this.groupManager.addTransaction(new GroupTransactionImpl("", elia, List.of(alex), 150, new LocalDate()));
    }

    @org.junit.Test
    public void testAccounts() {
        // controlli accounts
        assertEquals(2, this.financeManager.getAccountManager().getAccounts().size());

        // elimino un account
        this.financeManager.removeAccount(this.financeManager.getAccountManager().getAccounts().get(0));

        // controllo che sia stato eliminato da account
        assertEquals(1, this.financeManager.getAccountManager().getAccounts().size());

        // controllo che le transazioni relativi a quell'account siano state eliminate
        assertEquals(1, this.financeManager.getTransactionManager().getTransactions().size());
    }

    @org.junit.Test()
    public void testCategories() {
        // controlli categories
        assertEquals(5, this.financeManager.getCategoryManager().getCategories().size());

        // provo a rimuovere delle categorie di cui esistono transazioni
        try {
            this.financeManager.removeCategory(this.financeManager.getCategoryManager().getCategories().get(0));
            fail();
        } catch (IllegalStateException ignored) { }
        try {
            this.financeManager.removeCategory(this.financeManager.getCategoryManager().getCategories().get(1));
            fail();
        } catch (IllegalStateException ignored) { }

        // controllo che non siano state rimosse categorie
        assertEquals(5, this.financeManager.getCategoryManager().getCategories().size());

        // provo a rimuovere le categorie di cui non esistono transazioni
        this.financeManager.removeCategory(this.financeManager.getCategoryManager().getCategories().get(4));
        this.financeManager.removeCategory(this.financeManager.getCategoryManager().getCategories().get(3));

        // controllo che siano state rimosse due categorie
        assertEquals(3, this.financeManager.getCategoryManager().getCategories().size());
    }

    @org.junit.Test
    public void testTransactions() {
        // controlli transactions
        assertEquals(2, this.financeManager.getTransactionManager().getTransactions().size());

        // referenze ai due account
        final Account firstAccount = this.financeManager.getAccountManager().getAccounts().get(0);
        final Account secondAccount = this.financeManager.getAccountManager().getAccounts().get(1);

        // controlli importi iniziali
        assertEquals(149_750, firstAccount.getAmount());
        assertEquals(8_800, secondAccount.getAmount());

        // eseguo altre transazioni
        this.financeManager.addTransaction(new TransactionImpl("Iper",
                this.financeManager.getCategoryManager().getCategories().get(3), new LocalDate(2020-12-96),
                firstAccount,-2_500, Repetition.ONCE));
        this.financeManager.addTransaction(new TransactionImpl("Conad",
                this.financeManager.getCategoryManager().getCategories().get(3), new LocalDate(2020-12-96),
                firstAccount, 5_000, Repetition.ONCE));

        // controllo importi
        assertEquals(152_250, firstAccount.getAmount());
        assertEquals(8_800, secondAccount.getAmount());

        // eseguo altre transazioni
        final var a = new TransactionImpl("Coop", this.financeManager.getCategoryManager().getCategories().get(3),
                new LocalDate(2020-12-96), firstAccount, -10_000, Repetition.ONCE);
        final var b = new TransactionImpl("Stipendio", this.financeManager.getCategoryManager().getCategories().get(2),
                new LocalDate(2020-12-96), secondAccount, 100_000, Repetition.ONCE);
        this.financeManager.addTransaction(a);
        this.financeManager.addTransaction(b);

        // controllo importi
        assertEquals(142_250, firstAccount.getAmount());
        assertEquals(108_800, secondAccount.getAmount());

        // elimino le ultime due transazioni
        this.financeManager.removeTransaction(a);
        this.financeManager.removeTransaction(b);

        // controllo importi
        assertEquals(152_250, firstAccount.getAmount());
        assertEquals(8_800, secondAccount.getAmount());

        // controllo il numero di transazioni positive, negative e abbonamenti
        assertEquals(1, this.financeManager.getTransactionManager().getIncomes().size());
        assertEquals(3, this.financeManager.getTransactionManager().getOutings().size());
        assertEquals(0, this.financeManager.getTransactionManager().getSubscriptions().size());
    }

    @org.junit.Test
    public void testSubscriptions() {
        this.financeManager.addAccount(new AccountImpl("Conto iscrizioni", "00FFFF", 200_000));

        // creo diverse transazioni ripetute (abbonamenti)
        this.financeManager.addTransaction(new TransactionImpl("Bar Pasquino",
                this.financeManager.getCategoryManager().getCategories().get(1), new LocalDate(2021, 1, 1),
                this.financeManager.getAccountManager().getAccounts().get(2), -100, Repetition.DAILY));
        this.financeManager.addTransaction(new TransactionImpl("Abbonamento metro",
                this.financeManager.getCategoryManager().getCategories().get(1), new LocalDate(2021, 1, 1),
                this.financeManager.getAccountManager().getAccounts().get(2), -1_000, Repetition.WEEKLY));
        this.financeManager.addTransaction(new TransactionImpl("Tariffa TIM",
                this.financeManager.getCategoryManager().getCategories().get(1), new LocalDate(2021, 3, 3),
                this.financeManager.getAccountManager().getAccounts().get(2), -699, Repetition.MONTHLY));
        this.financeManager.addTransaction(new TransactionImpl("Enel",
                this.financeManager.getCategoryManager().getCategories().get(1), new LocalDate(2020, 11, 7),
                this.financeManager.getAccountManager().getAccounts().get(2), -12_500, Repetition.BIMONTHLY));
        this.financeManager.addTransaction(new TransactionImpl("Acqua",
                this.financeManager.getCategoryManager().getCategories().get(1), new LocalDate(2020, 11, 25),
                this.financeManager.getAccountManager().getAccounts().get(2), -15_000, Repetition.QUARTERLY));
        this.financeManager.addTransaction(new TransactionImpl("Abbonamento autobus",
                this.financeManager.getCategoryManager().getCategories().get(1), new LocalDate(2020, 6, 5),
                this.financeManager.getAccountManager().getAccounts().get(2), -39_900, Repetition.HALF_YEARLY));
        this.financeManager.addTransaction(new TransactionImpl("Amazon",
                this.financeManager.getCategoryManager().getCategories().get(1), new LocalDate(2020, 1, 1),
                this.financeManager.getAccountManager().getAccounts().get(2), -2_400, Repetition.YEARLY));

        // controllo che siano state aggiunte con successo
        assertEquals(9, this.financeManager.getTransactionManager().getTransactions().size());
        assertEquals(7, this.financeManager.getTransactionManager().getSubscriptions().size());

        // controllo spesa totale mensile e annuale
        assertEquals(-26_191, this.financeManager.getTransactionManager().monthlyExpense());
        assertEquals(-314_228, this.financeManager.getTransactionManager().yearlyExpense());

        // faccio creare tutte le transazioni da ripetere
        this.financeManager.generateRepeatedTransactions(new LocalDate(2021, 3, 13));

        // controllo che siano state aggiunte tutte
        assertEquals(95, this.financeManager.getTransactionManager().getTransactions().size());

        // controllo che l'importo del conto sia corretto
        assertEquals(29_001, this.financeManager.getAccountManager().getAccounts().get(2).getAmount());
    }

    @org.junit.Test()
    public void testQuickTransactions() {
        // controllo che siano il numero corretto
        assertEquals(3, this.financeManager.getQuickManager().getQuickTransactions().size());

        // controllo quante transazioni ci sono di base
        assertEquals(2, this.financeManager.getTransactionManager().getTransactions().size());

        // controllo il saldo del conto di riferimento
        assertEquals(149750, this.financeManager.getAccountManager().getAccounts().get(0).getAmount());

        // eseguo alcune transazioni rapide
        this.financeManager.doQuickTransaction(this.financeManager.getQuickManager().getQuickTransactions().get(0));
        this.financeManager.doQuickTransaction(this.financeManager.getQuickManager().getQuickTransactions().get(0));
        this.financeManager.doQuickTransaction(this.financeManager.getQuickManager().getQuickTransactions().get(0));
        this.financeManager.doQuickTransaction(this.financeManager.getQuickManager().getQuickTransactions().get(1));
        this.financeManager.doQuickTransaction(this.financeManager.getQuickManager().getQuickTransactions().get(1));
        this.financeManager.doQuickTransaction(this.financeManager.getQuickManager().getQuickTransactions().get(2));

        // controllo che le transazioni siano sei in più
        assertEquals(8, this.financeManager.getTransactionManager().getTransactions().size());

        // controllo che il saldo sia corretto
        assertEquals(149700, this.financeManager.getAccountManager().getAccounts().get(0).getAmount());

        // AGGIUNGERE POSSIBILITA' DI MODIFICARE IL CONTO DI UN MOVIMENTO PASSATO O ALTRE COSE MAGARI
    }

    @org.junit.Test()
    public void testGroupTransactions() {
        // controllo che ci siano tutte le persone nel gruppo
        assertEquals(5, this.groupManager.getGroup().size());

        // controllo che ci siano tutte le transazioni di gruppo
        assertEquals(11, this.groupManager.getTransactions().size());

        // controllo che i crediti e i debiti siano tutti corretti
        assertEquals(-800, this.groupManager.getCredit(this.groupManager.getGroup().get(0)));
        assertEquals(600, this.groupManager.getCredit(this.groupManager.getGroup().get(1)));
        assertEquals(-50, this.groupManager.getCredit(this.groupManager.getGroup().get(2)));
        assertEquals(100, this.groupManager.getCredit(this.groupManager.getGroup().get(3)));
        assertEquals(150, this.groupManager.getCredit(this.groupManager.getGroup().get(4)));

        // provo a eliminare qualche transazione
        this.groupManager.removeTransaction(this.groupManager.getTransactions().get(7));
        this.groupManager.removeTransaction(this.groupManager.getTransactions().get(4));
        this.groupManager.removeTransaction(this.groupManager.getTransactions().get(0));

        // eseguo una transazione
        this.groupManager.addTransaction(new GroupTransactionImpl("", this.groupManager.getGroup().get(0),
                List.of(this.groupManager.getGroup().get(0), this.groupManager.getGroup().get(2)),
                200, new LocalDate()));

        // controllo che siano state aggiornate
        assertEquals(9, this.groupManager.getTransactions().size());

        // controllo che i crediti siano cambiati e corretti
        assertEquals(-750, this.groupManager.getCredit(this.groupManager.getGroup().get(0)));
        assertEquals(500, this.groupManager.getCredit(this.groupManager.getGroup().get(1)));
        assertEquals(0, this.groupManager.getCredit(this.groupManager.getGroup().get(2)));

        // elimino una persona eliminabile
        this.groupManager.removePerson(this.groupManager.getGroup().get(2));

        // controllo quante persone ci sono nel gruppo
        assertEquals(4, this.groupManager.getGroup().size());

        // elimino una persona non eliminabile
        try {
            this.groupManager.removePerson(this.groupManager.getGroup().get(0));
            fail();
        } catch (IllegalStateException ignored) { }

        // controllo quante persone ci sono nel gruppo
        assertEquals(4, this.groupManager.getGroup().size());

        // richiedo la soluzione dei debiti
        var solution = this.groupManager.resolve();
        assertEquals(3, solution.size());

        // eseguo le transazioni di risoluzione
        solution.forEach(this.groupManager::addTransaction);

        // controllo che nessuno abbia più debiti
        this.groupManager.getGroup().forEach(p -> assertEquals(0, this.groupManager.getCredit(p)));

        // elimino il gruppo e le sue transazioni
        try {
            this.groupManager.reset();
        } catch (IllegalStateException e) {
            fail();
        }

        // controllo che non ci sia più nulla
        assertEquals(0, this.groupManager.getGroup().size());
        assertEquals(0, this.groupManager.getTransactions().size());
    }

}


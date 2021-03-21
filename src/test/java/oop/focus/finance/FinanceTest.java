package oop.focus.finance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.db.exceptions.DaoAccessException;
import oop.focus.homepage.model.Person;
import oop.focus.homepage.model.PersonImpl;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.List;

public class FinanceTest {

    private final DataSource db = new DataSourceImpl();
    private final FinanceManager financeManager = new FinanceManagerImpl(this.db);
    private final GroupManager groupManager = new GroupManagerImpl(this.db);

    @org.junit.Before
    public void initLists() {
        // ripristino il database
        var transactions = this.db.getTransactions();
        var categories = this.db.getCategories();
        var accounts = this.db.getAccounts();
        var colors = this.db.getColors();
        var quickTransactions = this.db.getQuickTransactions();
        var group = this.db.getGroup();
        var persons = this.db.getPersons();
        var relationships = this.db.getRelationships();
        var groupTransactions = this.db.getGroupTransactions();

        try {
            for (var v : transactions.getAll()) {
                transactions.delete(v);
            }
            for (var v : accounts.getAll()) {
                accounts.delete(v);
            }
            for (var v : colors.getAll()) {
                colors.delete(v);
            }
            for (var v : quickTransactions.getAll()) {
                quickTransactions.delete(v);
            }
            for (var v : groupTransactions.getAll()) {
                groupTransactions.delete(v);
            }
            for (var v : group.getAll()) {
                group.delete(v);
            }
            for (var v : categories.getAll()) {
                categories.delete(v);
            }
            for (var v : persons.getAll()) {
                persons.delete(v);
            }
            for (var v : relationships.getAll()) {
                relationships.delete(v);
            }
        } catch (DaoAccessException e) {
            fail();
        }

        // salvo un colore nel dabatabe
        try {
            colors.save("FFFFFF");
        } catch (DaoAccessException e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(1, colors.getAll().size());

        // creo qualche account e la aggiungo a accounts
        this.financeManager.addAccount(new AccountImpl("Conto Corrente", "FFFFFF", 150_000));
        this.financeManager.addAccount(new AccountImpl("Portafoglio", "FFFFFF", 10_000));

        // creo qualche categoria e la aggiungo a categories
        this.financeManager.addCategory(new CategoryImpl("Bar", "FFFFFF"));
        this.financeManager.addCategory(new CategoryImpl("Ristoranti", "FFFFFF"));
        this.financeManager.addCategory(new CategoryImpl("Stipendio", "FFFFFF"));
        this.financeManager.addCategory(new CategoryImpl("Spesa", "FFFFFF"));
        this.financeManager.addCategory(new CategoryImpl("Affitto", "FFFFFF"));

        // creo qualche transazione e la aggiungo a transactions
        this.financeManager.addTransaction(new TransactionImpl("Gelato",
                new CategoryImpl("Bar", "FFFFFF"), new LocalDateTime(2020, 1, 1, 0, 0, 0),
                new AccountImpl("Conto Corrente", "FFFFFF", 0), -250, Repetition.ONCE));
        this.financeManager.addTransaction(new TransactionImpl("Pizzeria la Marinella",
                new CategoryImpl("Ristoranti", "FFFFFF"), new LocalDateTime(2020, 1, 1, 0, 0, 0),
                new AccountImpl("Portafoglio", "FFFFFF", 0), -1_200, Repetition.ONCE));

        // creo alcune transazioni rapide e le aggiungo a quickTransactions
        this.financeManager.getQuickManager().add(new QuickTransactionImpl(-150,
                new CategoryImpl("Bar", "FFFFFF"),
                new AccountImpl("Conto Corrente", "FFFFFF", 0), "Parcheggio"));
        this.financeManager.getQuickManager().add(new QuickTransactionImpl(-300,
                new CategoryImpl("Bar", "FFFFFF"),
                new AccountImpl("Conto Corrente", "FFFFFF", 0), "Frappé"));
        this.financeManager.getQuickManager().add(new QuickTransactionImpl(1000,
                new CategoryImpl("Bar", "FFFFFF"),
                new AccountImpl("Conto Corrente", "FFFFFF", 0), "Paghetta"));

        // creo e aggiugno al db le relationships
        try {
            relationships.save("me");
            relationships.save("fratello");
            relationships.save("amica");
            relationships.save("fidanzata");
            relationships.save("fidanzato");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // creo cinque persone (nei due modi possibili)
        final Person alex = new PersonImpl("Alex", "me");
        final Person luca = new PersonImpl("Luca", "fratello");
        final Person gaia = new PersonImpl("Gaia", "amica");
        final Person maia = new PersonImpl("Maia", "fidanzata");
        final Person elia = new PersonImpl("Elia", "fidanzato");

        // aggiungo le persone a persons
        try {
            persons.save(alex);
            persons.save(luca);
            persons.save(gaia);
            persons.save(maia);
            persons.save(elia);
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }

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
        this.financeManager.removeAccount(new AccountImpl("Conto Corrente", "FFFFFF"
        , 150_000));

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
            this.financeManager.removeCategory(new CategoryImpl("Bar", "FFFFFF"
            ));
            fail();
        } catch (Exception ignored) { }
        try {
            this.financeManager.removeCategory(new CategoryImpl("Ristoranti", "FFFFFF"
            ));
            fail();
        } catch (Exception ignored) { }

        // controllo che non siano state rimosse categorie
        assertEquals(5, this.financeManager.getCategoryManager().getCategories().size());

        // provo a rimuovere le categorie di cui non esistono transazioni
        this.financeManager.removeCategory(new CategoryImpl("Spesa", "FFFFFF"
        ));
        this.financeManager.removeCategory(new CategoryImpl("Affitto", "FFFFFF"
        ));

        // controllo che siano state rimosse due categorie
        assertEquals(3, this.financeManager.getCategoryManager().getCategories().size());
    }

    @org.junit.Test
    public void testTransactions() {
        // controlli transactions
        assertEquals(2, this.financeManager.getTransactionManager().getTransactions().size());

        // referenze ai due account
        final Account firstAccount = new AccountImpl("Conto Corrente", "FFFFFF", 0);
        final Account secondAccount = new AccountImpl("Portafoglio", "FFFFFF", 0);

        // controlli importi iniziali
        assertEquals(149_750, this.financeManager.getAmount(firstAccount));
        assertEquals(8_800, this.financeManager.getAmount(secondAccount));

        // eseguo altre transazioni
        this.financeManager.addTransaction(new TransactionImpl("Iper",
                new CategoryImpl("Spesa", "FFFFFF"), new LocalDateTime(2020, 12, 6, 0, 0, 0),
                firstAccount,-2_500, Repetition.ONCE));
        this.financeManager.addTransaction(new TransactionImpl("Conad",
                new CategoryImpl("Spesa", "FFFFFF"), new LocalDateTime(2020, 12, 6, 0, 0, 0),
                firstAccount, 5_000, Repetition.ONCE));

        // controllo importi
        assertEquals(152_250, this.financeManager.getAmount(firstAccount));
        assertEquals(8_800, this.financeManager.getAmount(secondAccount));

        // eseguo altre transazioni
        final var a = new TransactionImpl("Coop", new CategoryImpl("Spesa", "FFFFFF"),
                new LocalDateTime(2020, 12, 6, 0, 0, 0), firstAccount, -10_000, Repetition.ONCE);
        final var b = new TransactionImpl("Stipendio", new CategoryImpl("Stipendio", "FFFFFF"),
                new LocalDateTime(2020, 12, 6, 0, 0, 0), secondAccount, 100_000, Repetition.ONCE);
        this.financeManager.addTransaction(a);
        this.financeManager.addTransaction(b);

        // controllo importi
        assertEquals(142_250, this.financeManager.getAmount(firstAccount));
        assertEquals(108_800, this.financeManager.getAmount(secondAccount));

        // elimino le ultime due transazioni
        this.financeManager.removeTransaction(a);
        this.financeManager.removeTransaction(b);

        // controllo importi
        assertEquals(152_250, this.financeManager.getAmount(firstAccount));
        assertEquals(8_800, this.financeManager.getAmount(secondAccount));

        // controllo il numero di transazioni positive, negative e abbonamenti
        assertEquals(1, this.financeManager.getTransactionManager().getIncomes().size());
        assertEquals(3, this.financeManager.getTransactionManager().getOutings().size());
        assertEquals(0, this.financeManager.getTransactionManager().getSubscriptions().size());
    }

    @org.junit.Test
    public void testSubscriptions() {
        this.financeManager.addAccount(new AccountImpl("Conto iscrizioni", "FFFFFF", 200_000));

        // creo diverse transazioni ripetute (abbonamenti)
        this.financeManager.addTransaction(new TransactionImpl("Bar Pasquino",
                new CategoryImpl("Ristoranti", "FFFFFF"), new LocalDateTime(2021, 1, 1, 0, 0, 0),
                new AccountImpl("Conto iscrizioni", "FFFFFF", 0), -100, Repetition.DAILY));
        this.financeManager.addTransaction(new TransactionImpl("Abbonamento metro",
                new CategoryImpl("Ristoranti", "FFFFFF"), new LocalDateTime(2021, 1, 1, 0, 0, 0),
                new AccountImpl("Conto iscrizioni", "FFFFFF", 0), -1_000, Repetition.WEEKLY));
        this.financeManager.addTransaction(new TransactionImpl("Tariffa TIM",
                new CategoryImpl("Ristoranti", "FFFFFF"), new LocalDateTime(2021, 3, 3, 0, 0, 0),
                new AccountImpl("Conto iscrizioni", "FFFFFF", 0), -699, Repetition.MONTHLY));
        this.financeManager.addTransaction(new TransactionImpl("Enel",
                new CategoryImpl("Ristoranti", "FFFFFF"), new LocalDateTime(2020, 11, 7, 0, 0, 0),
                new AccountImpl("Conto iscrizioni", "FFFFFF", 200_000), -12_500, Repetition.BIMONTHLY));
        this.financeManager.addTransaction(new TransactionImpl("Acqua",
                new CategoryImpl("Ristoranti", "FFFFFF"), new LocalDateTime(2020, 11, 25, 0, 0, 0),
                new AccountImpl("Conto iscrizioni", "FFFFFF", 200_000), -15_000, Repetition.QUARTERLY));
        this.financeManager.addTransaction(new TransactionImpl("Abbonamento autobus",
                new CategoryImpl("Ristoranti", "FFFFFF"), new LocalDateTime(2020, 6, 5, 0, 0, 0),
                new AccountImpl("Conto iscrizioni", "FFFFFF", 200_000), -39_900, Repetition.HALF_YEARLY));
        this.financeManager.addTransaction(new TransactionImpl("Amazon",
                new CategoryImpl("Ristoranti", "FFFFFF"), new LocalDateTime(2020, 1, 1, 0, 0, 0),
                new AccountImpl("Conto iscrizioni", "FFFFFF", 200_000), -2_400, Repetition.YEARLY));

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

        // stampo
        this.financeManager.getTransactionManager().getTransactions().forEach(System.out::println);

        // controllo che l'importo del conto sia corretto
        assertEquals(29_001, this.financeManager.getAmount(new AccountImpl("Conto iscrizioni", "FFFFFF", 0)));
    }

    @org.junit.Test()
    public void testQuickTransactions() {
        // controllo che siano il numero corretto
        assertEquals(3, this.financeManager.getQuickManager().getQuickTransactions().size());

        // controllo quante transazioni ci sono di base
        assertEquals(2, this.financeManager.getTransactionManager().getTransactions().size());

        // controllo il saldo del conto di riferimento
        assertEquals(149750, this.financeManager.getAmount(new AccountImpl("Conto Corrente", "FFFFFF", 0)));

        // eseguo alcune transazioni rapide
        this.financeManager.doQuickTransaction(new QuickTransactionImpl(-150,
                new CategoryImpl("Spesa", "FFFFFF"),
                new AccountImpl("Conto Corrente", "FFFFFF", 0), "Parcheggio"));
        this.financeManager.doQuickTransaction(new QuickTransactionImpl(-150,
                new CategoryImpl("Spesa", "FFFFFF"),
                new AccountImpl("Conto Corrente", "FFFFFF", 0), "Parcheggio"));
        this.financeManager.doQuickTransaction(new QuickTransactionImpl(-150,
                new CategoryImpl("Spesa", "FFFFFF"),
                new AccountImpl("Conto Corrente", "FFFFFF", 0), "Parcheggio"));
        this.financeManager.doQuickTransaction(new QuickTransactionImpl(-300,
                new CategoryImpl("Bar", "FFFFFF"),
                new AccountImpl("Conto Corrente", "FFFFFF", 0), "Frappé"));
        this.financeManager.doQuickTransaction(new QuickTransactionImpl(-300,
                new CategoryImpl("Bar", "FFFFFF"),
                new AccountImpl("Conto Corrente", "FFFFFF", 0), "Frappé"));
        this.financeManager.doQuickTransaction(new QuickTransactionImpl(1000,
                new CategoryImpl("Stipendio", "FFFFFF"),
                new AccountImpl("Conto Corrente", "FFFFFF", 0), "Paghetta"));

        // controllo che le transazioni siano sei in più
        assertEquals(8, this.financeManager.getTransactionManager().getTransactions().size());

        // controllo che il saldo sia corretto
        assertEquals(149700, this.financeManager.getAmount(new AccountImpl("Conto Corrente", "FFFFFF", 0)));

        // AGGIUNGERE POSSIBILITA' DI MODIFICARE IL CONTO DI UN MOVIMENTO PASSATO O ALTRE COSE MAGARI
    }

    @org.junit.Test()
    public void testGroupTransactions() {
        // mi salvo tutte le persone
        final Person alex = new PersonImpl("Alex", "me");
        final Person luca = new PersonImpl("Luca", "fratello");
        final Person gaia = new PersonImpl("Gaia", "amica");
        final Person maia = new PersonImpl("Maia", "fidanzata");
        final Person elia = new PersonImpl("Elia", "fidanzato");

        // controllo che ci siano tutte le persone nel gruppo
        assertEquals(5, this.groupManager.getGroup().size());

        // controllo che ci siano tutte le transazioni di gruppo
        assertEquals(11, this.groupManager.getTransactions().size());

        // controllo che i crediti e i debiti siano tutti corretti
        assertEquals(-800, this.groupManager.getCredit(alex));
        assertEquals(600, this.groupManager.getCredit(luca));
        assertEquals(-50, this.groupManager.getCredit(gaia));
        assertEquals(100, this.groupManager.getCredit(maia));
        assertEquals(150, this.groupManager.getCredit(elia));

        // provo a eliminare qualche transazione
        this.groupManager.removeTransaction(new GroupTransactionImpl("", luca, List.of(alex, luca), 400, new LocalDate()));
        this.groupManager.removeTransaction(new GroupTransactionImpl("", gaia, List.of(luca), 100, new LocalDate()));
        this.groupManager.removeTransaction(new GroupTransactionImpl("", alex, List.of(alex, gaia), 500, new LocalDate()));

        // eseguo una transazione
        this.groupManager.addTransaction(new GroupTransactionImpl("", alex, List.of(alex, gaia), 200, new LocalDate()));

        // controllo che siano state aggiornate
        assertEquals(9, this.groupManager.getTransactions().size());

        // controllo che i crediti siano cambiati e corretti
        assertEquals(-750, this.groupManager.getCredit(alex));
        assertEquals(500, this.groupManager.getCredit(luca));
        assertEquals(0, this.groupManager.getCredit(gaia));

        // elimino una persona eliminabile
        this.groupManager.removePerson(gaia);

        // controllo quante persone ci sono nel gruppo
        assertEquals(4, this.groupManager.getGroup().size());

        // elimino una persona non eliminabile
        try {
            this.groupManager.removePerson(alex);
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


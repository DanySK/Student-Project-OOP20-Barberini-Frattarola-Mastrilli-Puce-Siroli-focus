package oop.focus.finance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.db.exceptions.DaoAccessException;
import oop.focus.finance.model.*;
import oop.focus.homepage.model.Person;
import oop.focus.homepage.model.PersonImpl;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.List;

public class FinanceTest {

    private final DataSource db = new DataSourceImpl();
    private final FinanceManager financeManager = new FinanceManagerImpl(this.db);
    private final GroupManager groupManager = new GroupManagerImpl(this.db);

    @org.junit.Test
    public void testAccounts() {
        // mi salvo quanti conti e quante transazioni sono già salvate nel database
        int numAcc = this.financeManager.getAccountManager().getAccounts().size();
        int numTra = this.financeManager.getTransactionManager().getTransactions().size();

        // riferimenti ai conti
        var firstAccount = new AccountImpl("Conto1", "ff6666", 150_000);
        var secondAccount = new AccountImpl("Conto2", "ff6666", 10_000);

        // salvo i conti
        this.financeManager.addAccount(firstAccount);
        this.financeManager.addAccount(secondAccount);

        // aggiungo una transazione
        this.financeManager.addTransaction(new TransactionImpl("Transazione1",
                new CategoryImpl("Spesa", ""),
                new LocalDateTime(2020, 1, 1, 0, 0, 0),
                firstAccount, -250, Repetition.ONCE));

        // controlli conti
        assertEquals(numAcc+2, this.financeManager.getAccountManager().getAccounts().size());

        // controlli transazioni
        assertEquals(numTra+1, this.financeManager.getTransactionManager().getTransactions().size());

        // elimino un account
        this.financeManager.removeAccount(firstAccount);

        // controllo che sia stato eliminato da account
        assertEquals(numAcc+1, this.financeManager.getAccountManager().getAccounts().size());

        // controllo che le transazioni relativi a quell'account siano state eliminate
        assertEquals(numTra, this.financeManager.getTransactionManager().getTransactions().size());

        // riporto tutto come da principio
        this.financeManager.removeAccount(secondAccount);

        // controllo che la rimozione sia andata a buon fine
        assertEquals(numAcc, this.financeManager.getAccountManager().getAccounts().size());
    }

    @org.junit.Test()
    public void testCategories() {
        // mi salvo quante categorie, conti e transazioni ho già salvato
        int numCat = this.financeManager.getCategoryManager().getCategories().size();
        int numAcc = this.financeManager.getAccountManager().getAccounts().size();
        int numTra = this.financeManager.getTransactionManager().getTransactions().size();

        // creo delle categorie
        this.financeManager.addCategory(new CategoryImpl("Categoria1", "ff6666"));
        this.financeManager.addCategory(new CategoryImpl("Categoria2", "ff6666"));
        this.financeManager.addCategory(new CategoryImpl("Categoria3", "ff6666"));

        // creo un conto e una transazione
        this.financeManager.addAccount(new AccountImpl("Conto1", "ff6666", 250));
        this.financeManager.addTransaction(new TransactionImpl("Transazione1",
                new CategoryImpl("Categoria1", ""),
                new LocalDateTime(2020, 1, 1, 0, 0, 0),
                new AccountImpl("Conto1", "", 0), -250, Repetition.ONCE));

        // controlli categories
        assertEquals(numCat+3, this.financeManager.getCategoryManager().getCategories().size());

        // provo a rimuovere una categoria di cui esistono transazioni
        try {
            this.financeManager.removeCategory(new CategoryImpl("Categoria1", ""));
            fail();
        } catch (Exception ignored) { }

        // controllo che non siano state rimosse categorie
        assertEquals(numCat+3, this.financeManager.getCategoryManager().getCategories().size());

        // provo a rimuovere le categorie di cui non esistono transazioni
        this.financeManager.removeCategory(new CategoryImpl("Categoria2", ""));
        this.financeManager.removeCategory(new CategoryImpl("Categoria3", ""));

        // controllo che siano state rimosse due categorie
        assertEquals(numCat+1, this.financeManager.getCategoryManager().getCategories().size());

        // riporto tutto come da principio
        this.financeManager.removeAccount(new AccountImpl("Conto1", "", 0));
        this.financeManager.removeCategory(new CategoryImpl("Categoria1", ""));

        // controllo che la rimozione sia andata a buon fine
        assertEquals(numCat, this.financeManager.getCategoryManager().getCategories().size());
        assertEquals(numAcc, this.financeManager.getAccountManager().getAccounts().size());
        assertEquals(numTra, this.financeManager.getTransactionManager().getTransactions().size());
    }

    @org.junit.Test
    public void testTransactions() {
        // mi salvo quanti conti e transazioni ho già salvato
        int numAcc = this.financeManager.getAccountManager().getAccounts().size();
        int numTra = this.financeManager.getTransactionManager().getTransactions().size();
        int numInc = this.financeManager.getTransactionManager().getIncomes().size();
        int numOut = this.financeManager.getTransactionManager().getOutings().size();
        int numSub = this.financeManager.getTransactionManager().getSubscriptions().size();

        // creo un conto
        this.financeManager.addAccount(new AccountImpl("Conto1", "ff6666", 250));

        // creo due transazioni
        this.financeManager.addTransaction(new TransactionImpl("Transazione1",
                new CategoryImpl("Spesa", ""),
                new LocalDateTime(2020, 1, 1, 0, 0, 0),
                new AccountImpl("Conto1", "", 0),
                -250, Repetition.ONCE));
        this.financeManager.addTransaction(new TransactionImpl("Transazione2",
                new CategoryImpl("Spesa", ""),
                new LocalDateTime(2020, 1, 1, 0, 0, 0),
                new AccountImpl("Conto1", "", 0),
                1_000, Repetition.ONCE));

        // controlli transazioni
        assertEquals(numTra+2, this.financeManager.getTransactionManager().getTransactions().size());

        // referenze al conto
        final Account account = new AccountImpl("Conto1", "", 0);

        // controllo importo iniziale
        assertEquals(1_000, this.financeManager.getAmount(account));

        // eseguo altre transazioni
        this.financeManager.addTransaction(new TransactionImpl("Transazione3",
                new CategoryImpl("Spesa", ""),
                new LocalDateTime(2020, 12, 6, 0, 0, 0),
                account,-2_500, Repetition.ONCE));
        this.financeManager.addTransaction(new TransactionImpl("Transazione4",
                new CategoryImpl("Spesa", ""),
                new LocalDateTime(2020, 12, 6, 0, 0, 0),
                account, 7_500, Repetition.ONCE));
        assertEquals(numTra+4, this.financeManager.getTransactionManager().getTransactions().size());

        // controllo importo
        assertEquals(6_000, this.financeManager.getAmount(account));

        // eseguo altre transazioni
        final var fifthTransaction = new TransactionImpl("Transazione5",
                new CategoryImpl("Spesa", ""),
                new LocalDateTime(2020, 12, 6, 0, 0, 0),
                account, -6_000, Repetition.ONCE);
        final var sixthTransaction = new TransactionImpl("Transazione6",
                new CategoryImpl("Spesa", ""),
                new LocalDateTime(2020, 12, 6, 0, 0, 0),
                account, 100_000, Repetition.ONCE);
        this.financeManager.addTransaction(fifthTransaction);
        this.financeManager.addTransaction(sixthTransaction);
        assertEquals(numTra+6, this.financeManager.getTransactionManager().getTransactions().size());

        // controllo importi
        assertEquals(100_000, this.financeManager.getAmount(account));

        // elimino le ultime due transazioni
        this.financeManager.removeTransaction(fifthTransaction);
        this.financeManager.removeTransaction(sixthTransaction);
        assertEquals(numTra+4, this.financeManager.getTransactionManager().getTransactions().size());

        // controllo importi
        assertEquals(6_000, this.financeManager.getAmount(account));

        // controllo il numero di transazioni positive, negative e abbonamenti
        assertEquals(numInc+2, this.financeManager.getTransactionManager().getIncomes().size());
        assertEquals(numOut+2, this.financeManager.getTransactionManager().getOutings().size());
        assertEquals(numSub, this.financeManager.getTransactionManager().getSubscriptions().size());

        // riporto tutto come da principio
        this.financeManager.removeAccount(new AccountImpl("Conto1", "", 0));

        // controllo che la rimozione sia andata a buon fine
        assertEquals(numAcc, this.financeManager.getAccountManager().getAccounts().size());
        assertEquals(numTra, this.financeManager.getTransactionManager().getTransactions().size());
    }

    @org.junit.Test
    public void testSubscriptions() {
        // mi salvo quanti conti e transazioni ho già salvato
        int numAcc = this.financeManager.getAccountManager().getAccounts().size();
        int numTra = this.financeManager.getTransactionManager().getTransactions().size();
        int numSub = this.financeManager.getTransactionManager().getSubscriptions().size();

        // creo un conto
        this.financeManager.addAccount(new AccountImpl("Conto1", "ff6666", 200_000));

        // creo diverse transazioni ripetute (abbonamenti)
        this.financeManager.addTransaction(new TransactionImpl("Abbonamento1",
                new CategoryImpl("Spesa", ""),
                new LocalDateTime(2021, 1, 1, 0, 0, 0),
                new AccountImpl("Conto1", "", 0),
                -100, Repetition.DAILY));
        this.financeManager.addTransaction(new TransactionImpl("Abbonamento2",
                new CategoryImpl("Spesa", ""),
                new LocalDateTime(2021, 1, 1, 0, 0, 0),
                new AccountImpl("Conto1", "", 0),
                -1_000, Repetition.WEEKLY));
        this.financeManager.addTransaction(new TransactionImpl("Abbonamento3",
                new CategoryImpl("Spesa", ""),
                new LocalDateTime(2021, 3, 3, 0, 0, 0),
                new AccountImpl("Conto1", "", 0),
                -699, Repetition.MONTHLY));
        this.financeManager.addTransaction(new TransactionImpl("Abbonamento4",
                new CategoryImpl("Spesa", ""),
                new LocalDateTime(2020, 11, 7, 0, 0, 0),
                new AccountImpl("Conto1", "", 200_000),
                -12_500, Repetition.BIMONTHLY));
        this.financeManager.addTransaction(new TransactionImpl("Abbonamento5",
                new CategoryImpl("Spesa", ""),
                new LocalDateTime(2020, 11, 25, 0, 0, 0),
                new AccountImpl("Conto1", "", 200_000),
                -15_000, Repetition.QUARTERLY));
        this.financeManager.addTransaction(new TransactionImpl("Abbonamento6",
                new CategoryImpl("Spesa", ""),
                new LocalDateTime(2020, 6, 5, 0, 0, 0),
                new AccountImpl("Conto1", "", 200_000),
                -39_900, Repetition.HALF_YEARLY));
        this.financeManager.addTransaction(new TransactionImpl("Abbonamento7",
                new CategoryImpl("Spesa", ""),
                new LocalDateTime(2020, 1, 1, 0, 0, 0),
                new AccountImpl("Conto1", "", 200_000),
                -2_400, Repetition.YEARLY));

        // controllo che siano state aggiunte con successo
        assertEquals(numTra+7, this.financeManager.getTransactionManager().getTransactions().size());
        assertEquals(numSub+7, this.financeManager.getTransactionManager().getSubscriptions().size());

        // controllo spesa totale mensile e annuale
        assertEquals(-26_191, this.financeManager.getTransactionManager().monthlyExpense());
        assertEquals(-314_228, this.financeManager.getTransactionManager().yearlyExpense());

        // faccio creare tutte le transazioni da ripetere
        this.financeManager.generateRepeatedTransactions(new LocalDate(2021, 3, 13));

        // controllo che siano state aggiunte tutte
        assertEquals(numTra+93, this.financeManager.getTransactionManager().getTransactions().size());

        // controllo che l'importo del conto sia corretto
        assertEquals(29_001, this.financeManager.getAmount(new AccountImpl("Conto1", "", 0)));

        // riporto tutto come da principio
        this.financeManager.removeAccount(new AccountImpl("Conto1", "", 0));

        // controllo che la rimozione sia andata a buon fine
        assertEquals(numAcc, this.financeManager.getAccountManager().getAccounts().size());
        assertEquals(numTra, this.financeManager.getTransactionManager().getTransactions().size());
        assertEquals(numSub, this.financeManager.getTransactionManager().getSubscriptions().size());
    }

    @org.junit.Test()
    public void testQuickTransactions() {
        // mi salvo quanti conti e transazioni ho già salvato
        int numAcc = this.financeManager.getAccountManager().getAccounts().size();
        int numTra = this.financeManager.getTransactionManager().getTransactions().size();
        int numQui = this.financeManager.getQuickManager().getQuickTransactions().size();

        // creo un conto
        this.financeManager.addAccount(new AccountImpl("Conto1", "ff6666", 149_750));

        // creo delle transazioni rapide
        this.financeManager.getQuickManager().add(new QuickTransactionImpl(-150,
                new CategoryImpl("Spesa", ""),
                new AccountImpl("Conto1", "", 0),
                "TransazioneRapida1"));
        this.financeManager.getQuickManager().add(new QuickTransactionImpl(-300,
                new CategoryImpl("Spesa", ""),
                new AccountImpl("Conto1", "", 0),
                "TransazioneRapida2"));
        this.financeManager.getQuickManager().add(new QuickTransactionImpl(1000,
                new CategoryImpl("Spesa", ""),
                new AccountImpl("Conto1", "", 0),
                "TransazioneRapida3"));

        // controllo che siano il numero corretto
        assertEquals(numQui+3, this.financeManager.getQuickManager().getQuickTransactions().size());

        // mi salvo riferimento al conto
        var account = new AccountImpl("Conto1", "", 0);

        // controllo il saldo del conto di riferimento
        assertEquals(149_750, this.financeManager.getAmount(account));

        // eseguo alcune transazioni rapide
        this.financeManager.doQuickTransaction(new QuickTransactionImpl(-150,
                new CategoryImpl("Spesa", ""), account, "TransazioneRapida1"));
        this.financeManager.doQuickTransaction(new QuickTransactionImpl(-150,
                new CategoryImpl("Spesa", ""), account, "TransazioneRapida1"));
        this.financeManager.doQuickTransaction(new QuickTransactionImpl(-150,
                new CategoryImpl("Spesa", ""), account, "TransazioneRapida1"));
        this.financeManager.doQuickTransaction(new QuickTransactionImpl(-300,
                new CategoryImpl("Spesa", ""), account, "TransazioneRapida2"));
        this.financeManager.doQuickTransaction(new QuickTransactionImpl(-300,
                new CategoryImpl("Spesa", ""), account, "TransazioneRapida2"));
        this.financeManager.doQuickTransaction(new QuickTransactionImpl(1000,
                new CategoryImpl("Spesa", ""), account, "TransazioneRapida3"));

        // controllo che le transazioni siano sei in più
        assertEquals(numTra+6, this.financeManager.getTransactionManager().getTransactions().size());

        // controllo che il saldo sia corretto
        assertEquals(149700, this.financeManager.getAmount(account));

        // riporto tutto come da principio
        this.financeManager.removeAccount(new AccountImpl("Conto1", "", 0));
        this.financeManager.getQuickManager().remove(new QuickTransactionImpl(-150,
                new CategoryImpl("Spesa", ""),
                new AccountImpl("Conto1", "", 0),
                "TransazioneRapida1"));
        this.financeManager.getQuickManager().remove(new QuickTransactionImpl(-300,
                new CategoryImpl("Spesa", ""),
                new AccountImpl("Conto1", "", 0),
                "TransazioneRapida2"));
        this.financeManager.getQuickManager().remove(new QuickTransactionImpl(1000,
                new CategoryImpl("Spesa", ""),
                new AccountImpl("Conto1", "", 0),
                "TransazioneRapida3"));

        // controllo che la rimozione sia andata a buon fine
        assertEquals(numAcc, this.financeManager.getAccountManager().getAccounts().size());
        assertEquals(numTra, this.financeManager.getTransactionManager().getTransactions().size());
        assertEquals(numQui, this.financeManager.getQuickManager().getQuickTransactions().size());
    }

    @org.junit.Test()
    public void testGroupTransactions() {
        // mi salvo quante persone e transazioni di gruppo ho già salvato
        int numPer = this.groupManager.getGroup().size();
        int numTra = this.groupManager.getTransactions().size();

        // aggiungo le persone a persons
        var persons = this.db.getPersons();
        final Person Persona1 = new PersonImpl("Persona1", "amico");
        final Person Persona2 = new PersonImpl("Persona2", "amico");
        final Person Persona3 = new PersonImpl("Persona3", "amico");
        final Person Persona4 = new PersonImpl("Persona4", "amico");
        final Person Persona5 = new PersonImpl("Persona5", "amico");
        try {
            persons.save(Persona1);
            persons.save(Persona2);
            persons.save(Persona3);
            persons.save(Persona4);
            persons.save(Persona5);
        } catch (DaoAccessException e) {
            fail();
        }
        this.groupManager.addPerson(Persona1);
        this.groupManager.addPerson(Persona2);
        this.groupManager.addPerson(Persona3);
        this.groupManager.addPerson(Persona4);
        this.groupManager.addPerson(Persona5);

        // controllo che ci siano tutte le persone nel gruppo
        assertEquals(numPer+5, this.groupManager.getGroup().size());

        // aggiungo alcune transazioni di gruppo
        GroupTransactionImpl Transazione1 = new GroupTransactionImpl("Transazione1",
                Persona1, List.of(Persona1, Persona3), 500, new LocalDate());
        GroupTransactionImpl Transazione2 = new GroupTransactionImpl("Transazione2",
                Persona2, List.of(Persona1, Persona2), 1000, new LocalDate());
        GroupTransactionImpl Transazione3 = new GroupTransactionImpl("Transazione3",
                Persona2, List.of(Persona1, Persona3), 300, new LocalDate());
        GroupTransactionImpl Transazione4 = new GroupTransactionImpl("Transazione4",
                Persona1, List.of(Persona1), 300, new LocalDate());
        GroupTransactionImpl Transazione5 = new GroupTransactionImpl("Transazione5",
                Persona3, List.of(Persona2), 100, new LocalDate());
        GroupTransactionImpl Transazione6 = new GroupTransactionImpl("Transazion6",
                Persona1, List.of(Persona1, Persona2, Persona3), 600, new LocalDate());
        GroupTransactionImpl Transazione7 = new GroupTransactionImpl("Transazione7",
                Persona3, List.of(Persona1, Persona2), 200, new LocalDate());
        GroupTransactionImpl Transazione8 = new GroupTransactionImpl("Transazione8",
                Persona2, List.of(Persona1, Persona2), 400, new LocalDate());
        GroupTransactionImpl Transazione9 = new GroupTransactionImpl("Transazione9",
                Persona3, List.of(Persona1, Persona3), 500, new LocalDate());
        GroupTransactionImpl Transazione10 = new GroupTransactionImpl("Transazione10",
                Persona4, List.of(Persona1), 100, new LocalDate());
        GroupTransactionImpl Transazione11 = new GroupTransactionImpl("Transazione11",
                Persona5, List.of(Persona1), 150, new LocalDate());
        GroupTransactionImpl Transazione12 = new GroupTransactionImpl("Transazione12",
                Persona1, List.of(Persona1, Persona3), 200, new LocalDate());
        this.groupManager.addTransaction(Transazione1);
        this.groupManager.addTransaction(Transazione2);
        this.groupManager.addTransaction(Transazione3);
        this.groupManager.addTransaction(Transazione4);
        this.groupManager.addTransaction(Transazione5);
        this.groupManager.addTransaction(Transazione6);
        this.groupManager.addTransaction(Transazione7);
        this.groupManager.addTransaction(Transazione8);
        this.groupManager.addTransaction(Transazione9);
        this.groupManager.addTransaction(Transazione10);
        this.groupManager.addTransaction(Transazione11);

        // controllo che ci siano tutte le transazioni di gruppo
        assertEquals(numTra+11, this.groupManager.getTransactions().size());

        // controllo che i crediti e i debiti siano tutti corretti
        assertEquals(-800, this.groupManager.getCredit(Persona1));
        assertEquals(600, this.groupManager.getCredit(Persona2));
        assertEquals(-50, this.groupManager.getCredit(Persona3));
        assertEquals(100, this.groupManager.getCredit(Persona4));
        assertEquals(150, this.groupManager.getCredit(Persona5));

        // provo a eliminare qualche transazione
        this.groupManager.removeTransaction(Transazione1);
        this.groupManager.removeTransaction(Transazione5);
        this.groupManager.removeTransaction(Transazione8);

        // eseguo una transazione
        this.groupManager.addTransaction(Transazione12);

        // controllo che siano state aggiornate
        assertEquals(numTra+9, this.groupManager.getTransactions().size());

        // controllo che i crediti siano cambiati e corretti
        assertEquals(-750, this.groupManager.getCredit(Persona1));
        assertEquals(500, this.groupManager.getCredit(Persona2));
        assertEquals(0, this.groupManager.getCredit(Persona3));

        // elimino una persona eliminabile
        this.groupManager.removePerson(Persona3);

        // controllo quante persone ci sono nel gruppo
        assertEquals(numPer+4, this.groupManager.getGroup().size());

        // elimino una persona non eliminabile
        try {
            this.groupManager.removePerson(Persona1);
            fail();
        } catch (IllegalStateException ignored) { }

        // controllo quante persone ci sono nel gruppo
        assertEquals(numPer+4, this.groupManager.getGroup().size());

        // richiedo la soluzione dei debiti
        var solution = this.groupManager.resolve();

        // eseguo le transazioni di risoluzione
        solution.forEach(this.groupManager::addTransaction);

        // controllo che nessuno abbia più debiti
        this.groupManager.getGroup().forEach(p -> assertEquals(0, this.groupManager.getCredit(p)));

        // riporto tutto come da principio
        solution.forEach(this.groupManager::removeTransaction);
        this.groupManager.removeTransaction(Transazione2);
        this.groupManager.removeTransaction(Transazione3);
        this.groupManager.removeTransaction(Transazione4);
        this.groupManager.removeTransaction(Transazione6);
        this.groupManager.removeTransaction(Transazione7);
        this.groupManager.removeTransaction(Transazione9);
        this.groupManager.removeTransaction(Transazione10);
        this.groupManager.removeTransaction(Transazione11);
        this.groupManager.removeTransaction(Transazione12);
        this.groupManager.removePerson(Persona1);
        this.groupManager.removePerson(Persona2);
        this.groupManager.removePerson(Persona4);
        this.groupManager.removePerson(Persona5);

        // controllo che la rimozione sia andata a buon fine
        assertEquals(numPer, this.groupManager.getGroup().size());
        assertEquals(numTra, this.groupManager.getTransactions().size());
    }

}


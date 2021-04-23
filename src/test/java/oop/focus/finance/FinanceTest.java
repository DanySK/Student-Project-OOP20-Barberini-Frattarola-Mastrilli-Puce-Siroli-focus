package oop.focus.finance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import oop.focus.common.Repetition;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.db.exceptions.DaoAccessException;

import oop.focus.finance.model.Account;
import oop.focus.finance.model.AccountImpl;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.FinanceManagerImpl;
import oop.focus.finance.model.TransactionImpl;
import oop.focus.finance.model.CategoryImpl;
import oop.focus.finance.model.QuickTransactionImpl;
import oop.focus.finance.model.GroupTransactionImpl;
import oop.focus.calendar.persons.model.Person;
import oop.focus.calendar.persons.model.PersonImpl;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.List;

/**
 * Class that tests everything related to the logic part of the finance section.
 */
public class FinanceTest {

    private static final String RELATIONSHIP = "amico";
    private static final String CATEGORY = "Spesa";
    private static final String COLOR = "ff6666";
    private final DataSource db = new DataSourceImpl();
    private final FinanceManager financeManager = new FinanceManagerImpl(this.db);

    @org.junit.Test
    public void testAccounts() {
        // mi salvo quanti conti e quante transazioni sono già salvate nel database
        final int numAcc = this.financeManager.getAccountManager().getElements().size();
        final int numTra = this.financeManager.getTransactionManager().getElements().size();

        // riferimenti ai conti
        final Account firstAccount = new AccountImpl("Conto1", COLOR, 150_000);
        final Account secondAccount = new AccountImpl("Conto2", COLOR, 10_000);

        // salvo i conti
        this.financeManager.addAccount(firstAccount);
        this.financeManager.addAccount(secondAccount);

        // aggiungo una transazione
        this.financeManager.addTransaction(new TransactionImpl("Transazione1",
                new CategoryImpl(CATEGORY, ""),
                new LocalDateTime(2020, 1, 1, 0, 0, 0),
                firstAccount, -250, Repetition.ONCE));

        // controlli conti
        assertEquals(numAcc+2, this.financeManager.getAccountManager().getElements().size());

        // controlli transazioni
        assertEquals(numTra+1, this.financeManager.getTransactionManager().getElements().size());

        // elimino un account
        this.financeManager.removeAccount(firstAccount);

        // controllo che sia stato eliminato da account
        assertEquals(numAcc+1, this.financeManager.getAccountManager().getElements().size());

        // controllo che le transazioni relativi a quell'account siano state eliminate
        assertEquals(numTra, this.financeManager.getTransactionManager().getElements().size());

        // riporto tutto come da principio
        this.financeManager.removeAccount(secondAccount);

        // controllo che la rimozione sia andata a buon fine
        assertEquals(numAcc, this.financeManager.getAccountManager().getElements().size());
    }

    @org.junit.Test()
    public void testCategories() {
        // mi salvo quante categorie, conti e transazioni ho già salvato
        final int numCat = this.financeManager.getCategoryManager().getElements().size();
        final int numAcc = this.financeManager.getAccountManager().getElements().size();
        final int numTra = this.financeManager.getTransactionManager().getElements().size();

        // creo delle categorie
        this.financeManager.addCategory(new CategoryImpl("Categoria1", COLOR));
        this.financeManager.addCategory(new CategoryImpl("Categoria2", COLOR));
        this.financeManager.addCategory(new CategoryImpl("Categoria3", COLOR));

        // creo un conto e una transazione
        this.financeManager.addAccount(new AccountImpl("Conto1", COLOR, 250));
        this.financeManager.addTransaction(new TransactionImpl("Transazione1",
                new CategoryImpl("Categoria1", ""),
                new LocalDateTime(2020, 1, 1, 0, 0, 0),
                new AccountImpl("Conto1", "", 0), -250, Repetition.ONCE));

        // controlli categories
        assertEquals(numCat+3, this.financeManager.getCategoryManager().getElements().size());

        // provo a rimuovere una categoria di cui esistono transazioni
        try {
            this.financeManager.removeCategory(new CategoryImpl("Categoria1", ""));
            fail();
        } catch (final IllegalStateException ignored) { }

        // controllo che non siano state rimosse categorie
        assertEquals(numCat+3, this.financeManager.getCategoryManager().getElements().size());

        // provo a rimuovere le categorie di cui non esistono transazioni
        this.financeManager.removeCategory(new CategoryImpl("Categoria2", ""));
        this.financeManager.removeCategory(new CategoryImpl("Categoria3", ""));

        // controllo che siano state rimosse due categorie
        assertEquals(numCat+1, this.financeManager.getCategoryManager().getElements().size());

        // riporto tutto come da principio
        this.financeManager.removeAccount(new AccountImpl("Conto1", "", 0));
        this.financeManager.removeCategory(new CategoryImpl("Categoria1", ""));

        // controllo che la rimozione sia andata a buon fine
        assertEquals(numCat, this.financeManager.getCategoryManager().getElements().size());
        assertEquals(numAcc, this.financeManager.getAccountManager().getElements().size());
        assertEquals(numTra, this.financeManager.getTransactionManager().getElements().size());
    }

    @org.junit.Test
    public void testTransactions() {
        // mi salvo quanti conti e transazioni ho già salvato
        final int numAcc = this.financeManager.getAccountManager().getElements().size();
        final int numTra = this.financeManager.getTransactionManager().getElements().size();
        final int numSub = this.financeManager.getTransactionManager().getSubscriptions().size();

        // creo un conto
        this.financeManager.addAccount(new AccountImpl("Conto1", COLOR, 250));

        // creo due transazioni
        this.financeManager.addTransaction(new TransactionImpl("Transazione1",
                new CategoryImpl(CATEGORY, ""),
                new LocalDateTime(2020, 1, 1, 0, 0, 0),
                new AccountImpl("Conto1", "", 0),
                -250, Repetition.ONCE));
        this.financeManager.addTransaction(new TransactionImpl("Transazione2",
                new CategoryImpl(CATEGORY, ""),
                new LocalDateTime(2020, 1, 1, 0, 0, 0),
                new AccountImpl("Conto1", "", 0),
                1_000, Repetition.ONCE));

        // controlli transazioni
        assertEquals(numTra+2, this.financeManager.getTransactionManager().getElements().size());

        // referenze al conto
        final Account account = new AccountImpl("Conto1", "", 0);

        // controllo importo iniziale
        assertEquals(1_000, this.financeManager.getAmount(account));

        // eseguo altre transazioni
        this.financeManager.addTransaction(new TransactionImpl("Transazione3",
                new CategoryImpl(CATEGORY, ""),
                new LocalDateTime(2020, 12, 6, 0, 0, 0),
                account,-2_500, Repetition.ONCE));
        this.financeManager.addTransaction(new TransactionImpl("Transazione4",
                new CategoryImpl(CATEGORY, ""),
                new LocalDateTime(2020, 12, 6, 0, 0, 0),
                account, 7_500, Repetition.ONCE));
        assertEquals(numTra+4, this.financeManager.getTransactionManager().getElements().size());

        // controllo importo
        assertEquals(6_000, this.financeManager.getAmount(account));

        // eseguo altre transazioni
        final var fifthTransaction = new TransactionImpl("Transazione5",
                new CategoryImpl(CATEGORY, ""),
                new LocalDateTime(2020, 12, 6, 0, 0, 0),
                account, -6_000, Repetition.ONCE);
        final var sixthTransaction = new TransactionImpl("Transazione6",
                new CategoryImpl(CATEGORY, ""),
                new LocalDateTime(2020, 12, 6, 0, 0, 0),
                account, 100_000, Repetition.ONCE);
        this.financeManager.addTransaction(fifthTransaction);
        this.financeManager.addTransaction(sixthTransaction);
        assertEquals(numTra+6, this.financeManager.getTransactionManager().getElements().size());

        // controllo importi
        assertEquals(100_000, this.financeManager.getAmount(account));

        // elimino le ultime due transazioni
        this.financeManager.removeTransaction(fifthTransaction);
        this.financeManager.removeTransaction(sixthTransaction);
        assertEquals(numTra+4, this.financeManager.getTransactionManager().getElements().size());

        // controllo importi
        assertEquals(6_000, this.financeManager.getAmount(account));

        // controllo il numero di transazioni positive, negative e abbonamenti
        assertEquals(numSub, this.financeManager.getTransactionManager().getSubscriptions().size());

        // riporto tutto come da principio
        this.financeManager.removeAccount(new AccountImpl("Conto1", "", 0));

        // controllo che la rimozione sia andata a buon fine
        assertEquals(numAcc, this.financeManager.getAccountManager().getElements().size());
        assertEquals(numTra, this.financeManager.getTransactionManager().getElements().size());
    }

    @org.junit.Test
    public void testSubscriptions() {
        // mi salvo quanti conti e transazioni ho già salvato
        final int numAcc = this.financeManager.getAccountManager().getElements().size();
        final int numTra = this.financeManager.getTransactionManager().getElements().size();
        final int numSub = this.financeManager.getTransactionManager().getSubscriptions().size();
        final int monthlyExp = this.financeManager.getTransactionManager().monthlyExpense();
        final int yearlyExp = this.financeManager.getTransactionManager().yearlyExpense();

        // creo un conto
        this.financeManager.addAccount(new AccountImpl("Conto1", COLOR, 200_000));

        // creo diverse transazioni ripetute (abbonamenti)
        this.financeManager.addTransaction(new TransactionImpl("Abbonamento1",
                new CategoryImpl(CATEGORY, ""),
                new LocalDateTime(2021, 1, 1, 0, 0, 0),
                new AccountImpl("Conto1", "", 0),
                -100, Repetition.DAILY));
        this.financeManager.addTransaction(new TransactionImpl("Abbonamento2",
                new CategoryImpl(CATEGORY, ""),
                new LocalDateTime(2021, 1, 1, 0, 0, 0),
                new AccountImpl("Conto1", "", 0),
                -1_000, Repetition.WEEKLY));
        this.financeManager.addTransaction(new TransactionImpl("Abbonamento3",
                new CategoryImpl(CATEGORY, ""),
                new LocalDateTime(2021, 3, 3, 0, 0, 0),
                new AccountImpl("Conto1", "", 0),
                -699, Repetition.MONTHLY));
        this.financeManager.addTransaction(new TransactionImpl("Abbonamento4",
                new CategoryImpl(CATEGORY, ""),
                new LocalDateTime(2020, 11, 7, 0, 0, 0),
                new AccountImpl("Conto1", "", 200_000),
                -12_500, Repetition.BIMONTHLY));
        this.financeManager.addTransaction(new TransactionImpl("Abbonamento5",
                new CategoryImpl(CATEGORY, ""),
                new LocalDateTime(2020, 11, 25, 0, 0, 0),
                new AccountImpl("Conto1", "", 200_000),
                -15_000, Repetition.QUARTERLY));
        this.financeManager.addTransaction(new TransactionImpl("Abbonamento6",
                new CategoryImpl(CATEGORY, ""),
                new LocalDateTime(2020, 6, 5, 0, 0, 0),
                new AccountImpl("Conto1", "", 200_000),
                -39_900, Repetition.HALF_YEARLY));
        this.financeManager.addTransaction(new TransactionImpl("Abbonamento7",
                new CategoryImpl(CATEGORY, ""),
                new LocalDateTime(2020, 1, 1, 0, 0, 0),
                new AccountImpl("Conto1", "", 200_000),
                -2_400, Repetition.YEARLY));

        // controllo che siano state aggiunte con successo
        assertEquals(numTra+7, this.financeManager.getTransactionManager().getElements().size());
        assertEquals(numSub+7, this.financeManager.getTransactionManager().getSubscriptions().size());

        // controllo spesa totale mensile e annuale
        assertEquals(monthlyExp-26_191, this.financeManager.getTransactionManager().monthlyExpense());
        assertEquals(yearlyExp-314_228, this.financeManager.getTransactionManager().yearlyExpense());

        // faccio creare tutte le transazioni da ripetere
        this.financeManager.generateRepeatedTransactions(new LocalDate(2021, 3, 13));

        // controllo che siano state aggiunte tutte
        assertEquals(numTra+93, this.financeManager.getTransactionManager().getElements().size());

        // controllo che l'importo del conto sia corretto
        assertEquals(29_001, this.financeManager.getAmount(new AccountImpl("Conto1", "", 0)));

        // riporto tutto come da principio
        this.financeManager.removeAccount(new AccountImpl("Conto1", "", 0));

        // controllo che la rimozione sia andata a buon fine
        assertEquals(numAcc, this.financeManager.getAccountManager().getElements().size());
        assertEquals(numTra, this.financeManager.getTransactionManager().getElements().size());
        assertEquals(numSub, this.financeManager.getTransactionManager().getSubscriptions().size());
    }

    @org.junit.Test()
    public void testQuickTransactions() {
        // mi salvo quanti conti e transazioni ho già salvato
        final int numAcc = this.financeManager.getAccountManager().getElements().size();
        final int numTra = this.financeManager.getTransactionManager().getElements().size();
        final int numQui = this.financeManager.getQuickManager().getElements().size();

        // creo un conto
        this.financeManager.addAccount(new AccountImpl("Conto1", COLOR, 149_750));

        // creo delle transazioni rapide
        this.financeManager.getQuickManager().add(new QuickTransactionImpl(-150,
                "TransazioneRapida1", new CategoryImpl(CATEGORY, ""),
                new AccountImpl("Conto1", "", 0)));
        this.financeManager.getQuickManager().add(new QuickTransactionImpl(-300,
                "TransazioneRapida2", new CategoryImpl(CATEGORY, ""),
                new AccountImpl("Conto1", "", 0)));
        this.financeManager.getQuickManager().add(new QuickTransactionImpl(1000,
                "TransazioneRapida3", new CategoryImpl(CATEGORY, ""),
                new AccountImpl("Conto1", "", 0)));

        // controllo che siano il numero corretto
        assertEquals(numQui+3, this.financeManager.getQuickManager().getElements().size());

        // mi salvo riferimento al conto
        final var account = new AccountImpl("Conto1", "", 0);

        // controllo il saldo del conto di riferimento
        assertEquals(149_750, this.financeManager.getAmount(account));

        // eseguo alcune transazioni rapide
        this.financeManager.doQuickTransaction(new QuickTransactionImpl(-150,
                "TransazioneRapida1", new CategoryImpl(CATEGORY, ""), account));
        this.financeManager.doQuickTransaction(new QuickTransactionImpl(-300,
                "TransazioneRapida2", new CategoryImpl(CATEGORY, ""), account));
        this.financeManager.doQuickTransaction(new QuickTransactionImpl(1000,
                "TransazioneRapida3", new CategoryImpl(CATEGORY, ""), account));

        // controllo che le transazioni siano sei in più
        assertEquals(numTra+3, this.financeManager.getTransactionManager().getElements().size());

        // controllo che il saldo sia corretto
        assertEquals(150_300, this.financeManager.getAmount(account));

        // riporto tutto come da principio
        this.financeManager.removeAccount(new AccountImpl("Conto1", "", 0));
        this.financeManager.getQuickManager().remove(new QuickTransactionImpl(-150,
                "TransazioneRapida1", new CategoryImpl(CATEGORY, ""),
                new AccountImpl("Conto1", "", 0)));
        this.financeManager.getQuickManager().remove(new QuickTransactionImpl(-300,
                "TransazioneRapida2", new CategoryImpl(CATEGORY, ""),
                new AccountImpl("Conto1", "", 0)));
        this.financeManager.getQuickManager().remove(new QuickTransactionImpl(1000,
                "TransazioneRapida3", new CategoryImpl(CATEGORY, ""),
                new AccountImpl("Conto1", "", 0)));

        // controllo che la rimozione sia andata a buon fine
        assertEquals(numAcc, this.financeManager.getAccountManager().getElements().size());
        assertEquals(numTra, this.financeManager.getTransactionManager().getElements().size());
        assertEquals(numQui, this.financeManager.getQuickManager().getElements().size());
    }

    @org.junit.Test()
    public void testGroupTransactions() {
        // mi salvo quante persone e transazioni di gruppo ho già salvato
        final int numPer = this.financeManager.getGroupManager().getGroup().size();
        final int numTra = this.financeManager.getGroupManager().getTransactions().size();

        // aggiungo le persone a persons
        final var persons = this.db.getPersons();
        final Person persona1 = new PersonImpl("Persona1", RELATIONSHIP);
        final Person persona2 = new PersonImpl("Persona2", RELATIONSHIP);
        final Person persona3 = new PersonImpl("Persona3", RELATIONSHIP);
        final Person persona4 = new PersonImpl("Persona4", RELATIONSHIP);
        final Person persona5 = new PersonImpl("Persona5", RELATIONSHIP);
        try {
            persons.save(persona1);
            persons.save(persona2);
            persons.save(persona3);
            persons.save(persona4);
            persons.save(persona5);
        } catch (final DaoAccessException e) {
            fail();
        }
        this.financeManager.getGroupManager().addPerson(persona1);
        this.financeManager.getGroupManager().addPerson(persona2);
        this.financeManager.getGroupManager().addPerson(persona3);
        this.financeManager.getGroupManager().addPerson(persona4);
        this.financeManager.getGroupManager().addPerson(persona5);

        // controllo che ci siano tutte le persone nel gruppo
        assertEquals(numPer+5, this.financeManager.getGroupManager().getGroup().size());

        // aggiungo alcune transazioni di gruppo
        final GroupTransactionImpl transazione1 = new GroupTransactionImpl("Transazione1",
                persona1, List.of(persona1, persona3), 500, new LocalDateTime());
        final GroupTransactionImpl transazione2 = new GroupTransactionImpl("Transazione2",
                persona2, List.of(persona1, persona2), 1000, new LocalDateTime());
        final GroupTransactionImpl transazione3 = new GroupTransactionImpl("Transazione3",
                persona2, List.of(persona1, persona3), 300, new LocalDateTime());
        final GroupTransactionImpl transazione4 = new GroupTransactionImpl("Transazione4",
                persona1, List.of(persona1), 300, new LocalDateTime());
        final GroupTransactionImpl transazione5 = new GroupTransactionImpl("Transazione5",
                persona3, List.of(persona2), 100, new LocalDateTime());
        final GroupTransactionImpl transazione6 = new GroupTransactionImpl("Transazion6",
                persona1, List.of(persona1, persona2, persona3), 600, new LocalDateTime());
        final GroupTransactionImpl transazione7 = new GroupTransactionImpl("Transazione7",
                persona3, List.of(persona1, persona2), 200, new LocalDateTime());
        final GroupTransactionImpl transazione8 = new GroupTransactionImpl("Transazione8",
                persona2, List.of(persona1, persona2), 400, new LocalDateTime());
        final GroupTransactionImpl transazione9 = new GroupTransactionImpl("Transazione9",
                persona3, List.of(persona1, persona3), 500, new LocalDateTime());
        final GroupTransactionImpl transazione10 = new GroupTransactionImpl("Transazione10",
                persona4, List.of(persona1), 100, new LocalDateTime());
        final GroupTransactionImpl transazione11 = new GroupTransactionImpl("Transazione11",
                persona5, List.of(persona1), 150, new LocalDateTime());
        final GroupTransactionImpl transazione12 = new GroupTransactionImpl("Transazione12",
                persona1, List.of(persona1, persona3), 200, new LocalDateTime());
        this.financeManager.getGroupManager().addTransaction(transazione1);
        this.financeManager.getGroupManager().addTransaction(transazione2);
        this.financeManager.getGroupManager().addTransaction(transazione3);
        this.financeManager.getGroupManager().addTransaction(transazione4);
        this.financeManager.getGroupManager().addTransaction(transazione5);
        this.financeManager.getGroupManager().addTransaction(transazione6);
        this.financeManager.getGroupManager().addTransaction(transazione7);
        this.financeManager.getGroupManager().addTransaction(transazione8);
        this.financeManager.getGroupManager().addTransaction(transazione9);
        this.financeManager.getGroupManager().addTransaction(transazione10);
        this.financeManager.getGroupManager().addTransaction(transazione11);

        // controllo che ci siano tutte le transazioni di gruppo
        assertEquals(numTra+11, this.financeManager.getGroupManager().getTransactions().size());

        // controllo che i crediti e i debiti siano tutti corretti
        assertEquals(-800, this.financeManager.getGroupManager().getCredit(persona1));
        assertEquals(600, this.financeManager.getGroupManager().getCredit(persona2));
        assertEquals(-50, this.financeManager.getGroupManager().getCredit(persona3));
        assertEquals(100, this.financeManager.getGroupManager().getCredit(persona4));
        assertEquals(150, this.financeManager.getGroupManager().getCredit(persona5));

        // provo a eliminare qualche transazione
        this.financeManager.getGroupManager().removeTransaction(transazione1);
        this.financeManager.getGroupManager().removeTransaction(transazione5);
        this.financeManager.getGroupManager().removeTransaction(transazione8);

        // eseguo una transazione
        this.financeManager.getGroupManager().addTransaction(transazione12);

        // controllo che siano state aggiornate
        assertEquals(numTra+9, this.financeManager.getGroupManager().getTransactions().size());

        // controllo che i crediti siano cambiati e corretti
        assertEquals(-750, this.financeManager.getGroupManager().getCredit(persona1));
        assertEquals(500, this.financeManager.getGroupManager().getCredit(persona2));
        assertEquals(0, this.financeManager.getGroupManager().getCredit(persona3));

        // elimino una persona eliminabile
        this.financeManager.getGroupManager().removePerson(persona3);

        // controllo quante persone ci sono nel gruppo
        assertEquals(numPer+4, this.financeManager.getGroupManager().getGroup().size());

        // elimino una persona non eliminabile
        try {
            this.financeManager.getGroupManager().removePerson(persona1);
            fail();
        } catch (final IllegalStateException ignored) { }

        // controllo quante persone ci sono nel gruppo
        assertEquals(numPer+4, this.financeManager.getGroupManager().getGroup().size());

        // richiedo la soluzione dei debiti
        final var solution = this.financeManager.getGroupManager().resolveList();

        // eseguo le transazioni di risoluzione
        solution.forEach(this.financeManager.getGroupManager()::addTransaction);

        // controllo che nessuno abbia più debiti
        this.financeManager.getGroupManager().getGroup().forEach(p -> assertEquals(0, this.financeManager.getGroupManager().getCredit(p)));

        // riporto tutto come da principio
        solution.forEach(this.financeManager.getGroupManager()::removeTransaction);
        this.financeManager.getGroupManager().removeTransaction(transazione2);
        this.financeManager.getGroupManager().removeTransaction(transazione3);
        this.financeManager.getGroupManager().removeTransaction(transazione4);
        this.financeManager.getGroupManager().removeTransaction(transazione6);
        this.financeManager.getGroupManager().removeTransaction(transazione7);
        this.financeManager.getGroupManager().removeTransaction(transazione9);
        this.financeManager.getGroupManager().removeTransaction(transazione10);
        this.financeManager.getGroupManager().removeTransaction(transazione11);
        this.financeManager.getGroupManager().removeTransaction(transazione12);
        this.financeManager.getGroupManager().removePerson(persona1);
        this.financeManager.getGroupManager().removePerson(persona2);
        this.financeManager.getGroupManager().removePerson(persona4);
        this.financeManager.getGroupManager().removePerson(persona5);
        try {
            persons.delete(persona1);
            persons.delete(persona2);
            persons.delete(persona3);
            persons.delete(persona4);
            persons.delete(persona5);
        } catch (final DaoAccessException e) {
            fail();
        }

        // controllo che la rimozione sia andata a buon fine
        assertEquals(numPer, this.financeManager.getGroupManager().getGroup().size());
        assertEquals(numTra, this.financeManager.getGroupManager().getTransactions().size());
    }

}


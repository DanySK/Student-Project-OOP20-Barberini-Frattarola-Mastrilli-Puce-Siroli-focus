package oop.focus.db;

import oop.focus.db.exceptions.DaoAccessException;
import oop.focus.diary.model.DailyMood;
import oop.focus.diary.model.DailyMoodImpl;
import oop.focus.diary.model.ToDoAction;
import oop.focus.diary.model.ToDoActionImpl;
import oop.focus.fidelitycard.FidelityCard;
import oop.focus.fidelitycard.FidelityCardImpl;
import oop.focus.fidelitycard.FidelityCardType;
import oop.focus.finance.*;
import oop.focus.homepage.model.*;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class DataTypeTest {
    @Test
    public void testGetAllAndDelete() {
        try {
            DataSourceFactory df = new DataSourceFactoryImpl();
            var db = df.getRelationships();
            int size = db.getAll().size();
            db.save("Nuovo");
            assertEquals(db.getAll().size(), size + 1);
            db.delete("Nuovo");
            assertEquals(db.getAll().size(), size);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testGetAndDelete() {
        var df = new DataSourceFactoryImpl();
        var relationships = df.getRelationships();
        var persons = df.getPersons();
        try {
            relationships.save("figlio");
            assertEquals(persons.getAll().size(), 0);
            persons.save(new PersonImpl("marco", "figlio"));
            assertEquals(persons.getAll().size(), 1);
            persons.save(new PersonImpl("Luca", "figlio"));
            assertEquals(persons.getAll().size(), 2);
            persons.delete(persons.getAll().get(0));
            assertEquals(persons.getAll().size(), 1);
            persons.delete(persons.getAll().get(0));
            assertEquals(persons.getAll().size(), 0);
            relationships.delete("figlio");
        } catch (DaoAccessException e) {
            fail();
            e.printStackTrace();
        }
        try {
            persons.delete(new PersonImpl("Giovanni", "figlio"));
            fail();
        } catch (Exception e) {
            // success
        }
    }

    @Test
    public void testColors() {
        var db = new DataSourceFactoryImpl();
        var colors = db.getColors();
        try {
            assertEquals(colors.getAll().size(), 0);
            colors.save("ffffff");
            assertEquals(colors.getAll().size(), 1);
            colors.delete("ffffff");
            assertEquals(colors.getAll().size(), 0);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        try {
            colors.save("ffffffffff"); // invalid arg
            fail();
        } catch (Exception e) {
            //
        }
    }

    @Test
    public void testCategories() {
        var db = new DataSourceFactoryImpl();
        var categories = db.getCategories();
        var colors = db.getColors();
        Category c = null;
        try {
            String c1 = "ffffff", c2 = "000000";
            colors.save(c2);
            colors.save(c1);
            List<Category> vars = List.of(new CategoryImpl("Cibo ", colors.getAll().get(0)),
                    new CategoryImpl("Palestra", colors.getAll().get(0)),
                    new CategoryImpl("Cinema", colors.getAll().get(0)));
            for (var v : vars) {
                categories.save(v);
            }
            assertEquals(3, categories.getAll().size());

            for (var ac : categories.getAll()) {
                categories.delete(ac);
            }
            colors.delete(c1);
            colors.delete(c2);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        try {
            categories.delete(c);
            fail();
        } catch (Exception e) {
            //
        }
    }

    @Test
    public void testAccounts() {
        var df = new DataSourceFactoryImpl();
        Dao<Account> accounts = df.getAccounts();
        Dao<String> colors = df.getColors();
        try {
            String c1 = "ffffff", c2 = "000000";
            colors.save(c2);
            colors.save(c1);
            Account account = new AccountImpl("Portafoglio", colors.getAll().get(0), 100);
            Account account2 = new AccountImpl("Hype", colors.getAll().get(0), 100);
            Account account3 = new AccountImpl("Revolut", colors.getAll().get(0), 100);
            accounts.save(account);
            accounts.save(account2);
            accounts.save(account3);
            assertEquals(3, accounts.getAll().size());
            for (var ac : accounts.getAll()) {
                accounts.delete(ac);
            }
            assertEquals(0, accounts.getAll().size());

            account = new AccountImpl("Portafoglio", colors.getAll().get(0), 300);
            account2 = new AccountImpl("Portafoglio", colors.getAll().get(0), 150);
            assertEquals(account, account2);
            accounts.save(account);
            assertEquals(1, accounts.getAll().size());
            accounts.update(account2);
            assertEquals(1, accounts.getAll().size());
            assertEquals(150, accounts.getAll().get(0).getAmount());
            assertEquals(1, accounts.getAll().size());
            accounts.delete(account);
            assertEquals(0, accounts.getAll().size());
            for (var ac : colors.getAll()) {
                colors.delete(ac);
            }
            assertEquals(0, colors.getAll().size());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

    }

    @Test
    public void testTransactions() {
        var db = new DataSourceFactoryImpl();
        var transactions = db.getTransactions();
        var cats = db.getCategories();
        var colors = db.getColors();
        var accounts = db.getAccounts();
        try {
            colors.save("000000");
            accounts.save(new AccountImpl("Conto Corrente", colors.getAll().get(0), 150_000));
            accounts.save(new AccountImpl("Portafoglio", colors.getAll().get(0), 10_000));
            cats.save(new CategoryImpl("c1", colors.getAll().get(0)));
            cats.save(new CategoryImpl("c2", colors.getAll().get(0)));
            List<Transaction> vars = List.of(new TransactionImpl("Gelato",
                            cats.getAll().get(0), new LocalDate(2020, 1, 1),
                            accounts.getAll().get(0), -250, Repetition.ONCE),
                    new TransactionImpl("Biscotto",
                            cats.getAll().get(1), new LocalDate(2020, 1, 1),
                            accounts.getAll().get(1), 300, Repetition.ONCE));
            for (var v : vars) {
                transactions.save(v);
            }
            assertEquals(2, transactions.getAll().size());
            for (var v : transactions.getAll()) {
                transactions.delete(v);
            }
            for (var v : accounts.getAll()) {
                accounts.delete(v);
            }
            for (var v : cats.getAll()) {
                cats.delete(v);
            }
            for (var v : colors.getAll()) {
                colors.delete(v);
            }
            assertEquals(0, transactions.getAll().size());
            assertEquals(0, colors.getAll().size());
            assertEquals(0, cats.getAll().size());
            assertEquals(0, accounts.getAll().size());

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testEvents() {
        var df = new DataSourceFactoryImpl();
        var rep = df.getEvents();
        var rel = df.getRelationships();
        var per = df.getPersons();
        try {
            assertEquals(new EventImpl("Mangiare", LocalDateTime.now(), LocalDateTime.now().plusDays(7), Repetition.BIMONTHLY),
                    new EventImpl("Mangiare", LocalDateTime.now(), LocalDateTime.now().plusDays(7), Repetition.HALF_YEARLY));
            List<Event> vars = List.of(
                    new EventImpl("Mangiare", LocalDateTime.now(), LocalDateTime.now().plusDays(5), Repetition.BIMONTHLY),
                    new EventImpl("Bere", LocalDateTime.now(), LocalDateTime.now().plusDays(4), Repetition.HALF_YEARLY),
                    new EventImpl("leggere ", LocalDateTime.now(), LocalDateTime.now().plusDays(3), Repetition.MONTHLY));
            for (var v : vars) {
                rep.save(v);
            }
            assertEquals(3, rep.getAll().size());
            for (var ac : rep.getAll()) {
                rep.delete(ac);
            }
            var p = new EventImpl("Mangiare", LocalDateTime.now(), LocalDateTime.now().plusDays(7), Repetition.BIMONTHLY);
            rep.save(p);
            assertEquals(Collections.emptyList(), rep.getAll().get(0).getPersons());


            rel.save("figlio");
            var p1 = new PersonImpl("marco", "figlio");
            var p2 = new PersonImpl("luca", "figlio");
            var p3 = new PersonImpl("gianni", "figlio");
            per.save(p1);
            per.save(p2);
            per.save(p3);
            p.addPerson(p1);
            p.addPerson(p2);
            rep.update(p);

            var k = rep.getAll().get(0);
            assertEquals(2, k.getPersons().size());
            k.addPerson(p3);
            rep.update(k);
            assertEquals(3, rep.getAll().get(0).getPersons().size());
            k = new EventImpl("Mangiare", LocalDateTime.now(), LocalDateTime.now().plusDays(7),
                    Repetition.BIMONTHLY, List.of(p1));
            rep.update(k);
            assertEquals(1, rep.getAll().get(0).getPersons().size());
            assertEquals(p1, rep.getAll().get(0).getPersons().get(0));
            for (var ac : rep.getAll()) {
                rep.delete(ac);
            }
            for (var ac : per.getAll()) {
                per.delete(ac);
            }
            rel.delete("figlio");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        try {
            rep.save(new EventImpl("Mangiare", LocalDateTime.now(), LocalDateTime.now().plusDays(5), Repetition.BIMONTHLY));
            rep.save(new EventImpl("Mangiare", LocalDateTime.now(), LocalDateTime.now().plusDays(7), Repetition.HALF_YEARLY));
            assertEquals(1, rep.getAll().size());
            rep.update(new EventImpl("Mangiare", LocalDateTime.now(), LocalDateTime.now().plusDays(8), Repetition.HALF_YEARLY));
            assertEquals(1, rep.getAll().size());
        } catch (IllegalArgumentException e) {
            // success
        } catch (DaoAccessException e) {
            fail();
            e.printStackTrace();
        }
        rep.getAll().forEach(x -> {
            try {
                rep.delete(x);
            } catch (DaoAccessException e) {
                e.printStackTrace();
            }
        });

    }

    @Test
    public void testDailyMood() {
        var df = new DataSourceFactoryImpl();
        Dao<DailyMood> rep = df.getDailyMoods();
        try {
            assertEquals(new DailyMoodImpl(5, LocalDate.now().plusDays(5)),
                    new DailyMoodImpl(3, LocalDate.now().plusDays(5)));
            var vars = List.of(new DailyMoodImpl(5, LocalDate.now().plusDays(5)),
                    new DailyMoodImpl(4, LocalDate.now().plusDays(4)),
                    new DailyMoodImpl(3, LocalDate.now().plusDays(3)));

            for (var v : vars) {
                rep.save(v);
            }
            assertEquals(3, rep.getAll().size());

            for (var ac : rep.getAll()) {
                rep.delete(ac);
            }
            assertEquals(0, rep.getAll().size());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        try {
            var d = new DailyMoodImpl(5, LocalDate.now().plusDays(5));
            rep.save(d);
            rep.update(new DailyMoodImpl(2, LocalDate.now().plusDays(5)));
            assertEquals(1, rep.getAll().size());
            assertEquals(2, rep.getAll().get(0).getMoodValue());
            d.setMoodValue(3);
            rep.update(d);
            assertEquals(3, rep.getAll().get(0).getMoodValue());
            rep.delete(d);
        } catch (DaoAccessException e) {
            fail();
            e.printStackTrace();
        }

        try {
            var d = new DailyMoodImpl(5, LocalDate.now().plusDays(5));
            rep.save(d);
            rep.update(new DailyMoodImpl(5, LocalDate.now().plusDays(6)));
        } catch (IllegalArgumentException e) {
            // right
        } catch (DaoAccessException ex) {
            fail();
        }

        rep.getAll().forEach(x -> {
            try {
                rep.delete(x);
            } catch (DaoAccessException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void testTodoList() {
        var df = new DataSourceFactoryImpl();
        Dao<ToDoAction> rep = df.getToDoList();
        try {
            assertEquals(new ToDoActionImpl("Mangiare", false),
                    new ToDoActionImpl("Mangiare", false));
            var vars = List.of( new ToDoActionImpl("Mangiare", false),
                    new ToDoActionImpl("dormire", true),
                    new ToDoActionImpl("leggere", false));

            for (var v : vars) {
                rep.save(v);
            }
            assertEquals(3, rep.getAll().size());
            rep.delete(rep.getAll().get(0));
            assertEquals(2, rep.getAll().size());

            for (var ac : rep.getAll()) {
                rep.delete(ac);
            }
            assertEquals(0, rep.getAll().size());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        try {
            rep.save(new ToDoActionImpl("Mangiare", false));
            rep.update(new ToDoActionImpl("Mangiare", true));
            assertTrue(rep.getAll().get(0).isDone());
        } catch (DaoAccessException e) {
            fail();
            e.printStackTrace();
        }

        try{
            rep.save(new ToDoActionImpl("Mangiare", false));
            rep.update(new ToDoActionImpl("leggere", true));
            fail();
        }catch (IllegalArgumentException e) {
            // right
        } catch (DaoAccessException ex) {
            fail();
        }
            rep.getAll().forEach(x -> {
                try {
                    rep.delete(x);
                } catch (DaoAccessException e) {
                    e.printStackTrace();
                }
            });
    }

    @Test
    public void testGroupTransaction() {
        var db = new DataSourceFactoryImpl();
        var transactions = db.getGroupTransactions();
        var cats = db.getCategories();
        var colors = db.getColors();
        var persons = db.getPersons();
        var rel = db.getRelationships();

        var p1 = new PersonImpl("marco", "figlio");
        var p2 = new PersonImpl("luca", "figlio");
        var p3 = new PersonImpl("gianni", "figlio");
        try {
            colors.save("000000");
            rel.save("figlio");
            persons.save(p1);
            persons.save(p2);
            persons.save(p3);
            cats.save(new CategoryImpl("c1", colors.getAll().get(0)));
            cats.save(new CategoryImpl("c2", colors.getAll().get(0)));
            List<GroupTransaction> vars = List.of(new GroupTransactionImpl("Gelato", p1, List.of(p2, p3),
                            300, new LocalDate(2020, 1, 1)),
                    new GroupTransactionImpl("Cornetto", p2, List.of(p1, p3),
                            250, new LocalDate(2020, 1, 1)));
            for (var v : vars) {
                transactions.save(v);
            }
            assertEquals(2, transactions.getAll().size());
            assertEquals(2, transactions.getAll().get(0).getForList().size());

            for (var v : transactions.getAll()) {
                transactions.delete(v);
            }
        }catch (Exception e) {
            fail();
        }
        var t = new GroupTransactionImpl("Gelato", p1, List.of(p2, p3),
                300, new LocalDate(2020, 1, 1));
        try {
                transactions.save(t);
                assertEquals(2, transactions.getAll().get(0).getForList().size());
                t = new GroupTransactionImpl("Gelato", p1, List.of(p2),
                        300, new LocalDate(2020, 1, 1));
                transactions.update(t);
                fail();
            }catch (Exception e) {
                //
            }
        try{
            for (var v : transactions.getAll()) {
                transactions.delete(v);
            }
            for (var v : cats.getAll()) {
                cats.delete(v);
            }
            for (var v : colors.getAll()) {
                colors.delete(v);
            }
            for (var v : persons.getAll()) {
                persons.delete(v);
            }
            for (var v : rel.getAll()) {
                rel.delete(v);
            }
            assertEquals(0, transactions.getAll().size());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

    }

    @Test
    public void testFidelityCards(){
        var df = new DataSourceFactoryImpl();
        Dao<FidelityCard> rep = df.getFidelityCards();
        try {
            assertEquals(new FidelityCardImpl("Coop", "0012jada", FidelityCardType.ALIMENTARI),
                    new FidelityCardImpl("Coop", "0012jada", FidelityCardType.ABBIGLIAMENTO));
            var vars = List.of(
                    new FidelityCardImpl("Coop", "0012ada", FidelityCardType.ALIMENTARI),
                    new FidelityCardImpl("Zara", "34232sdd", FidelityCardType.ABBIGLIAMENTO),
                    new FidelityCardImpl("C'entro", "232424", FidelityCardType.RISTORAZIONE));

            for (var v : vars) {
                rep.save(v);
            }
            assertEquals(3, rep.getAll().size());
            rep.delete(rep.getAll().get(0));
            assertEquals(2, rep.getAll().size());

            for (var ac : rep.getAll()) {
                rep.delete(ac);
            }
            assertEquals(0, rep.getAll().size());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        try {
            rep.save(new FidelityCardImpl("Coop", "0012jada", FidelityCardType.ALIMENTARI));
            rep.update(new FidelityCardImpl("Coop", "0012jada", FidelityCardType.COSMESI));
            assertEquals(FidelityCardType.COSMESI,rep.getAll().get(0).getType());
            rep.delete(rep.getAll().get(0));
        } catch (DaoAccessException e) {
            fail();
            e.printStackTrace();
        }

        try{
            rep.save(new FidelityCardImpl("Coop", "0012jada", FidelityCardType.ALIMENTARI));
            rep.update(new FidelityCardImpl("Mcdonald", "0012jada", FidelityCardType.ALIMENTARI));
            fail();
        }catch (IllegalArgumentException e) {
            // right
        } catch (DaoAccessException ex) {
            fail();
        }
        try {
            rep.delete(rep.getAll().get(0));
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testHotkeys(){
        var df = new DataSourceFactoryImpl();
        Dao<HotKey> rep = df.getHotKeys();
        var f = new HotKeyFactoryImpl();
        try {
            assertNotEquals(f.createCounterHotKey("shopping"),
                         f.createActivityHotKey("shopping"));
            var vars = List.of(
                    f.createCounterHotKey("acqua"),
                    f.createEventHotKey("Spesa"),
                    f.createActivityHotKey("shopping"));

            for (var v : vars) {
                rep.save(v);
            }
            assertEquals(3, rep.getAll().size());
            rep.delete(rep.getAll().get(0));
            assertEquals(2, rep.getAll().size());

            for (var ac : rep.getAll()) {
                rep.delete(ac);
            }
            assertEquals(0, rep.getAll().size());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        try {
            var g = f.createActivityHotKey("acqua");
            var h = f.createCounterHotKey("acqua");
            rep.save(g);
            rep.save(h);
            rep.delete(g);
            assertEquals(HotKeyType.COUNTER,rep.getAll().get(0).getType());
            for (var ac : rep.getAll()) {
                rep.delete(ac);
            }
        } catch (DaoAccessException e) {
            fail();
            e.printStackTrace();
        }
    }
}

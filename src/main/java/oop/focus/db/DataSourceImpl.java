package oop.focus.db;

import javafx.util.Pair;
import oop.focus.diary.model.DailyMood;
import oop.focus.diary.model.DailyMoodImpl;
import oop.focus.diary.model.ToDoAction;
import oop.focus.diary.model.ToDoActionImpl;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.AccountImpl;
import oop.focus.finance.model.Category;
import oop.focus.finance.model.CategoryImpl;
import oop.focus.finance.model.GroupTransaction;
import oop.focus.finance.model.GroupTransactionImpl;
import oop.focus.finance.model.QuickTransaction;
import oop.focus.finance.model.QuickTransactionImpl;
import oop.focus.common.Repetition;
import oop.focus.finance.model.Transaction;
import oop.focus.finance.model.TransactionImpl;
import oop.focus.event.model.Event;
import oop.focus.event.model.EventImpl;
import oop.focus.calendarhomepage.model.HotKey;
import oop.focus.calendarhomepage.model.HotKeyImpl;
import oop.focus.calendarhomepage.model.HotKeyType;
import oop.focus.calendar.persons.model.Person;
import oop.focus.calendar.persons.model.PersonImpl;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The implementation of {@link DataSource}. no synchronization is guaranteed between
 * multiple instances of the DataSource object.
 * Changes made on different instances will not affect each other.
 */
public class DataSourceImpl implements DataSource {
    private static final int NA = -1;
    private static final DateTimeFormatter DF = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    private final SingleDao<String> colors;
    private final SingleDao<Category> categories;
    private final SingleDao<String> relationships;
    private final SingleDao<DailyMood> dailyMoods;
    private final SingleDao<Person> persons;
    private final SingleDao<Event> events;
    private final SingleDao<ToDoAction> todoList;
    private final SingleDao<Pair<Integer, Integer>> eventPersons;
    private final SingleDao<FidelityCard> fidelityCards;
    private final SingleDao<Account> accounts;
    private final SingleDao<Transaction> transactions;
    private final SingleDao<GroupTransaction> groupTransactions;
    private final SingleDao<Pair<Integer, Integer>> groupTransactionPersons;
    private final SingleDao<HotKey> hotKeys;
    private final SingleDao<Person> groups;
    private final SingleDao<QuickTransaction> quickTransactions;

    /**
     * Instantiates a new Data source.
     */
    public DataSourceImpl() {
        this.colors = this.colors();
        this.categories = this.categories();
        this.relationships = this.relationships();
        this.dailyMoods = this.dailyMoods();
        this.persons = this.persons();
        this.eventPersons = this.eventPersons();
        this.events = this.events();
        this.todoList = this.todoList();
        this.accounts = this.accounts();
        this.transactions = this.transactions();
        this.groupTransactionPersons = this.groupTransactionPersons();
        this.groupTransactions = this.groupTransactions();
        this.hotKeys = this.hotKeys();
        this.groups = this.groups();
        this.quickTransactions = this.quickTransactions();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Dao<Person> getPersons() {
        return this.persons;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Dao<Category> getCategories() {
        return this.categories;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Dao<Account> getAccounts() {
        return this.accounts;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Dao<String> getColors() {
        return this.colors;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Dao<String> getRelationships() {
        return this.relationships;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Dao<DailyMood> getDailyMoods() {
        return this.dailyMoods;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Dao<Event> getEvents() {
        return this.events;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Dao<HotKey> getHotKeys() {
        return this.hotKeys;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Dao<Transaction> getTransactions() {
        return this.transactions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Dao<GroupTransaction> getGroupTransactions() {
        return this.groupTransactions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Dao<QuickTransaction> getQuickTransactions() {
        return this.quickTransactions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Dao<ToDoAction> getToDoList() {
        return this.todoList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Dao<Person> getGroup() {
        return this.groups;
    }

    private SingleDao<Person> persons() {
        return new CachedDao<>(new ParserImpl<>("person", a -> new PersonImpl(a.remove(1),
                this.relationships.getValue(Integer.parseInt(a.remove(1))).orElse("null")),
                List.of(new Pair<>("name", Person::getName),
                        new Pair<>("id_relationship", p -> this.relationships.getId(p.getRelationships())
                                .orElse(NA).toString()))));
    }

    private SingleDao<Category> categories() {
        return new CachedDao<>(new ParserImpl<>("category",
                a -> new CategoryImpl(a.remove(1),
                        this.colors.getValue(Integer.parseInt(a.remove(1))).orElse("null")),
                List.of(new Pair<>("name", Category::getName),
                        new Pair<>("id_color", p ->
                                this.colors.getId(p.getColor()).orElse(NA).toString()))));
    }

    private SingleDao<Account> accounts() {
        return new CachedDao<>(new ParserImpl<>("account", a -> new AccountImpl(a.remove(1),
                this.colors.getValue(Integer.parseInt(a.remove(1))).orElse(null),
                Integer.parseInt(a.remove(1))),
                List.of(new Pair<>("name", Account::getName),
                        new Pair<>("id_color", a -> this.colors.getId(a.getColor()).orElse(NA).toString()),
                        new Pair<>("start_amount", a -> String.valueOf(a.getInitialAmount())))));
    }

    private SingleDao<String> colors() {
        return new CachedDao<>(new ParserImpl<>("color", a -> a.remove(1),
                List.of(new Pair<>("value", s -> s))));
    }

    private SingleDao<String> relationships() {
        return new CachedDao<>(new ParserImpl<>("relationship", a -> a.remove(1),
                List.of(new Pair<>("name", s -> s))));
    }

    private SingleDao<DailyMood> dailyMoods() {
        return new CachedDao<>(new ParserImpl<>("daily_mood", a ->
                new DailyMoodImpl(Integer.parseInt(a.remove(1)),
                        new LocalDate(a.remove(1))),
                List.of(new Pair<>("value", d -> String.valueOf(d.getMoodValue())),
                        new Pair<>("date", d -> d.getDate().toString()))));
    }

    private SingleDao<Event> events() {
        final Function<Event, List<Integer>> function = (a) -> a.getPersons().stream()
                .map(p -> this.persons.getId(p).orElse(NA))
                .collect(Collectors.toList());
        final DataSourceParser<Event> parser = new ParserImpl<>("event", a -> {
            final var id = Integer.parseInt(a.remove(0));
            return new EventImpl(a.remove(0),
                    DF.parseLocalDateTime(a.remove(0)),
                    DF.parseLocalDateTime(a.remove(0)),
                    Repetition.values()[Integer.parseInt(a.remove(0))],
                    this.eventPersons.getAll().stream().filter(p -> p.getKey().equals(id))
                            .map(i -> this.persons.getValue(i.getValue()).orElse(null))
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList()),
                    Integer.parseInt(a.remove(0)) == 0);
        }, List.of(new Pair<>("name", Event::getName),
                new Pair<>("startdate", e -> DF.print(e.getStart())),
                new Pair<>("enddate", e -> DF.print(e.getEnd())),
                new Pair<>("frequency", e -> String.valueOf(e.getRepetition().ordinal())),
                new Pair<>("is_last", e -> e.isRepeated() ? "0" : "1")));
        return new RelationDao<>(parser, List.of(new Pair<>(this.eventPersons, function)));
    }

    private SingleDao<HotKey> hotKeys() {
        return new CachedDao<>(new ParserImpl<>("quick_action",
                a -> new HotKeyImpl(a.remove(1), HotKeyType.values()[Integer.parseInt(a.remove(1))]),
                List.of(new Pair<>("name", HotKey::getName),
                        new Pair<>("type", a -> String.valueOf(a.getType().ordinal())))));
    }

    private SingleDao<Transaction> transactions() {
        return new CachedDao<>(new ParserImpl<>("transaction",
                a -> new TransactionImpl(a.remove(1),
                        this.categories.getValue(Integer.parseInt(a.remove(1))).orElse(null),
                        DF.parseLocalDateTime(a.remove(1)),
                        this.accounts.getValue(Integer.parseInt(a.remove(1))).orElse(null),
                        Integer.parseInt(a.remove(1)),
                        Repetition.values()[Integer.parseInt(a.remove(1))],
                        Integer.parseInt(a.remove(1)) == 0),
                List.of(new Pair<>("description", Transaction::getDescription),
                        new Pair<>("id_category", t -> String.valueOf(this.categories.getId(t.getCategory()).orElse(NA))),
                        new Pair<>("date", t -> DF.print(t.getDate())),
                        new Pair<>("id_account", t -> String.valueOf(this.accounts.getId(t.getAccount()).orElse(NA))),
                        new Pair<>("amount", t -> String.valueOf(t.getAmount())),
                        new Pair<>("type", t -> String.valueOf(t.getRepetition().ordinal())),
                        new Pair<>("is_last", t -> String.valueOf(t.isToBeRepeated() ? 0 : 1)))));
    }

    private SingleDao<GroupTransaction> groupTransactions() {
        final Function<GroupTransaction, List<Integer>> function = (a) -> a.getForList().stream()
                .map(p -> this.persons.getId(p).orElse(NA))
                .collect(Collectors.toList());
        return new RelationDao<>(new ParserImpl<>("group_transaction", a -> {
            final var id = Integer.parseInt(a.remove(0));
            return new GroupTransactionImpl(a.remove(0),
                    this.persons.getValue(Integer.parseInt(a.remove(0))).orElse(null),
                    this.groupTransactionPersons.getAll().stream()
                            .filter(p -> p.getKey().equals(id))
                            .map(i -> this.persons.getValue(i.getValue()).orElse(null))
                            .filter(Objects::nonNull).collect(Collectors.toList()),
                    Integer.parseInt(a.remove(0)),
                    DF.parseLocalDateTime(a.remove(0)));
        }, List.of(new Pair<>("description", GroupTransaction::getDescription),
                new Pair<>("id_person", t -> String.valueOf(this.persons.getId(t.getMadeBy()).orElse(NA))),
                new Pair<>("amount", t -> String.valueOf(t.getAmount())),
                new Pair<>("date", t -> DF.print(t.getDate())))),
                List.of(new Pair<>(this.groupTransactionPersons, function)));
    }

    private SingleDao<ToDoAction> todoList() {
        return new CachedDao<>(new ParserImpl<>("todo_action",
                a -> new ToDoActionImpl(a.remove(1),
                        Integer.parseInt(a.remove(1)) == 1),
                List.of(new Pair<>("description", ToDoAction::getAnnotation),
                        new Pair<>("done", a -> a.isDone() ? "1" : "0"))));
    }

    private SingleDao<Person> groups() {
        return new CachedDao<>(new ParserImpl<>("group_person",
                a -> this.persons.getValue(Integer.parseInt(a.remove(1))).orElse(null),
                List.of(new Pair<>("id_person", p ->
                        String.valueOf(this.persons.getId(p).orElse(NA))))));
    }

    private SingleDao<QuickTransaction> quickTransactions() {
        return new CachedDao<>(new ParserImpl<>("quick_transaction",
                a -> new QuickTransactionImpl(Integer.parseInt(a.remove(1)),
                        a.remove(1),
                        this.categories.getValue(Integer.parseInt(a.remove(1))).orElse(null),
                        this.accounts.getValue(Integer.parseInt(a.remove(1))).orElse(null)),
                List.of(new Pair<>("price", t -> String.valueOf(t.getAmount())),
                        new Pair<>("name", QuickTransaction::getDescription),
                        new Pair<>("id_category", t ->
                                String.valueOf(this.categories.getId(t.getCategory()).orElse(NA))),
                        new Pair<>("id_account", t ->
                                String.valueOf(this.accounts.getId(t.getAccount()).orElse(NA))))));
    }

    private SingleDao<Pair<Integer, Integer>> groupTransactionPersons() {
        return new CachedDao<>(new ParserImpl<>("group_transaction_persons",
                a -> new Pair<>(Integer.parseInt(a.remove(1)), Integer.parseInt(a.remove(1))),
                List.of(new Pair<>("id_group_transaction", p -> String.valueOf(p.getKey())),
                        new Pair<>("id_person", p -> String.valueOf(p.getValue())))));
    }

    private SingleDao<Pair<Integer, Integer>> eventPersons() {
        return new CachedDao<>(new ParserImpl<>("event_person",
                a -> new Pair<>(Integer.parseInt(a.remove(1)), Integer.parseInt(a.remove(1))),
                List.of(new Pair<>("id_event", p -> String.valueOf(p.getKey())),
                        new Pair<>("id_person", p -> String.valueOf(p.getValue())))));
    }
}

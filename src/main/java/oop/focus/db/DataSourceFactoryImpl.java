package oop.focus.db;

import javafx.util.Pair;
import oop.focus.diary.model.DailyMood;
import oop.focus.diary.model.DailyMoodImpl;
import oop.focus.diary.model.ToDoAction;
import oop.focus.diary.model.ToDoActionImpl;
import oop.focus.fidelitycard.FidelityCard;
import oop.focus.fidelitycard.FidelityCardImpl;
import oop.focus.fidelitycard.FidelityCardType;
import oop.focus.finance.Account;
import oop.focus.finance.AccountImpl;
import oop.focus.finance.Category;
import oop.focus.finance.CategoryImpl;
import oop.focus.finance.GroupTransaction;
import oop.focus.finance.GroupTransactionImpl;
import oop.focus.finance.Repetition;
import oop.focus.finance.Transaction;
import oop.focus.finance.TransactionImpl;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventImpl;
import oop.focus.homepage.model.HotKey;
import oop.focus.homepage.model.HotKeyFactoryImpl;
import oop.focus.homepage.model.Person;
import oop.focus.homepage.model.PersonImpl;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DataSourceFactoryImpl implements DataSourceFactory {
    private static final int NA = -1;
    private static final DateTimeFormatter DF = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public final SingleDao<Person> getPersons() {
        var rel = this.getRelationships();
        return new CachedDao<>(new ParserImpl<>("person", a -> new PersonImpl(a.remove(1),
                rel.getValue(Integer.parseInt(a.remove(1))).orElse("null")),
                List.of(new Pair<>("name", Person::getName),
                        new Pair<>("id_relationship", p -> rel.getId(p.getDegreeOfKinship())
                                .orElse(NA).toString()))));
    }
    @Override
    public final SingleDao<Category> getCategories() {
        var rel = this.getColors();
        return new CachedDao<>(new ParserImpl<>("category", a -> new CategoryImpl(a.remove(1),
                rel.getValue(Integer.parseInt(a.remove(1))).orElse("null")),
                List.of(new Pair<>("name", Category::getName),
                        new Pair<>("id_color", p -> rel.getId(p.getColor()).orElse(NA).toString()))));
    }
    @Override
    public final SingleDao<Account> getAccounts() {
        var colors = this.getColors();
        return new CachedDao<>(new ParserImpl<>("account", a -> new AccountImpl(a.remove(1),
                colors.getValue(Integer.parseInt(a.remove(1))).orElse(null),
                Integer.parseInt(a.remove(1))),
                List.of(new Pair<>("name", Account::getName),
                        new Pair<>("id_color", a -> colors.getId(a.getColor()).orElse(NA).toString()),
                        new Pair<>("start_amount", a -> String.valueOf(a.getAmount())))));
    }
    @Override
    public final SingleDao<String> getColors() {
        return new CachedDao<>(new ParserImpl<>("color", a -> a.remove(1),
                List.of(new Pair<>("value", s -> s))));
    }
    @Override
    public final SingleDao<String> getRelationships() {
        return new CachedDao<>(new ParserImpl<>("relationship", a -> a.remove(1),
                List.of(new Pair<>("name", s -> s))));
    }
    @Override
    public final SingleDao<DailyMood> getDailyMoods() {
        return new CachedDao<>(new ParserImpl<>("daily_mood", a ->
                new DailyMoodImpl(Integer.parseInt(a.remove(1)), new LocalDate(a.remove(1))),
                List.of(new Pair<>("value", d -> String.valueOf(d.getMoodValue())),
                        new Pair<>("date", d -> d.getDate().toString()))));
    }
    @Override
    public final SingleDao<Event> getEvents() {
        var persons = this.getPersons();
        return new RelationDao<>(new ParserImpl<>("event", a -> {
            var id = Integer.parseInt(a.remove(0));
            return new EventImpl(a.remove(0),
                    DF.parseLocalDateTime(a.remove(0)),
                    DF.parseLocalDateTime(a.remove(0)),
                    Repetition.values()[Integer.parseInt(a.remove(0))],
                    this.getEventPerson().getAll().stream().filter(p -> p.getKey().equals(id))
                            .map(i -> this.getPersons().getValue(i.getValue()).orElse(null))
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList()));
        },
                List.of(new Pair<>("name", Event::getName),
                        new Pair<>("startdate", e -> DF.print(e.getStart())),
                        new Pair<>("enddate", e -> DF.print(e.getEnd())),
                        new Pair<>("frequency", e -> String.valueOf(e.getRipetition().ordinal())))),
                List.of(new Pair<>(this.getEventPerson(),
                        (id, a) -> a.getPersons().stream()
                                .map(p -> new Pair<>(id, persons.getId(p).orElse(NA))).collect(Collectors.toList()))));
    }
    @Override
    public final SingleDao<HotKey> getHotKeys() {
        return new CachedDao<>(new ParserImpl<>("quick_action",
                a -> new HotKeyFactoryImpl().createFromType(a.remove(1), Integer.parseInt(a.remove(1))),
                List.of(new Pair<>("name", HotKey::getName),
                        new Pair<>("type", a -> String.valueOf(a.getType().ordinal())))));
    }
    @Override
    public final SingleDao<Transaction> getTransactions() {
        var cat = this.getCategories();
        var accounts = this.getAccounts();
        return new CachedDao<>(new ParserImpl<>("transaction",
                a -> new TransactionImpl(a.remove(1),
                        cat.getValue(Integer.parseInt(a.remove(1))).orElse(null),
                        new LocalDate(a.remove(1)),
                        accounts.getValue(Integer.parseInt(a.remove(1))).orElse(null),
                        Integer.parseInt(a.remove(1)),
                        Repetition.values()[Integer.parseInt(a.remove(1))],
                        Integer.parseInt(a.remove(1)) == 0),
                List.of(new Pair<>("description", Transaction::getDescription),
                        new Pair<>("id_category", t -> String.valueOf(cat.getId(t.getCategory()).orElse(NA))),
                        new Pair<>("date", t -> t.getDate().toString()),
                        new Pair<>("id_account", t -> String.valueOf(accounts.getId(t.getAccount()).orElse(NA))),
                        new Pair<>("amount", t -> String.valueOf(t.getAmount())),
                        new Pair<>("type", t -> String.valueOf(t.getRepetition().ordinal())),
                        new Pair<>("is_last", t -> String.valueOf(t.isToBeRepeated() ? 0 : 1)))));
    }
    @Override
    public final SingleDao<GroupTransaction> getGroupTransactions() {
        var persons = this.getPersons();
        var transPersons = this.getGroupTransactionPersons();
        return new RelationDao<>(new ParserImpl<>("group_transaction", a -> {
                    var id = Integer.parseInt(a.remove(0));
                    return new GroupTransactionImpl(a.remove(0),
                            persons.getValue(Integer.parseInt(a.remove(0))).orElse(null),
                            transPersons.getAll().stream()
                                    .filter(p -> p.getKey().equals(id))
                                    .map(i -> this.getPersons().getValue(i.getValue()).orElse(null))
                                    .filter(Objects::nonNull).collect(Collectors.toList()),
                            Integer.parseInt(a.remove(0)),
                            new LocalDate(a.remove(0)));
                },
                List.of(new Pair<>("description", GroupTransaction::getDescription),
                        new Pair<>("id_person", t -> String.valueOf(persons.getId(t.getMadeBy()).orElse(NA))),
                        new Pair<>("amount", t -> String.valueOf(t.getAmount())),
                        new Pair<>("date", t -> t.getDate().toString()))),
                List.of(new Pair<>(this.getGroupTransactionPersons(),
                        (id, a) -> a.getForList().stream()
                                .map(p -> new Pair<>(id, persons.getId(p).orElse(NA))).collect(Collectors.toList()))));
    }
    @Override
    public final SingleDao<ToDoAction> getToDoList() {
        return new CachedDao<>(new ParserImpl<>("todo_action",
                a -> new ToDoActionImpl(a.remove(1), Integer.parseInt(a.remove(1)) == 1),
                List.of(new Pair<>("description", ToDoAction::getAnnotation),
                        new Pair<>("done", a -> a.isDone() ? "1" : "0"))));
    }
    @Override
    public final SingleDao<FidelityCard> getFidelityCards() {
        return new CachedDao<>(new ParserImpl<>("fidelity_Card",
                a -> new FidelityCardImpl(a.remove(1), a.remove(1),
                        FidelityCardType.values()[Integer.parseInt(a.remove(1))]),
                List.of(new Pair<>("name", FidelityCard::getCardName),
                        new Pair<>("code", FidelityCard::getCardId),
                        new Pair<>("type", f -> String.valueOf(f.getType().ordinal())))));
    }

    private SingleDao<Pair<Integer, Integer>> getGroupTransactionPersons() {
        return new CachedDao<>(new ParserImpl<>("group_transaction_persons",
                a -> new Pair<>(Integer.parseInt(a.remove(1)), Integer.parseInt(a.remove(1))),
                List.of(new Pair<>("id_group_transaction", p -> String.valueOf(p.getKey())),
                        new Pair<>("id_person", p -> String.valueOf(p.getValue())))));
    }
    private SingleDao<Pair<Integer, Integer>> getEventPerson() {
        return new CachedDao<>(new ParserImpl<>("event_person",
                a -> new Pair<>(Integer.parseInt(a.remove(1)), Integer.parseInt(a.remove(1))),
                List.of(new Pair<>("id_event", p -> String.valueOf(p.getKey())),
                        new Pair<>("id_person", p -> String.valueOf(p.getValue())))));
    }
}

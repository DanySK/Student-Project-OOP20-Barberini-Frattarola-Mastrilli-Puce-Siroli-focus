package oop.focus.finance.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import oop.focus.common.Linker;
import oop.focus.common.View;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.GroupTransactionImpl;
import oop.focus.finance.view.windows.GenericWindow;
import oop.focus.finance.view.windows.NewGroupTransactionViewImpl;
import oop.focus.homepage.model.Person;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Set;

public class NewGroupTransactionControllerImpl implements NewGroupTransactionController {

    private final GenericWindow<NewGroupTransactionController> view;
    private final FinanceManager manager;

    public NewGroupTransactionControllerImpl(final FinanceManager manager) {
        this.manager = manager;
        this.view = new NewGroupTransactionViewImpl(this);
    }

    @Override
    public final View getView() {
        return this.view;
    }

    @Override
    public final void newGroupTransaction(final String description, final Person madeBy, final Set<Person> forSet, final double amount,
                                          final java.time.LocalDate date, final int hours, final int minutes) {
        LocalDateTime formattedDate = new LocalDateTime(date == null ? LocalDate.now().getYear() : date.getYear(),
                date == null ? LocalDate.now().getMonthOfYear() : date.getMonthValue(),
                date == null ? LocalDate.now().getDayOfMonth() : date.getDayOfMonth(), hours, minutes, 0);
        this.manager.getGroupManager().addTransaction(new GroupTransactionImpl(description, madeBy,
                new ArrayList<>(forSet), (int) (amount * 100), formattedDate));
    }

    @Override
    public final ObservableList<Person> getGroupList() {
        ObservableList<Person> list = FXCollections.observableArrayList();
        Linker.setToList(this.getGroup(), list);
        return list;
    }

    @Override
    public final ObservableSet<Person> getGroup() {
        return this.manager.getGroupManager().getGroup();
    }
}

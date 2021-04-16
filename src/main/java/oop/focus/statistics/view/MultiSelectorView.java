package oop.focus.statistics.view;
import javafx.application.Platform;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
/**
 * A Generic implementation of the Multi selector, using a list of {@link CheckBox}.
 *
 * @param <X> the type parameter
 */
public class MultiSelectorView<X> implements MultiSelector<X> {

    private final Map<CheckBox, X> checkBoxMap;
    private final Map<X, Boolean> selectedItems;
    private final Pane root;
    private final ObservableSet<X> items;
    /**
     * Instantiates a new Multi selector view.
     * Changes on the input set will be auto updated on the view.
     *
     * @param items the observable list of elements to display
     * @param function a function that maps an element to a String that can be displayed
     */
    public MultiSelectorView(final ObservableSet<X> items, final Function<X, String> function) {
        this.root = this.getBox();
        this.checkBoxMap = new HashMap<>();
        this.selectedItems = new HashMap<>();
        this.items = items;
        this.items.forEach(a -> this.selectedItems.put(a, false));
        this.items.forEach(a -> this.checkBoxMap.put(this.createBox(function.apply(a)), a));
        this.checkBoxMap.keySet().forEach(c -> this.root.getChildren().add(c));
        this.checkBoxMap.keySet().forEach(this::addListeners);
        this.addListener(function);
    }

    private void addListener(final Function<X, String> function) {
        this.items.addListener((SetChangeListener<X>) change -> Platform.runLater(() -> {
            if (change.wasRemoved()) {
                this.checkBoxMap.entrySet()
                        .stream()
                        .filter(a -> a.getValue().equals(change.getElementRemoved()))
                        .findFirst().ifPresent(p -> {
                    this.checkBoxMap.remove(p.getKey());
                    this.root.getChildren().remove(p.getKey());
                });
                this.selectedItems.remove(change.getElementRemoved());
            } else if (change.wasAdded() && !this.selectedItems.containsKey(change.getElementAdded())) {
                var box = this.createBox(function.apply(change.getElementAdded()));
                this.checkBoxMap.put(box, change.getElementAdded());
                this.selectedItems.put(change.getElementAdded(), false);
                this.root.getChildren().add(box);
                this.addListeners(box);

            }
        }));
    }

    /**
     * Creates the box where the items will be shown.
     *
     * @param name the string representation of the element.
     * @return the check box.
     */
    protected CheckBox createBox(final String name) {
        CheckBox c = new CheckBox(name);
        c.setAlignment(Pos.CENTER);
        return c;
    }

    /**
     * Creates the box that will contain all the check boxes.
     *
     * @return the box
     */
    protected Pane getBox() {
        var v = new VBox();
        v.setAlignment(Pos.TOP_LEFT);
        return v;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Node getRoot() {
        return new ScrollPane(this.root);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Set<X> getSelected() {
        return this.selectedItems.entrySet().stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey).collect(Collectors.toSet());
    }

    private void addListeners(final CheckBox checkBox) {
        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            var r = this.selectedItems.replace(this.checkBoxMap.get(checkBox), newValue);
        });
    }
}

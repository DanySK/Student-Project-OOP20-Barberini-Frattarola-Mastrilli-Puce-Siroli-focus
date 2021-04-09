package oop.focus.common;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This static class provides methods to link multiple {@link ObservableList} and {@link ObservableSet}
 * to allows them to keep track of changes.
 */
public final class Linker {

    private Linker() {
    }

    /**
     * Links an {@link ObservableSet} to an {@link ObservableList} with the same elements type.
     *
     * @param <X>    the type of the elements in the collections
     * @param source the source set
     * @param dest   the destination list
     */
    public static <X> void setToList(final ObservableSet<X> source, final ObservableList<X> dest) {
        setToList(source, dest, Function.identity());
    }

    /**
     * Links an {@link ObservableList} to an {@link ObservableSet} with the same elements type.
     *
     * @param <X>    the type of the elements in the collections
     * @param source the source list
     * @param dest   the destination set
     */
    public static <X> void listToSet(final ObservableList<X> source, final ObservableSet<X> dest) {
        listToSet(source, dest, Function.identity());
    }

    /**
     * Links an {@link ObservableList} to an {@link ObservableList} with the same elements type.
     *
     * @param <X>    the type of the elements in the collections
     * @param source the source list
     * @param dest   the destination list
     */
    public static <X> void listToList(final ObservableList<X> source, final ObservableList<X> dest) {
        listToList(source, dest, Function.identity());
    }

    /**
     * Links an {@link ObservableSet}  to an {@link ObservableSet}  with the same elements type.
     *
     * @param <X>    the type of the elements in the collections
     * @param source the source set
     * @param dest   the destination set
     */
    public static <X> void setToSet(final ObservableSet<X> source, final ObservableSet<X> dest) {
        setToSet(source, dest, Function.identity());
    }

    /**
     * Links an {@link ObservableSet} of X type to an {@link ObservableList} of Y type,
     * using a mapper to associate each X element with an Y element.
     *
     * @param <X>    the type of the source element
     * @param <Y>    the type of the destination element
     * @param source the source set
     * @param dest   the destination list
     * @param mapper the mapper that maps an X element to a Y element
     */
    public static <X, Y> void setToList(final ObservableSet<X> source, final ObservableList<Y> dest,
                                        final Function<X, Y> mapper) {
        dest.addAll(source.stream().map(mapper).collect(Collectors.toList()));
        linkFromSet(source, dest, mapper);
    }

    /**
     * Links an {@link ObservableList} of X type to an {@link ObservableSet} of Y type,
     * using a mapper to associate each X element with an Y element.
     *
     * @param <X>    the type of the source element
     * @param <Y>    the type of the destination element
     * @param source the source list
     * @param dest   the destination set
     * @param mapper the mapper that maps an X element to a Y element
     */
    public static <X, Y> void listToSet(final ObservableList<X> source, final ObservableSet<Y> dest,
                                        final Function<X, Y> mapper) {
        dest.addAll(source.stream().map(mapper).collect(Collectors.toSet()));
        linkFromList(source, dest, mapper);
    }

    /**
     * Links an {@link ObservableList} of X type to an {@link ObservableList} of Y type,
     * using a mapper to associate each X element with an Y element.
     *
     * @param <X>    the type of the source element
     * @param <Y>    the type of the destination element
     * @param source the source list
     * @param dest   the destination list
     * @param mapper the mapper that maps an X element to a Y element
     */
    public static <X, Y> void listToList(final ObservableList<X> source, final ObservableList<Y> dest,
                                         final Function<X, Y> mapper) {
        dest.addAll(source.stream().map(mapper).collect(Collectors.toList()));
        linkFromList(source, dest, mapper);
    }

    /**
     * Links an {@link ObservableSet} of X type to an {@link ObservableSet} of Y type,
     * using a mapper to associate each X element with an Y element.
     *
     * @param <X>    the type of the source element
     * @param <Y>    the type of the destination element
     * @param source the source set
     * @param dest   the destination set
     * @param mapper the mapper that maps an X element to a Y element
     */
    public static <X, Y> void setToSet(final ObservableSet<X> source, final ObservableSet<Y> dest,
                                       final Function<X, Y> mapper) {
        dest.addAll(source.stream().map(mapper).collect(Collectors.toSet()));
        linkFromSet(source, dest, mapper);
    }

    private static <X, Y> void linkFromSet(final ObservableSet<X> source, final Collection<Y> dest,
                                           final Function<X, Y> mapper) {
        source.addListener((SetChangeListener<X>) c -> {
            if (c.wasAdded()) {
                dest.add(mapper.apply(c.getElementAdded()));
            } else if (c.wasRemoved()) {
                dest.remove(mapper.apply(c.getElementRemoved()));
            }
        });
    }

    private static <X, Y> void linkFromList(final ObservableList<X> source, final Collection<Y> dest,
                                            final Function<X, Y> mapper) {
        source.addListener((ListChangeListener<? super X>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    dest.addAll(c.getAddedSubList().stream().map(mapper).collect(Collectors.toList()));
                } else if (c.wasRemoved()) {
                    dest.removeAll(c.getRemoved().stream().map(mapper).collect(Collectors.toList()));
                }
            }
        });
    }

}

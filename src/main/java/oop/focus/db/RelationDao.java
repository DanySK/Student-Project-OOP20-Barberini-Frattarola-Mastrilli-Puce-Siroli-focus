package oop.focus.db;

import javafx.util.Pair;
import oop.focus.db.exceptions.DaoAccessException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * An extension of {@link CachedDao} that allows to interact with others Dao during the default RW operations.
 * For example it is possible to make sure that after the elimination of an object
 * through this Dao, other objects linked to the deleted element are eliminated too, through other Dao interfaces.
 *
 * @param <X> the type of the element
 */
public class RelationDao<X> extends CachedDao<X> {
    private final List<Pair<SingleDao<Pair<Integer, Integer>>, Function<X, List<Integer>>>> related;

    /**
     * Instantiates a new Relation dao.
     *
     * @param parser  the parser
     * @param related the list of pairs of elements where the first represents the {@link SingleDao} on which
     *                to carry out the operations in cascade, while the second represents a function that,
     *                given the element on which it is carried out (reading, deletion, update),
     *                returns a list of id relative to the elements on which to perform the same operation .
     */
    public RelationDao(final DataSourceParser<X> parser, final List<Pair<SingleDao<Pair<Integer, Integer>>,
            Function<X, List<Integer>>>> related) {
        super(parser);
        this.related = related;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void save(final X x) throws DaoAccessException {
        super.save(x);
        this.saveAndCheckMissing(x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void update(final X x) throws IllegalArgumentException, DaoAccessException {
        this.saveAndCheckMissing(x);
        super.update(x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void delete(final X x) throws DaoAccessException {
        final int id = this.getId(x).orElse(-1);
        super.delete(x);
        for (var p : this.related) {
            var total = p.getValue().apply(x);
            this.deleteRelated(p.getKey(), total, id);
        }
    }

    /**
     * Adds and removes the elements related to x using the given relation.
     *
     * @param x the element
     */
    private void saveAndCheckMissing(final X x) {
        var opt = this.getId(x);
        if (opt.isEmpty()) {
            throw new IllegalArgumentException();
        }
        int id = opt.get();
        for (var p : this.related) {
            var total = p.getValue().apply(x);
            this.saveRelated(p.getKey(), total, id);
            var missing = p.getKey().getAll().stream()
                    .filter(px -> px.getKey().equals(id))
                    .map(Pair::getValue)
                    .collect(Collectors.toList());
            total.forEach(missing::remove);
            this.deleteRelated(p.getKey(), missing, id);
        }
    }

    /**
     * Saves the related elements into the source.
     *
     * @param dao        the Dao used to save the elements
     * @param relatedIds the ids of the related elements to be saved.
     * @param savedId    the id of the element to which they are related
     */
    private void saveRelated(final Dao<Pair<Integer, Integer>> dao,
                             final List<Integer> relatedIds,
                             final int savedId) {
        relatedIds.forEach(e -> {
            try {
                dao.save(new Pair<>(savedId, e));
            } catch (DaoAccessException daoAccessException) {
                daoAccessException.printStackTrace();
            }
        });
    }

    /**
     * Deletes the related elements from the source.
     *
     * @param dao        the Dao used to delete the elements
     * @param relatedIds the relatedIds of the related elements to be deleted.
     * @param deletedId  the id of the element to which they are related
     */
    private void deleteRelated(final Dao<Pair<Integer, Integer>> dao,
                               final List<Integer> relatedIds,
                               final int deletedId) {
        relatedIds.forEach(e -> {
            try {
                dao.delete(new Pair<>(deletedId, e));
            } catch (DaoAccessException daoAccessException) {
                daoAccessException.printStackTrace();
            }
        });
    }
}

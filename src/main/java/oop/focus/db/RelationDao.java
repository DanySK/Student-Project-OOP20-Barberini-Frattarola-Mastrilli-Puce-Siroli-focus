package oop.focus.db;

import javafx.util.Pair;
import oop.focus.db.exceptions.DaoAccessException;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class RelationDao<X> extends CachedDao<X> {
    private final List<Pair<SingleDao<Pair<Integer, Integer>>, BiFunction<Integer, X, List<Pair<Integer, Integer>>>>> related;
    public RelationDao(final DataSourceParser<X> parser, final List<Pair<SingleDao<Pair<Integer, Integer>>,
            BiFunction<Integer, X, List<Pair<Integer, Integer>>>>> related) {
        super(parser);
        this.related = related;
    }
    @Override
    public final void save(final X x) throws DaoAccessException {
        super.save(x);
        this.saveAndCheckMissing(x);
    }
    @Override
    public final void update(final X x) throws IllegalArgumentException, DaoAccessException {
        this.saveAndCheckMissing(x);
        super.update(x);
    }
    @Override
    public final void delete(final X x) throws DaoAccessException {
        final int id = this.getId(x).orElse(-1);
        super.delete(x);
        for (var p : this.related) {
            var total = p.getValue().apply(id, x);
            this.deleteRelated(p, total);
        }
    }
    private void saveAndCheckMissing(final X x) {
        int id = this.getId(x).orElse(-1);
        for (var p : this.related) {
            var total = p.getValue().apply(id, x);
            this.saveRelated(p, total);
            var missing = p.getKey().getAll().stream()
                    .filter(px -> px.getKey().equals(id)).collect(Collectors.toList());
            total.forEach(missing::remove);
            this.deleteRelated(p, missing);
        }
    }
    private void saveRelated(final Pair<SingleDao<Pair<Integer, Integer>>, BiFunction<Integer, X, List<Pair<Integer, Integer>>>> p,
                             final List<Pair<Integer, Integer>> total) {
        total.forEach(e -> {
            try {
                p.getKey().save(e);
            } catch (DaoAccessException daoAccessException) {
                daoAccessException.printStackTrace();
            }
        });
    }
    private void deleteRelated(final Pair<SingleDao<Pair<Integer, Integer>>, BiFunction<Integer, X, List<Pair<Integer, Integer>>>> p,
                               final List<Pair<Integer, Integer>> missing) {
        missing.forEach(e -> {
            try {
                p.getKey().delete(e);
            } catch (DaoAccessException daoAccessException) {
                daoAccessException.printStackTrace();
            }
        });
    }
}

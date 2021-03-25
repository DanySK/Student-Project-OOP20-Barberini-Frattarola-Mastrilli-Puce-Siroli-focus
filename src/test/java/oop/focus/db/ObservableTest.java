package oop.focus.db;

import javafx.collections.ListChangeListener;
import oop.focus.db.exceptions.DaoAccessException;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;

public class ObservableTest {
    @Test
    public void testList(){
        var db = new DataSourceImpl();
        var colors = db.getColors();
        var list  = colors.getAll();
        AtomicReference<Integer> counter = new AtomicReference<>(0);
        list.addListener((ListChangeListener<String>) c -> counter.getAndSet(counter.get() + 1));
        try {
            int initial = list.size();
            colors.save("test11");
            assertEquals(1, (long) counter.get());
            colors.delete("test11");
            assertEquals(initial, colors.getAll().size());
            assertEquals(2, (long) counter.get());
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }
    }
}

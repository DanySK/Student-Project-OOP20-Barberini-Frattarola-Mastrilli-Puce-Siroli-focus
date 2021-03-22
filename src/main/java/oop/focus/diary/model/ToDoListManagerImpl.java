package oop.focus.diary.model;

import oop.focus.db.Dao;
import oop.focus.db.DataSourceImpl;
import oop.focus.db.exceptions.DaoAccessException;
import java.util.List;
/**
 * Immutable implementation of ToDoListManagerImpl.
 */

public class ToDoListManagerImpl implements ToDoListManager {
    private final Dao<ToDoAction> dsi;
    public ToDoListManagerImpl(final DataSourceImpl dsi) {
        this.dsi = dsi.getToDoList();
    }
    @Override
    public final void addAnnotation(final ToDoAction tdl) {
        if (!this.dsi.getAll().contains(tdl)) {
            try {
                this.dsi.save(tdl);
            } catch (DaoAccessException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public final void removeAnnotation(final ToDoAction tdl) {
        try {
            this.dsi.delete(tdl);
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }
    }
    @Override
    public final void changeBoxStatus(final ToDoAction tdl) {
        try {
            this.dsi.update(new ToDoActionImpl(tdl.getAnnotation(), !tdl.isDone()));
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }
    }
    @Override
    public final List<ToDoAction> getAnnotations() {
        return this.dsi.getAll();
    }
}

package oop.focus.diary.model;


import oop.focus.db.DataSourceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;



public class ToDoListManagerTest {
    private final DataSourceImpl dsi = new DataSourceImpl();
    private final ToDoListManager toDoListManager = new ToDoListManagerImpl(this.dsi);
    @Test
    public void test() {
        final ToDoActionImpl test1 = new ToDoActionImpl("test1", false);
        this.toDoListManager.addAnnotation(test1);
        final ToDoActionImpl test2 = new ToDoActionImpl("test2", false);
        this.toDoListManager.addAnnotation(test2);
        final ToDoActionImpl test3 = new ToDoActionImpl("test3", true);
        this.toDoListManager.addAnnotation(test3);
        assertEquals(test1.getAnnotation(), "test1");
        assertFalse(test2.isDone());
        this.toDoListManager.changeBoxStatus(test2);
        assertTrue(this.toDoListManager.getAnnotations().stream().filter(s -> s.equals(test2)).findAny().get().isDone());
        this.toDoListManager.removeAnnotation(test1);
        assertFalse(this.toDoListManager.getAnnotations().stream().anyMatch(s -> s.equals(test1)));
        this.toDoListManager.removeAnnotation(test2);
        this.toDoListManager.removeAnnotation(test3);
     }
    @Test (expected = IllegalArgumentException.class)
    public void testException() {
        final ToDoActionImpl test = new ToDoActionImpl("cucinarecucinarecucinarecucinarecucinarecucinarecucinare",
                false);
        this.toDoListManager.addAnnotation(test);
        this.toDoListManager.getAnnotations().forEach(s -> System.out.println(s.getAnnotation()));
    }
}

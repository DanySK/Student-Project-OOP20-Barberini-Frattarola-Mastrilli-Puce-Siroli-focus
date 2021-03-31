package oop.focus.diary.model;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import oop.focus.db.DataSourceImpl;
import org.junit.Test;

public class ToDoListManagerImplTest {
    private final DataSourceImpl dsi = new DataSourceImpl();
    private final ToDoListManager tdlm = new ToDoListManagerImpl(dsi);
    @Test
    public void test() {
        final ToDoActionImpl prova = new ToDoActionImpl("test1", false);
        tdlm.addAnnotation(prova);
        final ToDoActionImpl prova2 = new ToDoActionImpl("test2", false);
        tdlm.addAnnotation(prova2);
        final ToDoActionImpl prova3 = new ToDoActionImpl("test3", true);
        tdlm.addAnnotation(prova3);
        tdlm.getAnnotations().forEach(s -> System.out.println(s.getAnnotation() +" "+ s.isDone()));
        //assertEquals(prova.getAnnotation(), "cucinare");
        //assertFalse(tdlm.getAnnotations().stream().filter(x -> x.equals(prova2)).iterator().next().isDone());
        tdlm.changeBoxStatus(prova2);
        tdlm.getAnnotations().forEach(s -> System.out.println(s.getAnnotation() +" "+ s.isDone()));
        tdlm.removeAnnotation(prova);
        tdlm.getAnnotations().forEach(s -> System.out.println(s.getAnnotation()+ " " +s.isDone()));
     }
    @Test (expected = IllegalArgumentException.class)
    public void testException() {
        final ToDoActionImpl prova = new ToDoActionImpl("cucinarecucinarecucinarecucinarecucinarecucinarecucinare", false);
        tdlm.addAnnotation(prova);
        tdlm.getAnnotations().forEach(s -> System.out.println(s.getAnnotation()));
    }
}

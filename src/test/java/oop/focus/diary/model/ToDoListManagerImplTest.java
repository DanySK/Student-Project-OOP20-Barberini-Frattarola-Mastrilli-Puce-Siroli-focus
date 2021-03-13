package oop.focus.diary.model;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ToDoListManagerImplTest {

    @Test
    public void test() {
        final ToDoListManager tdlm = new ToDoListManagerImpl();
        final ToDoActionImpl prova = new ToDoActionImpl("cucinare", false);
        tdlm.addAnnotation(prova);
        final ToDoActionImpl prova2 = new ToDoActionImpl("portare fuori la spazzatura", false);
        tdlm.addAnnotation(prova2);
        final ToDoActionImpl prova3 = new ToDoActionImpl("studiare", false);
        tdlm.addAnnotation(prova3);
        assertEquals(prova.getAnnotation(), "cucinare");
        assertFalse(prova2.isDone());
        tdlm.changeBoxStatus(prova2);
        assertTrue(prova2.isDone());
        tdlm.getAnnotations().forEach(s -> System.out.println(s.getAnnotation()));
        tdlm.removeAnnotation(prova2);
        tdlm.getAnnotations().forEach(s -> System.out.println(s.getAnnotation()));
    }
    @Test (expected = IllegalArgumentException.class)
    public void testException() {
        final ToDoListManager tdlm = new ToDoListManagerImpl();
        final ToDoActionImpl prova = new ToDoActionImpl("cucinarecucinarecucinarecucinarecucinarecucinarecucinare", false);
        tdlm.addAnnotation(prova);
        tdlm.getAnnotations().forEach(s -> System.out.println(s.getAnnotation()));
    }

}

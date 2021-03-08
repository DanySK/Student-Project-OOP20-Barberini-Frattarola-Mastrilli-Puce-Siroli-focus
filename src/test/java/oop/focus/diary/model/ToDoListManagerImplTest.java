package oop.focus.diary.model;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ToDoListManagerImplTest {

    @Test
    public void test() {
        final ToDoListManager tdlm = new ToDoListManagerImpl();
        final ToDoListImpl prova = new ToDoListImpl("cucinare");
        tdlm.addAnnotation(prova);
        final ToDoListImpl prova2 = new ToDoListImpl("portare fuori la spazzatura");
        tdlm.addAnnotation(prova2);
        final ToDoListImpl prova3 = new ToDoListImpl("studiare");
        tdlm.addAnnotation(prova3);
        assertEquals(prova.getAnnotation(), "cucinare");
        assertFalse(prova2.isDone());
        tdlm.changeBoxStatus(prova2);
        assertTrue(prova2.isDone());
        tdlm.getAnnotations().forEach(s -> System.out.println(s.getAnnotation()));
        tdlm.removeAnnotation(prova2);
        tdlm.getAnnotations().forEach(s -> System.out.println(s.getAnnotation()));
    }

}

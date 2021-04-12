package oop.focus.diary.model;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import oop.focus.db.DataSourceImpl;
import org.junit.Test;

public class ToDoListManagerImplTest {
    private final DataSourceImpl dsi = new DataSourceImpl();
    private final ToDoListManager tdlm = new ToDoListManagerImpl(dsi);
    @Test
    public void test() {
        final ToDoActionImpl prova = new ToDoActionImpl("test1", false);
        this.tdlm.addAnnotation(prova);
        final ToDoActionImpl prova2 = new ToDoActionImpl("test2", false);
        this.tdlm.addAnnotation(prova2);
        final ToDoActionImpl prova3 = new ToDoActionImpl("test3", true);
        this.tdlm.addAnnotation(prova3);
        this.tdlm.getAnnotations().forEach(s -> System.out.println(s.getAnnotation() +" "+ s.isDone()));
        assertEquals(prova.getAnnotation(), "test1");
        assertFalse(prova2.isDone());
        this.tdlm.changeBoxStatus(prova2);
        this.tdlm.getAnnotations().forEach(s -> System.out.println(s.getAnnotation() +" "+ s.isDone()));
        this.tdlm.removeAnnotation(prova);
        this.tdlm.getAnnotations().forEach(s -> System.out.println(s.getAnnotation()+ " " +s.isDone()));
        this.tdlm.removeAnnotation(prova2);
        this.tdlm.removeAnnotation(prova3);
     }
    @Test (expected = IllegalArgumentException.class)
    public void testException() {
        final ToDoActionImpl prova = new ToDoActionImpl("cucinarecucinarecucinarecucinarecucinarecucinarecucinare", false);
        tdlm.addAnnotation(prova);
        tdlm.getAnnotations().forEach(s -> System.out.println(s.getAnnotation()));
    }
}

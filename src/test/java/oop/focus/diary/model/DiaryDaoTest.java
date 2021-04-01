package oop.focus.diary.model;

import oop.focus.db.exceptions.DaoAccessException;
import org.junit.Test;

public class DiaryDaoTest {
    private final DiaryDao dsd = new DiaryDao();
    private final DiaryImpl diario1 = new DiaryImpl("oggi il tempo è brutto, sta per piovere", "oggi");
    private final DiaryImpl diario2 = new DiaryImpl("domani il tempo sarà bello e il sole splenderà", "domani");
    @Test
    public void testBackupPages() throws DaoAccessException {
        this.dsd.save(this.diario1);
        this.dsd.save(this.diario2);
        this.dsd.getAll().forEach(a -> System.out.println(a.getName() + " " + a.getContent()));
    }    @Test
    public void testUpdate() throws DaoAccessException {
        this.dsd.getAll().forEach(a -> System.out.println(a.getName() + " " + a.getContent()));
        this.dsd.update(new DiaryImpl("ha smesso di piovere", "oggi"));
        this.dsd.getAll().forEach(a -> System.out.println(a.getName() + " " + a.getContent()));
        this.dsd.update(new DiaryImpl("ha smesso di piovere2", "ieri"));
        this.dsd.getAll().forEach(a -> System.out.println(a.getName() + " " + a.getContent()));
        this.dsd.delete(this.dsd.getAll().stream().filter(a -> a.equals(diario1)).findAny().get());
        System.out.println("dopo eliminazione : " );
        this.dsd.getAll().forEach(a -> System.out.println(a.getName() + " "+ a.getContent()));



    }
    
    @Test
    public void anotherTest() {

        this.dsd.save(new DiaryImpl("pro", "proa"));
        this.dsd.getAll().forEach(a -> System.out.println(a.getName() + " "+ a.getContent()));
        this.dsd.update(new DiaryImpl("ciaoooo", "proa"));
        this.dsd.getAll().forEach(a -> System.out.println(a.getName() + " "+ a.getContent()));
        this.dsd.save(new DiaryImpl("pro", "proa"));
    }
    @Test
    public void test2() {
        this.dsd.getAll().forEach(a -> System.out.println(a.getName() + " "+ a.getContent()));
    }


 



}
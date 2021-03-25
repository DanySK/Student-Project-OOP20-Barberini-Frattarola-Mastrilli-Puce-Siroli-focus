package oop.focus.diary.model;

import oop.focus.db.exceptions.DaoAccessException;
import org.junit.Test;

public class DiaryDaoTest {
    private final DiaryDao dsd = new DiaryDao();
    private final DiaryImpl diario1 = new DiaryImpl("oggi il tempo è brutto, sta per piovere", "oggi");
    private final DiaryImpl diario2 = new DiaryImpl("domani il tempo sarà bello e il sole splenderà", "domani");
  /*  @Test
    public void testBackupPages() throws DaoAccessException {
        this.dsd.save(this.diario1);
        this.dsd.save(this.diario2);
        System.out.println(this.dsd.getAll().get(0).getName());
        System.out.println(this.dsd.getAll().get(0).getContent());
        System.out.println(this.dsd.getAll().get(1).getName());
        System.out.println(this.dsd.getAll().get(1).getContent());
    }    @Test
    public void testUpdate() throws DaoAccessException {
        System.out.println(this.dsd.getAll().get(0).getName());
        System.out.println(this.dsd.getAll().get(0).getContent());
        this.dsd.update(new DiaryImpl("ha smesso di piovere", "oggi"));
        System.out.println(this.dsd.getAll().get(0).getName());
        System.out.println(this.dsd.getAll().get(0).getContent());
        System.out.println(this.dsd.getAll().get(1).getName());
        System.out.println(this.dsd.getAll().get(1).getContent());
        this.dsd.update(new DiaryImpl("ha smesso di piovere2", "ieri"));
        System.out.println("da elimiare : "+ this.dsd.getAll().get(0).getName());
        System.out.println("da elimiare : " + this.dsd.getAll().get(0).getContent());
        System.out.println(this.dsd.getAll().get(1).getName());
        System.out.println(this.dsd.getAll().get(1).getContent());
        this.dsd.delete(this.dsd.getAll().get(0));
        System.out.println("dopo eliminazione : " );
        this.dsd.getAll().forEach(a -> System.out.println(a.getName() + " "+ a.getContent()));



    }
    */
    @Test
    public void anotherTest() {
        this.dsd.save(new DiaryImpl("pro", "proa"));
    }






}
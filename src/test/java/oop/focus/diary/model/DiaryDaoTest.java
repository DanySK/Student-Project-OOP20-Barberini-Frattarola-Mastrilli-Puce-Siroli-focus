package oop.focus.diary.model;

import oop.focus.db.exceptions.DaoAccessException;
import org.junit.Test;

public class DiaryDaoTest {
    private final DiaryDao dsd = new DiaryDao();
    private final DiaryImpl diario1 = new DiaryImpl("oggi il tempo è brutto, sta per piovere", "oggi");
    private final DiaryImpl diario2 = new DiaryImpl("domani il tempo sarà bello e il sole splenderà", "domani");
    @Test
    public void testBackupPages() throws DaoAccessException {
       
        dsd.save(diario1);
        dsd.save(diario2);
        System.out.println(dsd.getAll().get(0).getName());
        System.out.println(dsd.getAll().get(0).getContent());
        System.out.println(dsd.getAll().get(1).getName());
        System.out.println(dsd.getAll().get(1).getContent());


    }
    @Test
    public void testUpdate() throws DaoAccessException {

        dsd.update(new DiaryImpl("ha smesso di piovere", "oggi"));
        System.out.println(dsd.getAll().get(0).getName());
        System.out.println(dsd.getAll().get(0).getContent());
        System.out.println(dsd.getAll().get(1).getName());
        System.out.println(dsd.getAll().get(1).getContent());
        dsd.update(new DiaryImpl("ha smesso di piovere2", "ieri"));
        System.out.println(dsd.getAll().get(0).getName());
        System.out.println(dsd.getAll().get(0).getContent());
        System.out.println(dsd.getAll().get(1).getName());
        System.out.println(dsd.getAll().get(1).getContent());
    }


}
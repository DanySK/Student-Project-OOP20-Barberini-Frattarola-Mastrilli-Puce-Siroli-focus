package oop.focus.diary.model;

import oop.focus.db.exceptions.DaoAccessException;
import org.junit.Test;

public class DiarySingleDaoTest {
    private final DiarySingleDao dsd = new DiarySingleDao();
    private final DiaryImpl diario1 = new DiaryImpl("oggi il tempo è brutto, sta per piovere", "oggi");
    private final DiaryImpl diario2 = new DiaryImpl("domani il tempo sarà bello e il sole splenderà", "domani");
    @Test
    public void testBackupPages() throws DaoAccessException {
        dsd.save(diario1);
        dsd.save(diario2);
        System.out.println(dsd.getValue(dsd.getId(diario1).get()).get().getName());
        System.out.println(dsd.getValue(dsd.getId(diario1).get()).get().getContent());
        System.out.println(dsd.getValue(dsd.getId(diario2).get()).get().getName());
        System.out.println(dsd.getValue(dsd.getId(diario2).get()).get().getContent());


    }
    @Test
    public void testUpdate() throws DaoAccessException {

        dsd.update(new DiaryImpl("ha smesso di piovere", "oggi"));
        System.out.println(dsd.getValue(dsd.getId(diario1).get()).get().getName());
        System.out.println(dsd.getValue(dsd.getId(diario1).get()).get().getContent());
        System.out.println(dsd.getValue(dsd.getId(diario2).get()).get().getName());
        System.out.println(dsd.getValue(dsd.getId(diario2).get()).get().getContent());
        dsd.update(new DiaryImpl("ha smesso di piovere", "ieri"));
    }

}
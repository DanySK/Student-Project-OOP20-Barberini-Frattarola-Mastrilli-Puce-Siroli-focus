package oop.focus.diary.model;
import org.junit.Test;

public class DiaryDaoTest {
    private final DiaryDao dsd = new DiaryDao();
    private final DiaryImpl diario1 = new DiaryImpl("oggi il tempo è brutto, sta per piovere", "oggioggi");
    private final DiaryImpl diario2 = new DiaryImpl("domani il tempo sarà bello e il sole splenderà", "domanidomani");
    private final DiaryImpl diario3 = new DiaryImpl("provaprovaprova", "provaprova");
    private final DiaryImpl diario4 = new DiaryImpl("ciaociao", "provaprova");
    private final DiaryImpl diario5 = new DiaryImpl("aaaaa", "prova1prova1");
    private final DiaryImpl diario6 = new DiaryImpl("ccccc", "provaprova2");

    @Test
    public void testBackupPages() {
        this.dsd.save(this.diario1);
        this.dsd.save(this.diario2);
        this.dsd.getAll().forEach(a -> System.out.println(a.getName() + " " + a.getContent()));
        this.dsd.delete(this.diario1);
        this.dsd.delete(this.diario2);
    }
    @Test
    public void testUpdate() {
        this.dsd.save(this.diario5);
        this.dsd.getAll().forEach(a -> System.out.println(a.getName() + " " + a.getContent()));
        this.dsd.update(new DiaryImpl("bbbbb", "prova1"));
        this.dsd.getAll().forEach(a -> System.out.println(a.getName() + " " + a.getContent()));
        this.dsd.delete(this.diario5);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testUpdateNewNote() {
        this.dsd.update(diario6);
        this.dsd.getAll().forEach(a -> System.out.println(a.getName() + " " + a.getContent()));
    }
    @Test
    public void anotherTest() {
        this.dsd.save(this.diario3);
        this.dsd.getAll().forEach(a -> System.out.println(a.getName() + " "+ a.getContent()));
        this.dsd.update(this.diario4);
        this.dsd.getAll().forEach(a -> System.out.println(a.getName() + " "+ a.getContent()));
        this.dsd.delete(this.diario4);
    }

}
package oop.focus.diary.model;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DiaryDaoTest {
    private final DiaryDao dsd = new DiaryDao();
    private final DiaryImpl diary1 = new DiaryImpl("testDiary1",
            "test1");
    private final DiaryImpl diary2 = new DiaryImpl("testDiary2",
            "test2");
    private final DiaryImpl diary5 = new DiaryImpl("testDiary5", "test5");
    private final DiaryImpl diary6 = new DiaryImpl("testDiary6", "test6");

    @Test
    public void testBackupPages() {
        this.dsd.save(this.diary1);
        this.dsd.save(this.diary2);
        assertEquals("testDiary1", this.dsd.getAll().stream().
                filter(s -> s.equals(this.diary1)).findAny().get().getContent());
        assertEquals("testDiary2", this.dsd.getAll().stream().
                filter(s -> s.equals(this.diary2)).findAny().get().getContent());
        this.dsd.delete(this.diary1);
        this.dsd.delete(this.diary2);
    }
    @Test
    public void testUpdate() {
        this.dsd.save(this.diary5);
        assertEquals("testDiary5", this.dsd.getAll().stream().
                filter(s -> s.getName().equals(this.diary5.getName())).findAny().get().getContent());
        this.dsd.update(new DiaryImpl("This is a test", "test5"));
        assertEquals("This is a test", this.dsd.getAll().stream().
                filter(s -> s.getName().equals(this.diary5.getName())).findAny().get().getContent());
        this.dsd.delete(this.diary5);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testUpdateNewNote() {
        this.dsd.update(this.diary6);
    }

}
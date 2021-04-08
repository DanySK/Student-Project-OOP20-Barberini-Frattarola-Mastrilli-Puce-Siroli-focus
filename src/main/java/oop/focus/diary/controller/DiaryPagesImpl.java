package oop.focus.diary.controller;

import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.ObservableSet;
import oop.focus.diary.model.DiaryDao;
import oop.focus.diary.model.DiaryImpl;

public class DiaryPagesImpl implements DiaryPages {
    private final DiaryDao diaryDao;
    public DiaryPagesImpl() {
        this.diaryDao = new DiaryDao();
    }
    @Override
    public final List<String> filesName() {
        return this.diaryDao.getAll().stream().map(DiaryImpl::getName).collect(Collectors.toList());
    }
    @Override
    public final ObservableSet<DiaryImpl> getObservableSet() {
         return this.diaryDao.getAll();
     }
    @Override
    public final String getContentByName(final String fileName) {
        if (this.diaryDao.getAll().stream().anyMatch(s -> s.getName().equals(fileName))) {
            return this.diaryDao.getAll().stream().filter(s -> s.getName().equals(fileName)).findAny().get().getContent();
        }
        throw new IllegalArgumentException();
    }

    @Override
    public final void updatePage(final String name, final String content) {
        if (this.filesName().contains(name)) {
            this.diaryDao.update(new DiaryImpl(content, name));
            System.out.println(content);
        }
    }
    @Override
    public final void removePage(final String name) {
        if (this.filesName().contains(name)) {
            this.diaryDao.delete(new DiaryImpl(this.getContentByName(name), name));
        }
    }
    @Override
    public final void createPage(final String name, final String content) {
        this.diaryDao.save(new DiaryImpl(content, name));
        this.diaryDao.getAll().forEach(s -> System.out.println(s.getName()));
    }

}

package oop.focus.diary.controller;

import java.util.Set;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import oop.focus.common.View;
import oop.focus.diary.model.DiaryDao;
import oop.focus.diary.model.DiaryImpl;
import oop.focus.diary.view.PagesViewImpl;

public class DiaryPagesImpl implements DiaryPages {
    private final DiaryDao diaryDao;
    private final View content;
    private final ObservableSet<DiaryImpl> set;

    public DiaryPagesImpl(final DiaryDao diaryDao) {
        this.diaryDao = diaryDao;
        this.set = FXCollections.observableSet();
        this.set.addAll(this.diaryDao.getAll());
        this.content = new PagesViewImpl(this);
    }
    @Override
    public final ObservableSet<DiaryImpl> getObservableSet() {
         return this.set;
     }
     @Override
     public final Set<String> getFileName() {
        return this.set.stream().map(DiaryImpl::getName).collect(Collectors.toSet());
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
        if (this.getFileName().contains(name)) {
            this.diaryDao.update(new DiaryImpl(content, name));
            System.out.println(content);
        }
    }

    @Override
    public final void createPage(final String name, final String content) {
        final DiaryImpl diary = new DiaryImpl(content, name);
        if (!this.set.contains(diary)) {
            this.diaryDao.save(diary);
            this.set.add(diary);
        }
    }

    @Override
    public final View getView() {
        return this.content;
    }

    @Override
    public final void remove(final String a) {
        if (this.getFileName().contains(a)) {
            final DiaryImpl diary = new DiaryImpl(this.getContentByName(a), a);
            this.diaryDao.delete(diary);
            this.set.remove(diary);
        }
    }
}

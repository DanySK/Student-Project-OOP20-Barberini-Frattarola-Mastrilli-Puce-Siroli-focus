package oop.focus.diary.controller;

import java.util.Set;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import oop.focus.common.View;
import oop.focus.diary.model.DiaryDao;
import oop.focus.diary.model.DiaryImpl;
import oop.focus.diary.view.DiaryView;

/**
 * Implementation of {@link DiaryPagesController}. The class manages diary's section.
 */
public class DiaryPagesControllerImpl implements DiaryPagesController {
    private final DiaryDao diaryDao;
    private final View content;
    private final ObservableSet<DiaryImpl> set;

    /**
     * Instantiates a new diary pages controller and creates the associated view.
     *
     * @param diaryDao  an implementation of {@link oop.focus.db.Dao}
     */
    public DiaryPagesControllerImpl(final DiaryDao diaryDao) {
        this.diaryDao = diaryDao;
        this.set = FXCollections.observableSet();
        this.set.addAll(this.diaryDao.getAll());
        this.content = new DiaryView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final ObservableSet<DiaryImpl> getObservableSet() {
         return this.set;
     }
    /**
     * {@inheritDoc}
     */
     @Override
     public final Set<String> getFileName() {
        return this.set.stream().map(DiaryImpl::getName).collect(Collectors.toSet());
     }
    /**
     * {@inheritDoc}
     */
    @Override
    public final String getContentByName(final String fileName) {
        if (this.diaryDao.getAll().stream().anyMatch(s -> s.getName().equals(fileName))) {
            return this.diaryDao.getAll().stream().filter(s -> s.getName().equals(fileName)).findAny().get().getContent();
        }
        throw new IllegalArgumentException();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final void updatePage(final String name, final String content) {
        if (this.getFileName().contains(name)) {
            this.diaryDao.update(new DiaryImpl(content, name));
            System.out.println(content);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final void createPage(final String name, final String content) {
        final DiaryImpl diary = new DiaryImpl(content, name);
        if (!this.set.contains(diary)) {
            this.diaryDao.save(diary);
            this.set.add(diary);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final View getView() {
        return this.content;
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void remove(final String input) {
        if (this.getFileName().contains(input)) {
            final DiaryImpl diary = new DiaryImpl(this.getContentByName(input), input);
            this.diaryDao.delete(diary);
            this.set.remove(diary);
        }
    }
}

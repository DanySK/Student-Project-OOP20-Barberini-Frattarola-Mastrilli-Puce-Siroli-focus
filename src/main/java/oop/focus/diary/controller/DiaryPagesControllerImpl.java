package oop.focus.diary.controller;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import oop.focus.common.View;
import oop.focus.db.Dao;
import oop.focus.db.exceptions.DaoAccessException;
import oop.focus.diary.model.DiaryImpl;
import oop.focus.diary.view.DiaryView;

/**
 * Implementation of {@link DiaryPagesController}. The class manages diary's section.
 */
public class DiaryPagesControllerImpl implements DiaryPagesController {
    private final Dao<DiaryImpl> diaryDao;
    private final View content;
    private final ObservableSet<DiaryImpl> set;

    /**
     * Instantiates a new diary pages controller and creates the associated view.
     *
     * @param diaryDao  an implementation of {@link oop.focus.db.Dao}
     */
    public DiaryPagesControllerImpl(final Dao<DiaryImpl> diaryDao) {
        this.diaryDao = diaryDao;
        this.set = FXCollections.observableSet();
        this.set.addAll(this.diaryDao.getAll());
        this.content = new DiaryView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObservableSet<DiaryImpl> getObservableSet() {
         return this.set;
     }
    /**
     * {@inheritDoc}
     */
     @Override
     public Set<String> getFileName() {
        return this.set.stream().map(DiaryImpl::getName).collect(Collectors.toSet());
     }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getContentByName(final String fileName) {
        final Optional<DiaryImpl> optional = this.diaryDao.getAll().stream().filter(s -> s.getName().equals(fileName)).findAny();
        if (optional.isPresent()) {
            return optional.get().getContent();
        }
        throw new IllegalArgumentException();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePage(final String name, final String content) {
        if (this.getFileName().contains(name)) {
            try {
                this.diaryDao.update(new DiaryImpl(content, name));
            } catch (final DaoAccessException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void createPage(final String name, final String content) {
        final DiaryImpl diary = new DiaryImpl(content, name);
        if (!this.set.contains(diary)) {
            try {
                this.diaryDao.save(diary);
            } catch (final DaoAccessException e) {
                e.printStackTrace();
            }
            this.set.add(diary);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public View getView() {
        return this.content;
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public void remove(final String input) {
        if (this.getFileName().contains(input)) {
            final DiaryImpl diary = new DiaryImpl(this.getContentByName(input), input);
            try {
                this.diaryDao.delete(diary);
            } catch (final DaoAccessException e) {
                e.printStackTrace();
            }
            this.set.remove(diary);
        }
    }
}

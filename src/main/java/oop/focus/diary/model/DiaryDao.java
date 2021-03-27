package oop.focus.diary.model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oop.focus.db.Dao;
import oop.focus.db.exceptions.ConnectionException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class DiaryDao implements Dao<DiaryImpl> {
    private static final int MAX_LENGTH = 50;
    private final Map<DiaryImpl, DiaryConnector> map;
    private static final String SEP = File.separator;
    private final ObservableList<DiaryImpl> list;
    private final DirectoryConnector directoryConnector = new DirectoryConnector();
    public DiaryDao() {
       this.map = new HashMap<>();
       this.directoryConnector.create();
       this.list = FXCollections.observableList(new ArrayList<>());
    }
    @Override
    public final ObservableList<DiaryImpl> getAll() {
        if (this.map.isEmpty()) {
            Arrays.stream(Objects.requireNonNull(this.directoryConnector.getConnection().listFiles())).forEach(elem -> {
                final DiaryConnector conn = new DiaryConnector(elem);
                conn.open();
                try {
                    final DiaryImpl diary = new DiaryImpl(conn.getConnection().getBufferedReader().readLine(), elem.getName());
                    this.map.put(diary, conn);
                    this.list.add(diary);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                conn.close();
            });
        }
        return FXCollections.unmodifiableObservableList(this.list);
   }
   private File getFile(final String name) {
        return Path.of(this.directoryConnector.getConnection() + SEP + name).toFile();
   }
    @Override
    public final void save(final DiaryImpl x) {
        if (x.getName().length() <= MAX_LENGTH && !this.getAll().contains(x)) {
            System.out.println(this.map.containsKey(x));
            final DiaryConnector diaryConnector = new DiaryConnector(this.getFile(x.getName()));
            try {
                diaryConnector.create();
            } catch (ConnectionException e) {
                e.printStackTrace();
            }
            this.map.put(x, diaryConnector);
            this.list.add(x);
            try {
                diaryConnector.open();
                diaryConnector.getConnection().getBufferedWriter().write(x.getContent());
                diaryConnector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public final void update(final DiaryImpl x) throws IllegalArgumentException {
        final Optional<DiaryImpl> di = this.getAll().stream().filter(l -> l.getName().equals(x.getName())).findAny();
        if (di.isPresent()) {
            delete(di.get());
            save(x);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public final void delete(final DiaryImpl x) {
        if (this.getAll().contains(x)) {
        try {
            Files.delete(this.map.get(x).getConnection().getFile());
            this.map.remove(x);
            this.list.remove(x);
        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
            throw new IllegalArgumentException();
        }
    }

}

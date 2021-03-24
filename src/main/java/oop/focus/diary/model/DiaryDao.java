package oop.focus.diary.model;
import oop.focus.db.Dao;
import oop.focus.db.exceptions.ConnectionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class DiaryDao implements Dao<DiaryImpl> {
    private static final int MAX_LENGTH = 50;
    private final Map<DiaryImpl, DiaryConnector> map;
    private static final String SEP = File.separator;
    private final DirectoryConnector directoryConnector = new DirectoryConnector();
    public DiaryDao() {
       this.map = new HashMap<>();
       this.directoryConnector.create();
    }
    @Override
    public final List<DiaryImpl> getAll() {
        Arrays.stream(Objects.requireNonNull(this.directoryConnector.getConnection().listFiles())).forEach(elem -> {
            final DiaryConnector conn = new DiaryConnector(elem);
            try {
                conn.getConnection().openBufferedReader(elem);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                this.map.put(new DiaryImpl(conn.getConnection().getBufferedReader().readLine(), elem.getName()), conn);
                conn.getConnection().getBufferedReader().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return new ArrayList<>(this.map.keySet());
   }
   private File getFile(final String name) {
        return Path.of(this.directoryConnector.getConnection() + SEP + name).toFile();
   }
    @Override
    public final void save(final DiaryImpl x) {
        if (x.getName().length() <= MAX_LENGTH || this.map.containsKey(x)) {
            final DiaryConnector diaryConnector = new DiaryConnector(this.getFile(x.getName()));
            try {
                diaryConnector.create();
            } catch (ConnectionException e) {
                e.printStackTrace();
            }
            this.map.put(x, diaryConnector);
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
            try {
                final DiaryConnector connector = this.map.get(di.get());
                connector.open();
                connector.getConnection().getBufferedWriter().write(x.getContent());
                di.get().setContent(x.getContent());
                connector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public final void delete(final DiaryImpl x) {
        try {
            if (this.getAll().contains(x)) {
                Files.delete(this.map.get(x).getConnection().getFile());
                this.map.remove(x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

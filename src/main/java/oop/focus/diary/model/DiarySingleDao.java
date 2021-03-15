package oop.focus.diary.model;



import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import oop.focus.db.SingleDao;
import oop.focus.db.exceptions.ConnectionException;

public class DiarySingleDao implements SingleDao<DiaryImpl> {
    private static final String SEP = File.separator;
    private List<DiaryImpl> list;
    private final DiaryConnector d;

    public DiarySingleDao() {
        this.d = new DiaryConnector();
        try {
            this.d.create();
        } catch (ConnectionException e) {
            e.printStackTrace();
        }
    }
    @Override
    public final List<DiaryImpl> getAll() {
        this.list = new ArrayList<>();
        Arrays.stream(Objects.requireNonNull(d.getConnection().listFiles())).forEach(elem -> {
            try {
                final DiaryImpl newD =  new DiaryImpl(getReader(elem).readLine(), elem.getName());
                if (!this.list.contains(newD)) {
                    this.list.add(newD);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return this.list;
    }
    private BufferedReader getReader(final File elem) throws FileNotFoundException {
        return new BufferedReader(new FileReader(elem));
    }

    private Path getDiaryPath(final DiaryImpl x) {
        return Paths.get(this.d.getConnection().getPath() + SEP + x.getName());
    }
    @Override
    public final void save(final DiaryImpl x) {
        final Path path = getDiaryPath(x);
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
                this.getAll().add(x);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            (getDos(path)).writeChars(x.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private DataOutputStream getDos(final Path path) throws FileNotFoundException {
        return new DataOutputStream(new FileOutputStream(path.toFile()));
    }
    @Override
    public final void update(final DiaryImpl x) throws IllegalArgumentException {
         for (final var elem : this.getAll()) {
            if (elem.getName().equals(x.getName())) {
                this.list.remove(this.getId(elem).get());
                save(x);
            }
        }
    }

    @Override
    public final void delete(final DiaryImpl x) {
        if (getAll().contains(x)) {
            this.list.remove(x);
            System.out.println(getDiaryPath(x).toFile().delete());
           /* try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
           */ //fileList.remove(x);
        }
    }
    @Override
    public final Optional<DiaryImpl> getValue(final int id) {
              if (getAll().get(id) != null) {
            return Optional.of(getAll().get(id));
        }
        return Optional.empty();
    }

    @Override
    public final Optional<Integer> getId(final DiaryImpl x) {
          if (this.getAll().contains(x)) {
            return Optional.of(this.getAll().indexOf(x));
        }
        return Optional.empty();
    }

}

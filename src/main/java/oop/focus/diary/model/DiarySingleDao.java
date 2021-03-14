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
import oop.focus.db.exceptions.DaoAccessException;

public class DiarySingleDao implements SingleDao<DiaryImpl> {
    private static final String SEP = File.separator;
    private final List<DiaryImpl> list;
    private final DiaryConnector d;

    public DiarySingleDao() {
        this.list = new ArrayList<>();
        this.d = new DiaryConnector();
        try {
            this.d.create();
        } catch (ConnectionException e) {
            e.printStackTrace();
        }
    }
    @Override
    public final List<DiaryImpl> getAll() {
        Arrays.stream(Objects.<File[]>requireNonNull(d.getConnection().listFiles())).forEach(elem -> {
            try {
                this.list.add(new DiaryImpl(getReader(elem).readLine(), elem.getName()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return this.list;
    }
    private BufferedReader getReader(final File elem) throws FileNotFoundException {
        return new BufferedReader(new FileReader(elem.toPath().toFile()));
    }


    private Path getDiaryPath(final DiaryImpl x) {
        return Paths.get(this.d.getConnection().getPath() + SEP + x.getName() + ".txt");
    }
    @Override
    public final void save(final DiaryImpl x) throws DaoAccessException {
        final Path path = getDiaryPath(x);
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
                this.list.add(x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            getDos(path).writeChars(x.getContent());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private DataOutputStream getDos(final Path path) throws FileNotFoundException {
        return new DataOutputStream(new FileOutputStream(path.toFile()));
    }

    @Override
    public final void update(final DiaryImpl x) throws IllegalArgumentException, DaoAccessException {
        final Optional<DiaryImpl> di = Optional.ofNullable(list.stream().filter(s -> Objects.equals(s.getName(), x.getName())).iterator().next());
        if (di.isPresent()) {
            di.get().setContent(x.getContent());
            save(di.get());
        }
    }

    @Override
    public final void delete(final DiaryImpl x) throws DaoAccessException {
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
        if (list.get(id) != null) {
            return Optional.of(list.get(id));
        }
        return Optional.empty();
    }

    @Override
    public final Optional<Integer> getId(final DiaryImpl x) {
        if (this.list.contains(x)) {
            return Optional.of(this.list.indexOf(x));
        }
        return Optional.empty();
    }

}

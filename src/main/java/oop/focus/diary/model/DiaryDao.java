package oop.focus.diary.model;


import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import oop.focus.db.Dao;
import oop.focus.db.exceptions.ConnectionException;

public class DiaryDao implements Dao<DiaryImpl> {
    private static final String SEP = File.separator;
    private final List<DiaryImpl> list;
    private final Map<DiaryImpl, DiaryConnector> map;
    private static final String SOURCE_PATH = ".focus";
    private static final String FOLDER_NAME = "DiarysPages";
    private final Path dirPath = Paths.get(System.getProperty("user.home") + SEP + SOURCE_PATH + SEP + FOLDER_NAME);
    public DiaryDao() {
       this.list = new ArrayList<>();
       this.map = new HashMap<>();
    }
    @Override
    public final List<DiaryImpl> getAll() {
        if (this.dirPath.toFile().exists()) {
            for (final var elem : Objects.requireNonNull(this.dirPath.toFile().listFiles())) {
                final DiaryConnector diary = new DiaryConnector(elem.toPath());
                try {
                    diary.open();
                    final DiaryImpl di = new DiaryImpl(diary.getConnection().readLine(), elem.getName());


                    map.put(di, diary);
                    if (!this.list.contains(di)) {
                        this.list.add(di);
                    }
                    diary.close();
                    } catch (IOException | ConnectionException e) {
                    e.printStackTrace();
                }
            }
            }
            return this.list;
   }
    @Override
    public final void save(final DiaryImpl x) {
        final Path pathd = this.getDiaryPath(x);
        if (!list.contains(x)) {
            this.list.add(x);
            final DiaryConnector diaryConnector = new DiaryConnector(pathd);
            map.put(x, diaryConnector);
        }
        try {
            this.map.get(x).open();
            this.getDos(pathd).writeChars(x.getContent());
            this.map.get(x).close();
        } catch (ConnectionException | IOException e) {
            e.printStackTrace();
        }
    }
    private Path getDiaryPath(final DiaryImpl x) {
        return Paths.get(dirPath.toString() + SEP + x.getName());
    }
    private DataOutputStream getDos(final Path path) throws FileNotFoundException {
        return new DataOutputStream(new FileOutputStream(path.toFile()));
    }
    @Override
    public final void update(final DiaryImpl x) throws IllegalArgumentException {
        final Optional<DiaryImpl> di = this.getAll().stream().filter(l -> l.getName().equals(x.getName())).findAny();
        if (di.isPresent()) {
            try {
                 this.map.get(di.get()).open();
                 this.getDos(this.getDiaryPath(di.get())).writeChars(x.getContent());
                 this.map.keySet().stream().filter(a -> a.equals(di.get())).iterator().next().setContent(x.getContent());
                 this.list.stream().filter(a -> a.equals(di.get())).iterator().next().setContent(x.getContent());
                 this.map.get(di.get()).close();

            } catch (ConnectionException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public final void delete(final DiaryImpl x) {
     // if (this.getAll().contains(x)) {
        try {
            this.getDos(this.getDiaryPath(x)).close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
        this.list.forEach(z -> System.out.println(z.getName()));
        this.list.remove(x);
    this.list.forEach(z -> System.out.println(z.getName()));
        this.map.remove(x);
        System.out.println("list" + this.list.contains(x));
    System.out.println(this.map.containsKey(x));
        try {
            Files.delete(this.getDiaryPath(x));
            //System.out.println(Paths.get(dirPath.toString() + SEP + x.getName()).toFile().exists());
            //System.out.println(Paths.get(dirPath.toString() + SEP + x.getName()).toFile().delete());
        } catch (IOException e) {
            e.printStackTrace();
        }
    //}
    }

}

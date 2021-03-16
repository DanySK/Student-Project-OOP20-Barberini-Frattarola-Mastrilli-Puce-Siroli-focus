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
import java.util.Optional;
import java.util.stream.Collectors;

import oop.focus.db.SingleDao;
import oop.focus.db.exceptions.ConnectionException;

public class DiarySingleDao implements SingleDao<DiaryImpl> {
    private static final String SEP = File.separator;
    private final List<DiaryImpl> list;
    private final Map<DiaryImpl, DiaryConnector> map;
    private static final String SOURCE_PATH = ".focus";
    private static final String FOLDER_NAME = "DiarysPages";
    private final Path dirPath = Paths.get(System.getProperty("user.home") + SEP + SOURCE_PATH + SEP + FOLDER_NAME);
    private FileOutputStream fos;
    public DiarySingleDao() {
       this.list = new ArrayList<>();
       this.map = new HashMap<>();
    }
    @Override
    public final List<DiaryImpl> getAll() {
        if (this.dirPath.toFile().exists()) {
        for (final var elem : this.dirPath.toFile().listFiles()) {
            final DiaryConnector diary = new DiaryConnector(elem.toPath());
            try {
                final DiaryImpl di = new DiaryImpl(diary.getConnection().readLine(), elem.getName());
                map.put(di, diary);
                this.list.add(di);
                } catch (FileNotFoundException e) {
                } catch (IOException e) {
                e.printStackTrace();
                }
            }
        }
        return this.list;
   }
    @Override
    public final void save(final DiaryImpl x) {
        final Path pathd = Paths.get(dirPath.toString() + SEP + x.getName());
        if (!list.contains(x)) {
            this.list.add(x);
            final DiaryConnector diaryConnector = new DiaryConnector(pathd);
            map.put(x, diaryConnector);
        }

        try {

            this.getDos(pathd).writeChars(x.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private DataOutputStream getDos(final Path path) throws FileNotFoundException {
        fos = new FileOutputStream(path.toFile());
        return new DataOutputStream(fos);
    }
    @Override
    public final void update(final DiaryImpl x) throws IllegalArgumentException {
       final List<DiaryImpl> di = this.getAll().stream().filter(l -> l.getName().equals(x.getName())).collect(Collectors.toList());
       if (!di.isEmpty()) {
            final Integer index = this.getId(di.get(0)).get();
            getAll().remove(index);
            getAll().get(index).setContent(x.getContent());
            save(x);
        }
    }

    @Override
    public final void delete(final DiaryImpl x) {
        if (getAll().contains(x)) {
            try {
                this.map.get(x).open();
                this.map.get(x).close();
                this.fos.close();
                this.getDos(Paths.get(dirPath.toString() + SEP + x.getName())).close();
            }  catch (ConnectionException | IOException e) {
                e.printStackTrace();
            }
            this.list.remove(x);
            this.map.remove(x);
            try {

                Files.delete(Paths.get(dirPath.toString() + SEP + x.getName()));
                System.out.println(Paths.get(dirPath.toString() + SEP + x.getName()).toFile().exists());
                System.out.println(Paths.get(dirPath.toString() + SEP + x.getName()).toFile().delete());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public final Optional<DiaryImpl> getValue(final int id) {
              if (getAll().get(id) != null) {

            return Optional.of(list.get(id));
        }
        return Optional.empty();
    }

    @Override
    public final Optional<Integer> getId(final DiaryImpl x) {
          if (this.getAll().contains(x)) {
            return Optional.of(this.list.indexOf(x));
        }
        return Optional.empty();
    }

}

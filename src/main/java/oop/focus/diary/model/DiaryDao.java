package oop.focus.diary.model;
import oop.focus.db.Dao;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DiaryDao implements Dao<DiaryImpl> {
    private static final int MAX_LENGTH = 50;
    private List<DiaryImpl> list;
    private DiaryConnector connector;
    public DiaryDao() {
       this.list = new ArrayList<>();
    }
    @Override
    public final List<DiaryImpl> getAll() {
        this.list = new ArrayList<>();
        this.connector = new DiaryConnector(Optional.empty());
        this.connector.getConnection().getList().forEach(elem -> {
            try {
                this.connector.getConnection().openBufferedReader(elem);
                this.list.add(new DiaryImpl(this.connector.getConnection().getBufferedReader().readLine(), elem.getName()));
                this.connector.getConnection().getBufferedReader().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return this.list;
   }
    @Override
    public final void save(final DiaryImpl x) {
        if (x.getName().length() <= MAX_LENGTH) {
            final DiaryConnector diaryConnector = new DiaryConnector(Optional.of(x.getName()));
            if (!this.list.contains(x)) {
                this.list.add(x);
            }
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
                final DiaryConnector connector = new DiaryConnector(Optional.ofNullable(di.get().getName()));
                connector.open();
                connector.getConnection().getBufferedWriter().write(x.getContent());
                this.list.stream().filter(a -> a.equals(di.get())).iterator().next().setContent(x.getContent());
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
                Files.delete(this.connector.getConnection().getList().stream().filter(a -> a.getName().equals(x.getName())).findAny().get().toPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package oop.focus.diary.model;

public class DiaryImpl implements Diary {
    private String content;
    private String name;
    public DiaryImpl(final String content, final String name) {
        super();
        this.content = content;
        this.name = name;
    }
    @Override
    public final String getContent() {
        return this.content;
    }
    @Override
    public final void setContent(final String content) {
        this.content = content;
    }
    @Override
    public final String getName() {
        return this.name;
    }
    @Override
    public final void setName(final String name) {
        this.name = name;
    }
}

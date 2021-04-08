package oop.focus.diary.model;

public class DiaryImpl implements Diary {
    private String content;
    private String name;
    public DiaryImpl(final String content, final String name) {
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
    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        return result;
    }
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final DiaryImpl other = (DiaryImpl) obj;
        if (this.name == null) {
            return other.name == null;
        } else {
            return this.name.equals(other.name);
        }
    }
}

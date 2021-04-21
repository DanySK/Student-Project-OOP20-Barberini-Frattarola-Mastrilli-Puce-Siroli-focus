package oop.focus.diary.model;

/**
 * Implementation of diary. The class can be used to set/get diary's fields.
 */
public class DiaryImpl implements Diary {
    private String content;
    private String name;

    /**
     * Instantiates a new diary, composed by the content and name in input.
     * @param content   the content of diary's page to create
     * @param name  the title of diary's page to create
     */
    public DiaryImpl(final String content, final String name) {
        this.content = content;
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getContent() {
        return this.content;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setContent(final String content) {
        this.content = content;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(final String name) {
        this.name = name;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        return result;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
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
